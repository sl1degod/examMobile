package com.example.api.retrofit;

import com.example.api.models.Post;
import com.example.api.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<List<User>> getUsersList();

    @GET("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<List<User>> getUsers(@Query("name") String user_login);


    @GET("posts")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<List<Post>> getPosts();

    @POST("posts")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<Post> createPost(@Body Post post);
}
