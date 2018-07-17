package com.example.wanghaiyan.myfirstapp;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static  final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Log.e(TAG,"oncreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand");
        Log.e(TAG,flags+"+++++"+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy");
        super.onDestroy();
    }

}
