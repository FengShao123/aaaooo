package com.gjd.puddingcomic.activitys;

import android.app.Application;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/6/25.
 */
public class MyApp extends Application{

    private static SharedPreferences spfs;
    public static RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        spfs = getSharedPreferences("config",MODE_PRIVATE);
//        isFirstLogin();
        requestQueue = Volley.newRequestQueue(this);
    }
    public static boolean isFirstLogin(){
        boolean state = spfs.getBoolean("isFristLogin",true);

        if(state){
            spfs.edit().putBoolean("isFristLogin",false);
        }
        return state;
    }
    public static RequestQueue getQueue(){

        return requestQueue;
    }

}
