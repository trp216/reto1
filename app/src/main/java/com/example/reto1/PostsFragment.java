package com.example.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PostsFragment extends Fragment implements NewPublicationFragment.OnCreatePublicationListener{

    private NewPublicationFragment newPublicationFragment;

    private OnAddPostListener listener = null;

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
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.hide(this);
            ft.commit();
            listener.onAdd();
            listener.swapFragment(newPublicationFragment,1);
        });
        return view;
    }


    @Override
    public void onCreateBtn(Fragment f) {
        //Aquí te quedaría entonces crear la publicación y mandar la info al recycler view
//        showFragment(f);
    }

    public interface OnAddPostListener{
        void onAdd();
        void swapFragment(Fragment f, int opt);
    }

    public void setListener(OnAddPostListener listener) {
        this.listener = listener;
    }
}