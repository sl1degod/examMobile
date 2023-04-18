package com.example.api.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.MainActivity;
import com.example.api.R;
import com.example.api.adapter.PostAdapter;
import com.example.api.models.Post;
import com.example.api.viewModel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class PostsFragment extends Fragment {

    private PostAdapter postAdapter;
    private RecyclerView recyclerView;
    private MainActivityViewModel viewModel;

    Button buttonCancel, buttonAdd;

    FloatingActionButton addPost;

    TextInputLayout headerTIL, bodyTIL;

    EditText header, body;

    private Dialog dialog;



    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        addPost = view.findViewById(R.id.buttonAddPost);

        initRecycler();
        getPosts();



        addPost.setOnClickListener(v -> {
            showDialog();
            createPost();
            postAdapter = new PostAdapter(getContext());
            recyclerView.setAdapter(postAdapter);
        });

        return view;
    }

    private void initRecycler() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        context = getContext();
        dialog = new Dialog(context);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        postAdapter = new PostAdapter(getContext());
        recyclerView.setAdapter(postAdapter);
    }

    private void showDialog() {
        dialog.setContentView(R.layout.alert_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        header = dialog.findViewById(R.id.headerTIED);
        body = dialog.findViewById(R.id.bodyTIED);
        buttonAdd = dialog.findViewById(R.id.button_add);
        buttonCancel = dialog.findViewById(R.id.button_cancel);
        headerTIL = dialog.findViewById(R.id.headerTIL);
        bodyTIL = dialog.findViewById(R.id.bodyTIL);

        buttonAdd.setOnClickListener(v -> {
            if (header.getText().toString().isEmpty()){
                headerTIL.setError("Введите заголовок");
                bodyTIL.setErrorEnabled(false);

            } else if (body.getText().toString().isEmpty()) {
                bodyTIL.setError("Введите информацию");
                headerTIL.setErrorEnabled(false);
            } else {
                bodyTIL.setErrorEnabled(false);
                headerTIL.setErrorEnabled(false);
                createPost();
                getPosts();
                postAdapter = new PostAdapter(getContext());
                recyclerView.setAdapter(postAdapter);
                dialog.hide();
            }
        });

        buttonCancel.setOnClickListener(v -> {
            dialog.cancel();
            getPosts();
            recyclerView.setAdapter(postAdapter);
            dialog.hide();
        });

        dialog.show();
    }

    private void createPost(){
        viewModel.getCreatePostData().observe((LifecycleOwner) context, posts -> {
            if (header.getText().toString().isEmpty()){

            } else if (body.getText().toString().isEmpty()) {

            } else {
                Toast.makeText(context,"Пост успешно создан",Toast.LENGTH_SHORT).show();
            }
        });
        Post post = new Post("", MainActivity.user_id, header.getText().toString(), body.getText().toString());
        viewModel.createPost(post);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getPosts() {
        viewModel.getPostsData().observe((LifecycleOwner) context, posts -> {
            if (posts == null) {
                Toast.makeText(context, "Провал", Toast.LENGTH_SHORT).show();
            } else {
                postAdapter.setPostList(posts);
                postAdapter.notifyDataSetChanged();
            }
        });
        viewModel.getPosts();
    }

}