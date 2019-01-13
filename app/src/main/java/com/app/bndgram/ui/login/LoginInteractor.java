package com.app.bndgram.ui.login;

import android.text.TextUtils;

import com.app.bndgram.models.IMessageListener;
import com.app.bndgram.serviceCaller.WebserviceCaller;

import java.util.List;

import okhttp3.ResponseBody;

public class LoginInteractor {

    public interface OnLoginFinishedListener {

        public void onErrorUsername();

        public void onErrorPassword();

        public void onSuccess(ResponseBody response);

        public void onError(String serverError);

    }


    public void login(String username, String password, OnLoginFinishedListener onLoginFinishedListener) {

        WebserviceCaller webserviceCaller = new WebserviceCaller();
        if (TextUtils.isEmpty(username)) {
            onLoginFinishedListener.onErrorUsername();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            onLoginFinishedListener.onErrorPassword();
            return;
        }

        webserviceCaller.login(username, password, new IMessageListener() {
            @Override
            public void onSuccess(List list) {

            }

            @Override
            public void onSuccessObject(Object item) {
                onLoginFinishedListener.onSuccess((ResponseBody)item);
            }

            @Override
            public void onError(String errorMessage) {
                onLoginFinishedListener.onError(errorMessage);
            }
        });


    }
}
