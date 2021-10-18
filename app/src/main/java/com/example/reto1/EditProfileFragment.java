package com.example.reto1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

public class EditProfileFragment extends Fragment {

    private OnEditProfileListener listener = null;

    private EditText nameET, businessDesc;
    private ImageView businessImg;
    private Button saveBtn;

    private File file;

    private ActivityResultLauncher<Intent> cameraLauncher;

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

        nameET = view.findViewById(R.id.nameET);
        businessDesc = view.findViewById(R.id.businessDesc);
        businessImg = view.findViewById(R.id.businessImg);
        saveBtn = view.findViewById(R.id.saveBtn);

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::onCameraResult);

        saveBtn.setOnClickListener(v->{
            String name = nameET.getText().toString();
            String description = businessDesc.getText().toString();

            listener.onEdit(name,description,businessImg);

//            getActivity().getFragmentManager().beginTransaction().remove(this).commit();
        });

        businessImg.setOnClickListener(this::openCamera);


        return view;
    }

    public void onCameraResult(ActivityResult result){
        if(result.getResultCode()==getActivity().RESULT_OK){
            //Thumbnail
            //Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
            //image.setImageBitmap(bitmap);

            //Foto completa
            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
            Bitmap thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/4,bitmap.getHeight()/4,true);
            businessImg.setImageBitmap(thumbnail);

        }else if(result.getResultCode()==getActivity().RESULT_CANCELED){

        }
    }

    public void openCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // PROBLEMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAS
        file = new File(getActivity().getExternalFilesDir(null)+"/photo.png");
        Log.e(">>>",file.toString());
        Uri uri = FileProvider.getUriForFile(getActivity(),getActivity().getPackageName(),file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        cameraLauncher.launch(intent);
    }

    public void setListener(OnEditProfileListener listener) {
        this.listener=listener;
    }

    public interface OnEditProfileListener{
        void onEdit(String name, String desc, ImageView image);
    }

}