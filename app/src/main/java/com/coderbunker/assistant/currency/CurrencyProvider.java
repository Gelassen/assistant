package com.coderbunker.assistant.currency;

import com.coderbunker.assistant.IApplication;
import com.coderbunker.assistant.currency.model.Currency;
import com.coderbunker.assistant.network.NetworkService;

import io.reactivex.Observable;
import retrofit2.http.Query;

public class CurrencyProvider implements CurrencyService {

    private NetworkService service;

    public CurrencyProvider(IApplication application) {
        service = new NetworkService(application);
    }

    @Override
    public Observable<Currency> getCurrency(@Query("base") String currency) {
        return service.getApi().getCurrency(currency);
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
    }
}
