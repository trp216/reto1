package com.example.reto1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class EditProfileFragment extends Fragment implements ChoiceDialog.OnChoiceListener {

    private OnEditProfileListener listener = null;

    private EditText nameET, businessDesc;
    private ImageView businessImg;
    private Button saveBtn;

    private File file;

    private Profile newProfile;

    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        newProfile = new Profile();

        nameET = view.findViewById(R.id.nameET);
        businessDesc = view.findViewById(R.id.businessDesc);
        businessImg = view.findViewById(R.id.publicationsImg);
        saveBtn = view.findViewById(R.id.createBtn);

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::onCameraResult);
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::onGalleryResult);


        saveBtn.setOnClickListener(v->{
            String name = nameET.getText().toString();
            String description = businessDesc.getText().toString();
            newProfile.setName(name);
            newProfile.setDescription(description);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.remove(this);
            ft.commit();
            listener.onEdit(newProfile);

        });

        businessImg.setOnClickListener(this::openChoice);


        return view;
    }

    public void onGalleryResult(ActivityResult result){
        if(result.getResultCode()==getActivity().RESULT_OK){
            Uri uri = result.getData().getData();
            businessImg.setImageURI(uri);
            newProfile.setUri(uri.toString());
        }
    }

    public void onCameraResult(ActivityResult result){
        if(result.getResultCode()==getActivity().RESULT_OK){

            //Foto completa
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/4,bitmap.getHeight()/4,true);
            businessImg.setImageBitmap(thumbnail);
            Uri uri = FileProvider.getUriForFile(getContext(),getContext().getPackageName(),file);
            newProfile.setUri(uri.toString());

        }else if(result.getResultCode()==getActivity().RESULT_CANCELED){
            Toast.makeText(getContext(),"Operación cancelada", Toast.LENGTH_LONG).show();
        }
    }

    public void openCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(getContext().getExternalFilesDir(null)+"/photo.png");
//        Log.e(">>>",file.toString());
        Uri uri = FileProvider.getUriForFile(getContext(),getContext().getPackageName(),file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        cameraLauncher.launch(intent);
    }

    public void setListener(OnEditProfileListener listener) {
        this.listener=listener;
    }

    public void openChoice(View view){
        ChoiceDialog dialog = ChoiceDialog.newInstance();
        dialog.setListener(this);
        dialog.show(getActivity().getSupportFragmentManager(),"dialog");
    }

    @Override
    public void onChoice(int choice) {
        if(choice == 0){
            openCamera(getView());
        }else if(choice==1){
            openGallery(getView());
        }
    }

    private void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }

    public interface OnEditProfileListener{
        void onEdit(Profile profile);
    }

}