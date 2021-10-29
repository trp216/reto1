package com.example.reto1;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

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
            Bitmap bitmap = BitmapFactory.decodeFile(p.getUri());
            Bitmap thumbnail = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/4,bitmap.getHeight()/4,true);
            holder.getPostpic().setImageBitmap(thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addPost(Post newPost){
        posts.add(newPost);
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

}
