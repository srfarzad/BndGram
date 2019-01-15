package com.app.bndgram.ui.login;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.app.bndgram.ui.map.MapActivity;
import com.app.bndgram.ui.register.RegisterActivity;
import com.app.bndlibrary.location.GPSTracker;
import com.app.bndlibrary.ui.BaseActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.onesignal.OneSignal;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import co.ronash.pushe.Pushe;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.input_username) AppCompatEditText input_username;
    @BindView(R.id.edt_password) AppCompatEditText edt_password;
    @BindView(R.id.btn_signin) Button btn_signin;
    @BindView(R.id.btn_map) Button btn_map;
    @BindView(R.id.btn_kotlin) Button btn_kotlin;
    @BindView(R.id.link_create)TextView link_create;
    @BindView(R.id.progress_bar) ProgressBar progress_bar;

    @BindString(R.string.error_password) String password_txt;
    @BindString(R.string.error_username) String username_txt;


    FirebaseAuth firebaseAuth;

    LoginPresentor loginPresentor;

      final String CHANNEL_ID="CHANNEL_ID";
    double lat,lang;


      int RC_SIGN_IN = 100;

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
        firebaseAuth = FirebaseAuth.getInstance();
        //OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.DEBUG);

        checkPermission();

    }


    @OnClick(R.id.btn_signin)
    public void btn_signin_click(){


        loginPresentor.validateInputs(input_username.getText().toString(),
                edt_password.getText().toString());

       /* firebaseAuth.createUserWithEmailAndPassword("sr.farzad@gmail.com"
        ,"1234567898777")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.e("","");

                    }
                });


      /*  firebaseAuth.signInWithEmailAndPassword("sr.farzad@gmail.com"
                ,"1234567898777")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("","");
                    }
                });*/



      /*  List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);*/



    }

    @OnClick(R.id.btn_map)
    public void btn_map_click(){
        Intent intent = new Intent(getApplicationContext(),MapActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.btn_kotlin)
    public void btn_kotlin_click(){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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


    void checkPermission() {

        Dexter.withActivity(LoginActivity.this)
                .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
                        if (gpsTracker.canGetLocation()) {

                            lat = gpsTracker.getLatitude();
                            lang = gpsTracker.getLongitude();

                            Log.e("","");

                            Locale locale = new Locale("fa");
                            Geocoder geocoder = new Geocoder(getApplicationContext(),locale);
                            try {
                                List<Address> addresses=geocoder.getFromLocation(lat,lang,1);

                                Log.e("","");

                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }


                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Log.e("Dexter", "There was an error: " + error.toString());
                    }
                }).check();

    }

}
