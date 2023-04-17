package com.example.api.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.R;
import com.example.api.adapter.UserAdapter;
import com.example.api.viewModel.MainActivityViewModel;


public class UsersFragment extends Fragment {

    private UserAdapter userAdapter;
    private RecyclerView recyclerView;
    private MainActivityViewModel viewModel;

    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        initRecycler();
        getUsers();

        return view;
    }

    private void initRecycler() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        context = getContext();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        userAdapter = new UserAdapter(getContext());
        recyclerView.setAdapter(userAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getUsers() {
        viewModel.getUsersData().observe((LifecycleOwner) context, users -> {
            if (users == null) {
                Toast.makeText(context, "Провал", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show();
                userAdapter.setUserList(users);
                userAdapter.notifyDataSetChanged();
            }
        });
        viewModel.getUsers();
    }

}