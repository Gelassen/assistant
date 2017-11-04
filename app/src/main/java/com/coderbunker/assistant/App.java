package com.coderbunker.assistant;


import android.app.Application;
import android.support.multidex.MultiDex;

import com.coderbunker.assistant.di.Component;
import com.coderbunker.assistant.di.DaggerComponent;
import com.coderbunker.assistant.di.IComponent;

import retrofit2.Retrofit;

public class App extends Application implements IApplication {

    public static final String TAG = "TAG";

    private Component component;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        component = DaggerComponent.builder().build();
    }

    @Override
    public IComponent getComponent() {
        return component;
    }

}
