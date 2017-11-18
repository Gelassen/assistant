package com.coderbunker.assistant;


import android.app.Application;
import android.support.multidex.MultiDex;

import com.coderbunker.assistant.di.Component;
import com.coderbunker.assistant.di.DaggerComponent;
import com.coderbunker.assistant.di.IComponent;

import retrofit2.Retrofit;

public class App extends Application implements IApplication {

    public static final String TAG = "TAG";

    private static IApplication app;

    private Component component;
    private Retrofit retrofit;

    public static IApplication getApplication() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        component = DaggerComponent.builder().build();
        app = this;
    }

    @Override
    public IComponent getComponent() {
        return component;
    }

}
