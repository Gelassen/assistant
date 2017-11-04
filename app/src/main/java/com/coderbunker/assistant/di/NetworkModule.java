package com.coderbunker.assistant.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    @Named("currency")
    String providesCurrencyUrl() {
        return "http://api.fixer.io";
    }

}
