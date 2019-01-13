package com.app.bndgram.ui.login;

import android.util.Log;

import okhttp3.ResponseBody;

public class LoginPresentor implements LoginInteractor.OnLoginFinishedListener {

    LoginView loginView;
    LoginInteractor loginInteractor;

    public LoginPresentor(LoginView loginView,LoginInteractor loginInteractor){
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }


    public void validateInputs(String username , String password){
        if(loginView!=null)
            loginView.showProgressBar();
        loginInteractor.login(username,password,this);
    }

    @Override
    public void onErrorUsername() {
        if(loginView!=null) {
            loginView.hideProgressBar();
            loginView.onUsernameError();
        }

    }

    @Override
    public void onErrorPassword() {

        if(loginView!=null) {
            loginView.hideProgressBar();
            loginView.onPasswordError();
        }

    }

    @Override
    public void onSuccess(ResponseBody response) {
        if(loginView!=null){
            loginView.hideProgressBar();
            loginView.navigateToHome(response);
        }



    }

    @Override
    public void onError(String serverError) {

        if(loginView!=null){
            loginView.hideProgressBar();
            loginView.onServerError(serverError);
        }

    }
}
