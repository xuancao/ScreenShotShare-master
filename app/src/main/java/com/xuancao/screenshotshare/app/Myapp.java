package com.xuancao.screenshotshare.app;

import android.app.Application;

/**
 * Created by Administrator on 2017/10/17.
 */

public class Myapp extends Application {


    private static Myapp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Myapp getInstance(){
        return instance;
    }
}
