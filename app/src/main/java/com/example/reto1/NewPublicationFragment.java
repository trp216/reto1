package com.example.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NewPublicationFragment extends Fragment {

    private OnCreatePublicationListener listener = null;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_publication, container, false);
    }

    public interface OnCreatePublicationListener{
        void onCreateBtn(Fragment f);
    }
}