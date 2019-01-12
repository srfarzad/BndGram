package com.app.bndgram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.bndgram.adapter.PostAdapter;
import com.app.bndgram.models.IMessageListener;
import com.app.bndgram.models.UserData;
import com.app.bndgram.serviceCaller.WebserviceCaller;
import com.app.bndlibrary.ui.BaseActivity;
import com.app.bndlibrary.utils.Logger;
import com.app.bndlibrary.utils.NetworkState;

import java.util.List;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends BaseActivity {


    WebserviceCaller webserviceCaller;

    @BindView(R.id.recycler_post) RecyclerView recycler_post;



    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webserviceCaller = new WebserviceCaller();

        if(NetworkState.checkInternetConnection(getApplicationContext())){
            getPost();
        }
        else {
            Logger.Log("","Internet not avaliable");
        }


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









    }



    public void getPost(){

        webserviceCaller.getAllPosts(0, 10, new IMessageListener() {
            @Override
            public void onSuccess(List list) {
                Logger.Log("","");

                recycler_post.setAdapter(new PostAdapter(getApplicationContext(),
                        list));

                recycler_post.setLayoutManager(new LinearLayoutManager(
                        getApplicationContext(), LinearLayoutManager.VERTICAL , false
                ));

            }

            @Override
            public void onSuccessObject(Object item) {
                Logger.Log("","");
            }

            @Override
            public void onError(String errorMessage) {
                Logger.Log("","");
            }
        });
    }

}
