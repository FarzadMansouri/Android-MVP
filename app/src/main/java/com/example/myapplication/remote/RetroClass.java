package com.example.myapplication.remote;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {

    public static Retrofit mRetrofit = null;
    private Context mContext;
    public static  String BaseUrl="http://api.themoviedb.org/3/";
    public static String TAG="TRACE";
    public static String Api_key="";


    public static Retrofit getmRetrofit(String address) {

        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(address)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return mRetrofit;

    }

}
