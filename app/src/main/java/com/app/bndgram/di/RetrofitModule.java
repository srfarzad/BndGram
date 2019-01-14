package com.app.bndgram.di;

import com.app.bndgram.serviceCaller.WebserviceCaller;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public WebserviceCaller getWebserice(){
        return new WebserviceCaller();
    }

}
