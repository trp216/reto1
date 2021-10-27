package com.example.reto1;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {

    //Views
    private TextView postname;
    private TextView postlocation;
    private TextView postend;
    private TextView poststart;

    public PostViewHolder(View itemView) {
        super(itemView);
        postname = itemView.findViewById(R.id.postname);
        postlocation = itemView.findViewById(R.id.postlocation);
        poststart = itemView.findViewById(R.id.poststart);
        postend = itemView.findViewById(R.id.postend);
    }

    public TextView getPostname() {
        return postname;
    }

    public TextView getPostlocation() {
        return postlocation;
    }

    public TextView getPostend() {
        return postend;
    }

    public TextView getPoststart() {
        return poststart;
    }
}
