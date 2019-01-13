package com.app.bndgram.ui.login;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.bndgram.MainActivity;
import com.app.bndgram.R;
import com.app.bndlibrary.ui.BaseActivity;
import com.onesignal.OneSignal;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import co.ronash.pushe.Pushe;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.input_username) AppCompatEditText input_username;
    @BindView(R.id.edt_password) AppCompatEditText edt_password;
    @BindView(R.id.btn_signin) Button btn_signin;
    @BindView(R.id.link_create)TextView link_create;
    @BindView(R.id.progress_bar) ProgressBar progress_bar;

    @BindString(R.string.error_password) String password_txt;
    @BindString(R.string.error_username) String username_txt;

    LoginPresentor loginPresentor;

      final String CHANNEL_ID="CHANNEL_ID";

    @Override
    public int setContentView() {
        return R.layout.activity_login;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        Pushe.initialize(this,true);

        loginPresentor = new LoginPresentor(this, new LoginInteractor());
        showNotify();

        //OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);

    }


    @OnClick(R.id.btn_signin)
    public void btn_signin_click(){


        loginPresentor.validateInputs(input_username.getText().toString(),
                edt_password.getText().toString());


    }


    @Override
    public void onUsernameError() {
        input_username.setError(username_txt);
    }

    @Override
    public void onPasswordError() {
        edt_password.setError(password_txt);
    }

    @Override
    public void hideProgressBar() {
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToHome(ResponseBody body) {
        Log.e("","");
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onServerError(String errorMessage) {
        Log.e("","");
    }

    private void showNotify(){


        NotificationManager notificationManager =(NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = CHANNEL_ID;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }



        NotificationCompat.Builder notification = new
                NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),1,intent,0);


        notification.setContentText("Welcome to Android");
        notification.setContentTitle("Welcome");
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setContentIntent(pendingIntent);


        notificationManager.notify(1,notification.build());


    }




}
