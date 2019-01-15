package com.app.bndgram.di;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {



    @Provides
    @Singleton
    Contact contactProvider() {
        return new Contact();
    }

    @Provides
    @Singleton
    User userProvides() {
        return new User(new Contact());
    }







}
