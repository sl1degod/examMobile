package com.example.api.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.R;
import com.example.api.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostAdapterViewHolder> {

    Context context;
    List<Post> postList = new ArrayList<>();


    public PostAdapter(Context context) {
        this.context = context;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.PostAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostAdapter.PostAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostAdapterViewHolder holder, int position) {
        holder.bind(postList.get(position));

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView title, body;

        public PostAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }

        public void bind(Post post) {
            title.setText(post.getTitle());
            body.setText(post.getBody());
        }

    }
}
