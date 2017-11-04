package com.coderbunker.assistant.di;


import com.coderbunker.assistant.Config;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MockNetworkModule {

    @Singleton
    @Provides
    @Named("currency")
    String providesCurrencyUrl() {
        return Config.API;
    }
}
