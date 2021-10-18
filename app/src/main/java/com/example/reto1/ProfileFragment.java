package com.example.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment implements EditProfileFragment.OnEditProfileListener {

    //State

    private ImageView image;
    private TextView businessName, description;
    private ImageButton editBtn;

    private EditProfileFragment editProfileFragment;

    private OnEditButtonListener listener = null;

    public ProfileFragment() {
        // Required empty public constructor
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
        editProfileFragment.setListener(this);


        editBtn.setOnClickListener(v->{
            listener.swapFragment(editProfileFragment);
        });

        return view;
    }

    @Override
    public void onEdit(String name, String desc, ImageView image) {
        this.image = image;
        businessName.setText(name);
        description.setText(desc);
    }

    public interface OnEditButtonListener{
        void swapFragment(Fragment f);
    }
}