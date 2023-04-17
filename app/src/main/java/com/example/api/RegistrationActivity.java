package com.example.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.api.models.User;
import com.example.api.databinding.ActivityRegistrationBinding;
import com.example.api.viewModel.RegistrationActivityViewModel;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    RegistrationActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViewModel();

        binding.buttonReg.setOnClickListener(view -> createOrUpdateUser());
        binding.goToAuth.setOnClickListener(view -> startActivity(new Intent(this, AuthorizationActivity.class)));
    }
    private void createOrUpdateUser(){
        viewModel.getCreateUserData().observe(this, user -> {
            if (user == null){
                Toast.makeText(this,"Пользователь не создан",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Пользователь успешно создан",Toast.LENGTH_SHORT).show();
            }
        });
        User user = new User("", binding.nameTIED.getText().toString(), binding.emailTIED.getText().toString(), "Male", "Active");
        viewModel.createUser(user);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(RegistrationActivityViewModel.class);
    }
}