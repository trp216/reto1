package com.example.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PostsFragment extends Fragment implements NewPublicationFragment.OnCreatePublicationListener{

    private NewPublicationFragment newPublicationFragment;




    private Button createBtn;

    public PostsFragment() {
        // Required empty public constructor
    }
    public static PostsFragment newInstance() {
        PostsFragment fragment = new PostsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_posts,container,false);

        createBtn = view.findViewById(R.id.createBtn);

        newPublicationFragment = NewPublicationFragment.newInstance();
        newPublicationFragment.setListener(this);

        createBtn.setOnClickListener(v->{
          showFragment(newPublicationFragment);
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void showFragment(Fragment f) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.bigCLPosts, f);
        transaction.commit();

    }

    @Override
    public void onCreateBtn(Fragment f) {
        showFragment(f);
    }


}