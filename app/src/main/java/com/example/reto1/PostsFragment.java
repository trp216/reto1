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
import android.widget.ScrollView;

public class PostsFragment extends Fragment implements NewPublicationFragment.OnCreatePublicationListener{

    private NewPublicationFragment newPublicationFragment;

    private OnAddPostListener listener = null;

    private Button createBtn;

    private RecyclerView postsrecycler;

    private PostAdapter adapter;

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

        newPublicationFragment = NewPublicationFragment.newInstance();
        newPublicationFragment.setListener(this);

        createBtn.setOnClickListener(v->{
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.hide(this);
            ft.commit();
            listener.onAdd();
            listener.swapFragment(newPublicationFragment,1);
        });

        if(adapter.getItemCount()>0){
            ScrollView scrollView2 = view.findViewById(R.id.scrollView2);
            scrollView2.setVisibility(View.INVISIBLE);
            postsrecycler.setVisibility(View.VISIBLE);
        }

        return view;
    }


    @Override
    public void onCreateBtn(Post newPost) {
        //Aquí te quedaría entonces crear la publicación y mandar la info al recycler view
//        showFragment(f);
        adapter.addPost(newPost);
    }

    public interface OnAddPostListener{
        void onAdd();
        void swapFragment(Fragment f, int opt);
    }

    public void setListener(OnAddPostListener listener) {
        this.listener = listener;
    }
}