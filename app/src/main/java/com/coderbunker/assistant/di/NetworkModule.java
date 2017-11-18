package com.coderbunker.assistant.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    @Named("currency")
    String providesCurrencyUrl() {
        return "http://api.fixer.io";
    }

    @Provides
    @Named("network")
    Scheduler providesSubscribeScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Named("ui")
    Scheduler providesObserveScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
