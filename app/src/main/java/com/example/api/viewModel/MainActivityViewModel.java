package com.example.api.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.api.models.Post;
import com.example.api.models.User;
import com.example.api.retrofit.RetrofitInstance;
import com.example.api.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<User>> usersData = new MutableLiveData<>();
    private MutableLiveData<List<Post>> postsData = new MutableLiveData<>();
    private final MutableLiveData<Post> createPostData = new MutableLiveData<>();

    public MutableLiveData<List<User>> getUsersData() {
        return usersData;
    }
    public MutableLiveData<List<Post>> getPostsData() {
        return postsData;
    }
    public MutableLiveData<Post> getCreatePostData() {
        return createPostData;
    }

    public void createPost(Post post) {
        RetrofitService retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<Post> call = retrofitService.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    createPostData.postValue(response.body());
                } else {
                    createPostData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                createPostData.postValue(null);
            }
        });
    }

    public void getUsers(String user_login) {
        RetrofitService retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<User>> call = retrofitService.getUsers(user_login);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    usersData.postValue(response.body());
                } else {
                    usersData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                usersData.postValue(null);
            }
        });
    }

    public void getUsersList() {
        RetrofitService retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<User>> call = retrofitService.getUsersList();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    usersData.postValue(response.body());
                } else {
                    usersData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                usersData.postValue(null);
            }
        });
    }

    public void getPosts() {
        RetrofitService retrofitService = RetrofitInstance.getRetrofitInstance().create(RetrofitService.class);
        Call<List<Post>> call = retrofitService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    postsData.postValue(response.body());
                } else {
                    postsData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postsData.postValue(null);
            }
        });
    }

}
