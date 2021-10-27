package com.example.reto1;

import static android.app.Activity.RESULT_OK;

import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class NewPublicationFragment extends Fragment implements MapsFragment.OnMapsListener{

    private MapsFragment mapsFragment;

    private OnCreatePublicationListener listener = null;

    private Button locationBtn, addBtn;

    private TextView nameEvent;
    private TextView textDir;
    private EditText inicio;
    private EditText fin;

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
        inicio = view.findViewById(R.id.inicio);
        fin = view.findViewById(R.id.fin);

        mapsFragment = new MapsFragment();
        mapsFragment.setListener(this);

        locationBtn = view.findViewById(R.id.locationBtn);
        addBtn = view.findViewById(R.id.addBtn);

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

        });
        return view;
    }

    @Override
    public void onMaps(String dir) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(this);
        transaction.commit();
        textDir.setText(dir);
    }

    public interface OnCreatePublicationListener{
        void onCreateBtn(Fragment f);
    }
}