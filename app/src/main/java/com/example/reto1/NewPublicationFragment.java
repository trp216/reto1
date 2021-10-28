package com.example.reto1;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NewPublicationFragment extends Fragment implements MapsFragment.OnMapsListener{

    private MapsFragment mapsFragment;

    private OnCreatePublicationListener listener = null;

    private Button locationBtn, addBtn, inicioBtn, finalBtn;

    private TextView nameEvent;
    private TextView textDir;


    private Post newPost;

    public NewPublicationFragment() {
        // Required empty public constructor
    }

    public void setListener(OnCreatePublicationListener listener) {
        this.listener=listener;
    }

    public static NewPublicationFragment newInstance() {
        NewPublicationFragment fragment = new NewPublicationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_publication, container, false);
        nameEvent = view.findViewById(R.id.nameEvent);
        textDir = view.findViewById(R.id.textDir);
        inicioBtn = view.findViewById(R.id.inicioBtn);
        finalBtn = view.findViewById(R.id.finalBtn);

        mapsFragment = new MapsFragment();
        mapsFragment.setListener(this);

        locationBtn = view.findViewById(R.id.locationBtn);
        addBtn = view.findViewById(R.id.addBtn);

        newPost = new Post();
        inicioBtn.setOnClickListener(this::defineStartDate);
        finalBtn.setOnClickListener(this::defineEndDate);

        locationBtn.setOnClickListener(
                v -> {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.fragmentContainer,mapsFragment);
                    transaction.hide(this);
                    transaction.commit();
                }
        );
        addBtn.setOnClickListener( v -> {

            newPost.setName(nameEvent.getText().toString());
            newPost.setBusiness("");
            newPost.setUri("");

            newPost.setStart(inicioBtn.getText().toString());
            newPost.setEnd(finalBtn.getText().toString());

            newPost.setLocation(textDir.getText().toString());
            listener.onCreateBtn(newPost);


        });
        return view;
    }

    @Override
    public void onMaps(String dir) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(mapsFragment);
        transaction.show(this);
        transaction.commit();
        textDir.setText(dir);
    }

    public interface OnCreatePublicationListener{
        void onCreateBtn(Post newPost);
    }
    private void defineStartDate(View view) {
        showDatePicker(date->{
            inicioBtn.setText(formatDate(date));
        });
    }

    private String formatDate(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return sdf.format(new Date(date));
    }

    private void defineEndDate(View view) {
        showDatePicker(date -> {
            finalBtn.setText(formatDate(date));
        });
    }

    private void showDatePicker(DateDialogFragment.OnDateSelectedListener listener) {
        DateDialogFragment dialog = new DateDialogFragment();
        dialog.setListener(listener);
        dialog.show(getActivity().getSupportFragmentManager(), "dialog");
    }
}