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
    @POST("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<User> createUser(@Body User user);

    @GET("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<User> getUserData(@Query("name") String user_login);

    @GET("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<List<User>> getUsers();

    @GET("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<List<User>> searchUsers(@Query("name") String searchText);

    @GET("users/{user_id}")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<User> getUser(@Path("user_id") String user_id);

    @PATCH("users/{user_id}")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<User> updateUser(@Path("user_id") String user_id, @Body User user);

    @DELETE("users")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<User> deleteUser(@Path("user_id") String user_id);

    @GET("posts")
    @Headers({"Accept:application/json", "Content-Type:application/json",
            "Authorization: Bearer 36b748bbf2efc888d06f7be38f975ddae2f90d4cc270ae73c5fec03f75bcd142"})
    Call<List<Post>> getPosts();
}
