package com.gjd.puddingcomic.utils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gjd.puddingcomic.activitys.MyApp;

/**
 * Created by Administrator on 2016/6/27.
 */
public class GetJsonData {

    private static RequestQueue queue = MyApp.getQueue();
    public static String getJson(String url){

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        return null;
    }
}
