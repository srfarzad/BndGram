package com.app.bndgram.ui.login;

import okhttp3.ResponseBody;

public interface LoginView {

    public void onUsernameError();

    public void onPasswordError();

    public void hideProgressBar();

    public void showProgressBar();

    public void navigateToHome(ResponseBody body);
    public void onServerError(String errorMessage);

}
