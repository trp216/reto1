package com.example.reto1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.ArrayList;

public class PostsFragment extends Fragment implements NewPublicationFragment.OnCreatePublicationListener{

    private NewPublicationFragment newPublicationFragment;

    private OnAddPostListener listener = null;

    private Button createBtn;

    private RecyclerView postsrecycler;

    private PostAdapter adapter;

    private Button addMoreBtn;


    public PostsFragment() {
        // Required empty public constructor
        adapter = new PostAdapter();
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
        postsrecycler = view.findViewById(R.id.postsrecycler);

        postsrecycler.setHasFixedSize(true);
        postsrecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        postsrecycler.setAdapter(adapter);

        addMoreBtn = view.findViewById(R.id.addMoreBtn);
        addMoreBtn.setVisibility(View.INVISIBLE);

        newPublicationFragment = NewPublicationFragment.newInstance();
        newPublicationFragment.setListener(this);

        createBtn.setOnClickListener(v->{
            listener.swapFragment(newPublicationFragment,0);
        });
        addMoreBtn.setOnClickListener(v->{
            listener.swapFragment(newPublicationFragment,0);
        });

        if(adapter.getItemCount()>0){
            ScrollView scrollView2 = view.findViewById(R.id.scrollView2);
            scrollView2.setVisibility(View.INVISIBLE);
            postsrecycler.setVisibility(View.VISIBLE);
            addMoreBtn.setVisibility(View.VISIBLE);
            createBtn.setVisibility(View.INVISIBLE);
        }

        return view;
    }


    @Override
    public void onCreateBtn(Post newPost) {
        listener.onAdd(newPost);
    }

    public interface OnAddPostListener{
        void onAdd(Post newPost);
        void swapFragment(Fragment f, int opt);
    }

    public void addPost(Post newPost){
        adapter.addPost(newPost);
    }

    public void setListener(OnAddPostListener listener) {
        this.listener = listener;
    }

    public PostAdapter getAdapter() {
        return adapter;
    }

    public ArrayList<Post> getPosts(){
        return adapter.getPosts();
    }

}