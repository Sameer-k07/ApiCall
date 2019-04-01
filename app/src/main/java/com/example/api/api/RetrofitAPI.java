package com.example.api.api;

import com.example.api.model.FetchUser;
import com.example.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {


    //fetching user details
    @GET("/users")
    Call<List<User>> getUsers();

    //fetching posts of particular user
    @GET("/posts")
    Call<List<FetchUser>> getUsersPost(@Query("userId")int id);
}