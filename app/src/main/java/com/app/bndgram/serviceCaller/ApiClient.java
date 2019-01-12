package com.app.bndgram.serviceCaller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASEURL = "http://androidsupport.ir/picpic/";
    public static final String BASEURL_IMAGES = BASEURL+"images/";


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }


}
