package com.example.reto1;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private ArrayList<Post> posts;

    public PostAdapter(){
        posts = new ArrayList<>();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.postrow,parent,false);

        PostViewHolder holder = new PostViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post p = posts.get(position);
        holder.getPostName().setText(p.getName());
        holder.getPostlocation().setText(p.getLocation());
        holder.getPoststart().setText(p.getStart());
        holder.getPostend().setText(p.getEnd());
        holder.getPostBusiness().setText(p.getBusiness());

        if(p.getUri()!=null){
        Uri uri = Uri.parse(p.getUri());
        holder.getPostpic().setImageURI(uri);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addPost(Post newPost){
        posts.add(newPost);
    }
}
