package com.example.reto1;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment implements EditProfileFragment.OnEditProfileListener {

    //State
    private Profile profile;

    private ImageView image;
    private TextView businessName, description;
    private ImageButton editBtn;

    private EditProfileFragment editProfileFragment;

    private OnEditButtonListener listener = null;

    private FragmentTransaction ft;

    public ProfileFragment() {
        // Required empty public constructor
        profile = new Profile("Negocio por defecto", "Inserte aquí la descripción de su negocio", null);

    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setListener(OnEditButtonListener listener){
        this.listener = listener;
    }


    // El fragment se vuelve visible
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        image = view.findViewById(R.id.image);
        businessName = view.findViewById(R.id.businessName);
        description = view.findViewById(R.id.description);
        editBtn = view.findViewById(R.id.editBtn);
        editProfileFragment = EditProfileFragment.newInstance();
        editProfileFragment.setProfile(profile);
        editProfileFragment.setListener(this);


        businessName.setText(profile.getName());
        description.setText(profile.getDescription());
        if (profile.getUri() != null){
            String uri = profile.getUri();
            Uri imageUri = Uri.parse(uri);
            image.setImageURI(imageUri);
        }

        ft = getActivity().getSupportFragmentManager().beginTransaction();

        editBtn.setOnClickListener(v->{
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.hide(this);
            ft.commit();
            listener.onEditProfile(profile);
            listener.swapFragment(editProfileFragment, 1);
        });

        return view;
    }

    @Override
    public void onEdit(Profile profile) {
        businessName.setText(profile.getName());
        description.setText(profile.getDescription());
        if(profile.getUri()!=null) {
            image.setImageURI(Uri.parse(profile.getUri()));
        }
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.show(this);
        ft.commit();
    }

    public interface OnEditButtonListener{
        void swapFragment(Fragment f, int opt);
        void onEditProfile(Profile profile);
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }
}