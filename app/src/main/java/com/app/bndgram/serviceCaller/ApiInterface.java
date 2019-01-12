package com.app.bndgram.serviceCaller;

import com.app.bndgram.models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("posts.php")
    Call<List<Posts>> getPosts(@Field("from") int from , @Field("to") int to );


    @FormUrlEncoded
    @POST("register.php")
    Call<List<Posts>> register(@Field("username") String username , @Field("password") String password );

    @FormUrlEncoded
    @POST("login.php")
    Call<List<Posts>> login(@Field("username") String username , @Field("password") String password  );



}
