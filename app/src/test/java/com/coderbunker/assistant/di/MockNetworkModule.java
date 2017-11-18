package com.coderbunker.assistant.di;


import com.coderbunker.assistant.Config;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class MockNetworkModule {

    @Singleton
    @Provides
    @Named("currency")
    String providesCurrencyUrl() {
        return Config.API;
    }

    @Provides
    @Named("network")
    Scheduler providesSubscribeScheduler() {
        return Schedulers.trampoline();
    }

    @Provides
    @Named("ui")
    Scheduler providesObserveScheduler() {
        return Schedulers.trampoline();
    }
}
