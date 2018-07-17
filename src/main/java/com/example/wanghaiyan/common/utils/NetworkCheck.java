package com.example.wanghaiyan.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

public class NetworkCheck {
    private String URL = "https://m.baidu.com";
    public static boolean checkNetwork(Context context){
        try{
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(manager != null){
                NetworkInfo info = manager.getActiveNetworkInfo();
                if(info != null && info.isConnected()){
                    if(info.getState() == NetworkInfo.State.CONNECTED){

                        return true;

                    }
                }
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }
}
