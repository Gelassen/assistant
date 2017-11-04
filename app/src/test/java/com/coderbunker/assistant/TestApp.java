package com.coderbunker.assistant;


import android.app.Application;

import com.coderbunker.assistant.di.DaggerMockComponent;
import com.coderbunker.assistant.di.IComponent;
import com.coderbunker.assistant.di.MockComponent;


public class TestApp extends Application implements IApplication {

    private MockComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMockComponent.builder().build();
    }

    @Override
    public IComponent getComponent() {
        return component;
    }
}
