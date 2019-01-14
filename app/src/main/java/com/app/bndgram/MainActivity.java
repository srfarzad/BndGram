package com.app.bndgram;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.bndgram.adapter.PostAdapter;
import com.app.bndgram.di.DaggerUserComponent;
import com.app.bndgram.di.RetrofitModule;
import com.app.bndgram.di.User;
import com.app.bndgram.di.UserComponent;
import com.app.bndgram.di.UserModule;
import com.app.bndgram.models.IMessageListener;
import com.app.bndgram.models.UserData;
import com.app.bndgram.service.UpdateService;
import com.app.bndgram.serviceCaller.WebserviceCaller;
import com.app.bndlibrary.location.GPSTracker;
import com.app.bndlibrary.ui.BaseActivity;
import com.app.bndlibrary.utils.Logger;
import com.app.bndlibrary.utils.NetworkState;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity {


    WebserviceCaller webserviceCaller;

    @BindView(R.id.recycler_post)
    RecyclerView recycler_post;

    double lat,lang;


    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webserviceCaller = new WebserviceCaller();

        if (NetworkState.checkInternetConnection(getApplicationContext())) {
            getPost();
        } else {
            Logger.Log("", "Internet not avaliable");
        }

        Intent intent = new Intent(getApplicationContext(), UpdateService.class);
        startService(intent);

/*

        UserData userData = new UserData();
        userData.id = 2;
        userData.description ="Test description";
        userData.title = "Android";

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(userData);
        realm.commitTransaction();



        RealmResults<UserData> userData1 = realm.where(UserData.class)
                .lessThanOrEqualTo("id",1)
                .findAll();

        Log.e("","");
*/


        Intent intent1 = new Intent();
        intent1.setClassName("com.app.bndsecond", "com.app.bndsecond.MyReciever");
        intent1.setAction("com.app.bndsecond.MyReciever");
        intent1.putExtra("id", 4);
        intent1.putExtra("name", "Android");
        sendBroadcast(intent1);


        // UserComponent component = DaggerUserComponent.builder().userModule(new UserModule()).build();


        UserComponent userComponent = DaggerUserComponent.builder().userModule(new UserModule())
                .build();

        User user = userComponent.provideUser();

        user.getUserContact();


        UserComponent component = DaggerUserComponent.builder().
                retrofitModule(new RetrofitModule()).build();


        // component.provideWebservice().


        checkPermission();


    }

    void checkPermission() {

        Dexter.withActivity(MainActivity.this)
                .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        GPSTracker gpsTracker = new GPSTracker(getApplicationContext());
                        if (gpsTracker.canGetLocation()) {

                            lat = gpsTracker.getLatitude();
                            lang = gpsTracker.getLongitude();

                            Log.e("","");

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


    public void getPost() {

        webserviceCaller.getAllPosts(0, 10, new IMessageListener() {
            @Override
            public void onSuccess(List list) {
                Logger.Log("", "");

                recycler_post.setAdapter(new PostAdapter(getApplicationContext(),
                        list));

                recycler_post.setLayoutManager(new LinearLayoutManager(
                        getApplicationContext(), LinearLayoutManager.VERTICAL, false
                ));

            }

            @Override
            public void onSuccessObject(Object item) {
                Logger.Log("", "");
            }

            @Override
            public void onError(String errorMessage) {
                Logger.Log("", "");
            }
        });
    }


}
