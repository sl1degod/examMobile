package com.example.api.viewModel;

import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.ViewModel;

import com.example.api.models.User;
import com.example.api.retrofit.RetrofitInstance;
import com.example.api.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorizationActivityViewModel extends ViewModel {
    private final MutableLiveData<User> loadUserData = new MutableLiveData<>();

    public MutableLiveData<User> getLoadUserData() {
        return loadUserData;
    }


    public void getUsers(String user_login) {
        RetrofitService retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<User> call = retrofitService.getUserData(user_login);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    loadUserData.postValue(response.body());
                } else {
                    loadUserData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                loadUserData.postValue(null);
            }
        });
    }
}
