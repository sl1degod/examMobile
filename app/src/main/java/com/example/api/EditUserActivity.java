package com.example.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.api.databinding.ActivityEditUserBinding;
import com.example.api.databinding.ActivityMainBinding;
import com.example.api.models.User;
import com.example.api.viewModel.EditUserActivityViewModel;
import com.example.api.viewModel.MainActivityViewModel;

public class EditUserActivity extends AppCompatActivity {

    ActivityEditUserBinding binding;
    EditUserActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        viewModel = new ViewModelProvider(this).get(EditUserActivityViewModel.class);

        binding.nameTIED.setText(getIntent().getStringExtra("user_name"));
        binding.emailTIED.setText(getIntent().getStringExtra("user_email"));
        String userId = getIntent().getStringExtra("user_id");
        if (userId != null) loadUser(userId);

        binding.buttonUpdate.setOnClickListener(view -> createOrUpdateUser(userId));
        binding.buttonDelete.setOnClickListener(view -> deleteUser(userId));
    }

    private void loadUser(String userId) {
        viewModel.getLoadUserData().observe(this, user ->{
            if (user != null) {
                binding.nameTIED.setText(user.getName());
                binding.emailTIED.setText(user.getEmail());
            }
        });
        viewModel.getUser(userId);
    }
    private void createOrUpdateUser(String userId){
        viewModel.getCreateUserData().observe(this, user -> {
            if (user == null){
                Toast.makeText(this,"Failed create/update new user",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Successfully create/update new user",Toast.LENGTH_SHORT).show();
                setResult(200);
                finish();
            }
        });
        User user = new User("", binding.nameTIED.getText().toString(), binding.emailTIED.getText().toString(), "Male", "Active");
        if (userId == null) viewModel.createUser(user);
        else viewModel.updateUser(userId, user);
    }

    private void deleteUser(String userId) {
        viewModel.getDeleteUserData().observe(this, user -> {
            if (user == null){
                Toast.makeText(this,"Failed create/update new user",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"Successfully create/update new user",Toast.LENGTH_SHORT).show();
                setResult(200);
                finish();
            }
        });
        viewModel.deleteUser(userId);
    }
}