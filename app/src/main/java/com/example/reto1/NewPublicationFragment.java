package com.example.reto1;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class NewPublicationFragment extends Fragment implements MapsFragment.OnMapsListener{

    private MapsFragment mapsFragment;

    private OnCreatePublicationListener listener = null;

    private ImageView businessImg;

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

        businessImg = view.findViewById(R.id.businessImg);
        ActivityResultLauncher<Intent> launcherMaps = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), this::onMaps
        );
        businessImg.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this.getActivity(), MapsFragment.class);
                }
        ); //Cuando se de click se abra el fragmento del mapa
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_publication, container, false);
    }

    public void onMaps(ActivityResult result) {
        if(result.getResultCode() == RESULT_OK) {
            String name = result.getData().getExtras().getString("direccion");
            textDir.setText(name);
        }
    }

    @Override
    public void onMaps(String dir) {

    }

    public interface OnCreatePublicationListener{
        void onCreateBtn(Fragment f);
    }
}