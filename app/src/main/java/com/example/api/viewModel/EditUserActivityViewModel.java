package com.example.api.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.api.models.User;
import com.example.api.retrofit.RetrofitInstance;
import com.example.api.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivityViewModel extends ViewModel {

    private MutableLiveData<User> createUserData = new MutableLiveData<>();
    private MutableLiveData<User> loadUserData = new MutableLiveData<>();
    private MutableLiveData<User> deleteUserData = new MutableLiveData<>();

    public MutableLiveData<User> getCreateUserData() {
        return createUserData;
    }

    public MutableLiveData<User> getLoadUserData() {
        return loadUserData;
    }

    public MutableLiveData<User> getDeleteUserData() {
        return deleteUserData;
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

    public void getUser(String userId) {
        RetrofitService retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<User> call = retrofitService.getUser(userId);
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

    public void updateUser(String userId, User user) {
        RetrofitService retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<User> call = retrofitService.updateUser(userId, user);
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

    public void deleteUser(String userId) {
        RetrofitService retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<User> call = retrofitService.deleteUser(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    deleteUserData.postValue(response.body());
                } else {
                    deleteUserData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                deleteUserData.postValue(null);
            }
        });
    }
}
