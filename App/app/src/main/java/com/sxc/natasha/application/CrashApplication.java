package com.sxc.natasha.application;

import android.app.Application;

/**
 * Created by bobo on 15/3/26.
 */
public class CrashApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
