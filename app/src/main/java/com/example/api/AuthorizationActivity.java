package com.example.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.example.api.databinding.ActivityAuthorizationBinding;
import com.example.api.models.User;

import com.example.api.viewModel.AuthorizationActivityViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AuthorizationActivity extends AppCompatActivity {

    AuthorizationActivityViewModel viewModel;

    ProgressBar progressBar;

    TextInputLayout nameTIL, emailTIL;

    EditText nameTIED, emailTIED;

    Button buttonAuth;

    boolean result = true;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        nameTIL = findViewById(R.id.nameTIL);
        nameTIED = findViewById(R.id.nameTIED);
        emailTIED = findViewById(R.id.emailTIED);
        emailTIL = findViewById(R.id.emailTIL);
        buttonAuth = findViewById(R.id.button_auth);
        progressBar = findViewById(R.id.progress_bar);

        viewModel = new ViewModelProvider(this).get(AuthorizationActivityViewModel.class);

        buttonAuth.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            if (nameTIED.getText().toString().isEmpty()) {
                nameTIL.setError("Пустое поле!");
                emailTIL.setErrorEnabled(false);
                progressBar.setVisibility(View.GONE);
            } else if (emailTIED.getText().toString().isEmpty()) {
                emailTIL.setError("Пустое поле!");
                progressBar.setVisibility(View.GONE);
                nameTIL.setErrorEnabled(false);
            }  else {
                emailTIL.setErrorEnabled(false);
                nameTIL.setErrorEnabled(false);
                getUser(nameTIED.getText().toString(), emailTIED.getText().toString());
            }

        });
    }

    private void getUser(String user_login, String user_email) {
        viewModel.getLoadUserData().observe(this, user -> {
            for (int i = 0; i < user.size(); i++) {
                if (user_login.equals(user.get(i).getName()) && user_email.equals(user.get(i).getEmail())) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("user_id", user.get(i).getId());
                    Toast.makeText(this,"Успешно",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);
                } else {
                    result = false;
                    progressBar.setVisibility(View.GONE);
                }
            }
            if (!result) {
                Toast.makeText(this, "Такого пользователя нет", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getUsers(user_login);
    }





}