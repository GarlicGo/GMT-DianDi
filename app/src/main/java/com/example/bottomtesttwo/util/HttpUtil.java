package com.example.bottomtesttwo.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

//发起一条HTTP请求
public class HttpUtil {

    public static void sendOkHttpRequest(String adress,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(adress).build();
        client.newCall(request).enqueue(callback);
    }

}
