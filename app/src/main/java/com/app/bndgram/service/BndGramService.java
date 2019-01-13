package com.app.bndgram.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.app.bndgram.BndService;
import com.app.bndgram.RequestCallBack;

public class BndGramService extends Service {



    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    private final BndService.Stub binder = new BndService.Stub() {
        @Override
        public void login() throws RemoteException {
            Log.e("","");
        }

        @Override
        public void register(RequestCallBack callBack) throws RemoteException {
            Log.e("","");

            callBack.onCallBack("APP");
            callBack.asBinder();
        }

        @Override
        public void onAccept(String message) throws RemoteException {
            Log.e("","");
        }
    };

}
