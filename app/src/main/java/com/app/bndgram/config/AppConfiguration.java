package com.app.bndgram.config;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.onesignal.OneSignal;

import io.realm.Realm;

public class AppConfiguration extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
