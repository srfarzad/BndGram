package com.app.bndgram.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootableService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context , "BootableService BND" , Toast.LENGTH_LONG).show();
    }
}
