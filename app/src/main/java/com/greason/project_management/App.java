package com.greason.project_management.activity;

import android.app.Application;
import android.content.Context;

import de.greenrobot.event.EventBus;

/**
 * Created by Greason on 17/4/27.
 */

public class App extends Application {

    public static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationContext = getApplicationContext();

    }


    public static Context getContext() {
        return mApplicationContext;
    }

    public static void RegisterEventBus(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void UnregisterEventBus(Object subscriber) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber);
        }
    }

}
