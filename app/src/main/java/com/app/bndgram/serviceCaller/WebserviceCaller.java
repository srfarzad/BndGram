package com.app.bndgram.serviceCaller;

import android.content.Context;

import com.app.bndgram.models.IMessageListener;
import com.app.bndgram.models.Posts;

import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebserviceCaller {

    ApiInterface apiInterface;

    @Inject
    public WebserviceCaller() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }


    public void getAllPosts(int from, int to, final IMessageListener iMessageListener) {


        Call<List<Posts>> postList = apiInterface.getPosts(from, to);

        postList.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                iMessageListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                iMessageListener.onError(t.getMessage().toString() + "");
            }
        });


    }


    public void login(String username,String password, IMessageListener iMessageListener){

        Call<ResponseBody> login = apiInterface.login(username, password);

        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                iMessageListener.onSuccessObject(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                iMessageListener.onError(t.getMessage().toString()+"");
            }
        });


    }


}
