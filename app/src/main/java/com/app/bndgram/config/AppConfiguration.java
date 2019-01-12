package com.app.bndgram.config;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import io.realm.Realm;

public class AppConfiguration extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
