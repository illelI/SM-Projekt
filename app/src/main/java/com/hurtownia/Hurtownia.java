package com.hurtownia;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class Hurtownia extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    public void onCreate(){
        super.onCreate();
        Hurtownia.context = getApplicationContext();
    }
    public static Context getContext(){
        return Hurtownia.context;
    }
}
