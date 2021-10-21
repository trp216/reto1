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

    private OnAddPostListener listener;

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
//          showFragment(newPublicationFragment);
            listener.onAdd();
        });
        // Inflate the layout for this fragment
        return view;
    }

    //Esto lo quité porque lo hago por medio de un listener en main activity
//    public void showFragment(Fragment f) {
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.bigCLPosts, f);
//        transaction.commit();
//
//    }

    @Override
    public void onCreateBtn(Fragment f) {
        //Aquí te quedaría entonces crear la publicación y mandar la info al recycler view
//        showFragment(f);
    }

    public interface OnAddPostListener{
        void onAdd();
    }

    public void setListener(OnAddPostListener listener) {
        this.listener = listener;
    }
}