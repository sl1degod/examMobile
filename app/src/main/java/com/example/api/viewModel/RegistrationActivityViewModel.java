package com.example.api.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.api.models.User;
import com.example.api.retrofit.RetrofitInstance;
import com.example.api.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivityViewModel extends ViewModel {
    private final MutableLiveData<User> createUserData = new MutableLiveData<>();
    public MutableLiveData<User> getCreateUserData() {
        return createUserData;
    }


    public void createUser(User user) {
        RetrofitService retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<User> call = retrofitService.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    createUserData.postValue(response.body());
                } else {
                    createUserData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                createUserData.postValue(null);
            }
        });
    }
}
