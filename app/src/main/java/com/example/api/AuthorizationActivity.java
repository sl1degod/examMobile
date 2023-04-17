package com.example.api;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.api.databinding.ActivityAuthorizationBinding;
import com.example.api.models.User;

import com.example.api.viewModel.AuthorizationActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class AuthorizationActivity extends AppCompatActivity {

    ActivityAuthorizationBinding binding;
    AuthorizationActivityViewModel viewModel;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthorizationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AuthorizationActivityViewModel.class);

        binding.buttonAuth.setOnClickListener(view -> {
//            if (binding.nameTIED.toString().isEmpty()) {
//                Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
//            } else {
//                getUser(binding.nameTIED.getText().toString());
//            }
            startActivity(new Intent(this, MainActivity.class));
        });

        binding.nameTIL.setHelperText("");
        binding.nameTIL.setBoxStrokeColor(R.color.error);
    }


    private void getUser(String user_login) {
        viewModel.getLoadUserData().observe(this, user -> {
            if (user_login.equals(user.getName())) {
                startActivity(new Intent(this, MainActivity.class));
            }
            else {
                Toast.makeText(this, "Такого пользователя нет!", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getUsers(user_login);
    }

}