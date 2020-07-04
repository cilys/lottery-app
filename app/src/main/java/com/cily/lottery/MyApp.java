package com.cily.lottery;

import android.app.Application;

import com.cily.utils.app.rx.okhttp.NetConf;
//import android.support.multidex.MultiDex;

/**
 * Created by 123 on 2018/4/21.
 */

public class MyApp extends Application {
    private static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        NetConf.setBaseUrl(Conf.URL_BASE + "lottery/");
//        MultiDex.install(this);
    }

    public static MyApp getApp(){
        return app;
    }
}
