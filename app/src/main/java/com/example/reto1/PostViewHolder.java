package com.example.reto1;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {

    //Views
    private TextView postBusiness;
    private TextView postlocation;
    private TextView postend;
    private TextView poststart;
    private TextView postName;
    private ImageView postpic;

    public PostViewHolder(View itemView) {
        super(itemView);
        postBusiness = itemView.findViewById(R.id.postBusiness);
        postlocation = itemView.findViewById(R.id.postlocation);
        poststart = itemView.findViewById(R.id.poststart);
        postend = itemView.findViewById(R.id.postend);
        postName = itemView.findViewById(R.id.postName);
        postpic = itemView.findViewById(R.id.postpic);
    }

    public TextView getPostBusiness() {
        return postBusiness;
    }

    public TextView getPostName() {
        return postName;
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

    public ImageView getPostpic() {
        return postpic;
    }
}
