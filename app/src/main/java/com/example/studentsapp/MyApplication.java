package com.example.studentsapp;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.os.Handler;
import android.provider.ContactsContract;

import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    private static Context appContext;
    public static ExecutorService executorService = Executors.newFixedThreadPool(1);
    final public static Handler mainHandler = HandlerCompat.createAsync(Looper.getMainLooper());


    @Override
    public void onCreate(){
        super.onCreate();
        appContext = getApplicationContext();
    }
    public static Context getContext(){
        return appContext;
    };

}
