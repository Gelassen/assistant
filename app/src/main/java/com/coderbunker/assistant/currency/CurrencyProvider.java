package com.coderbunker.assistant.currency;


import com.coderbunker.assistant.App;
import com.coderbunker.assistant.currency.model.Currency;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

public class CurrencyProvider implements CurrencyService {

    private CurrencyService service;

    public CurrencyProvider(App context) {
        this.service = context.getRetrofit().create(CurrencyService.class);
    }

    @Override
    public Observable<Currency> getCurrency(@Query("base") String currency) {
        return service.getCurrency(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
