package com.app.bndgram.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationProviderModule {

    private Application application;

    public ApplicationProviderModule(Application application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }


}
