package com.coderbunker.assistant;


import android.app.Application;
import android.support.multidex.MultiDex;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static final String TAG = "TAG";

    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_currency))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
