package com.coderbunker.assistant.network;


import com.coderbunker.assistant.IApplication;
import com.coderbunker.assistant.currency.CurrencyService;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    @Inject
    @Named("currency")
    String url;

    private CurrencyService service;

    public NetworkService(IApplication application) {
        application.getComponent().inject(this);
        service = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CurrencyService.class);
    }

    public CurrencyService getApi() {
        return service;
    }
}
