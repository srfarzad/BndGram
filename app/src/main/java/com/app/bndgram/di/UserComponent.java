package com.app.bndgram.di;


import android.app.Application;

import com.app.bndgram.MainActivity;
import com.app.bndgram.serviceCaller.WebserviceCaller;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

@Singleton
@Component(modules = {UserModule.class,ApplicationProviderModule.class, RetrofitModule.class})
public interface UserComponent {

    User provideUser();

    UserDbAdapter provideDatabase();

    WebserviceCaller provideWebservice();


    void inject(MainActivity activity);



}
