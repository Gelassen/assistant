package com.coderbunker.assistant.currency;

import android.util.Pair;

import com.coderbunker.assistant.IApplication;
import com.coderbunker.assistant.currency.model.Currency;
import com.coderbunker.assistant.currency.model.Rates;
import com.coderbunker.assistant.network.NetworkService;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

public class CurrencyProvider implements CurrencyService {

    @Inject
    @Named("network")
    Scheduler subscribeScheduler;

    @Inject
    @Named("ui")
    Scheduler observeScheduler;

    private NetworkService service;

    public CurrencyProvider(IApplication application) {
        application.getComponent().inject(this);
        service = new NetworkService(application);
    }

    @Override
    public Observable<Currency> getCurrency(@Query("base") String currency) {
        return service.getApi().getCurrency(currency)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler);
    }

    @Override
    public Observable<ArrayList<String>> getCurrencyBoard(String currency) {
        return getCurrency(currency)
                .flatMap(new Function<Currency, ObservableSource<ArrayList<String>>>() {
                    @Override
                    public ObservableSource<ArrayList<String>> apply(@NonNull Currency currency) throws Exception {
                        Rates rates = currency.getRates();
                        ArrayList<String> result = new ArrayList<>();
                        result.add(convertCurrencyToDisplayFormat(rates.getUsd()));
                        result.add(convertCurrencyToDisplayFormat(rates.getRub()));
                        result.add(convertCurrencyToDisplayFormat(rates.getEur()));
                        result.add(convertCurrencyToDisplayFormat(rates.getHkd()));
                        result.add(convertCurrencyToDisplayFormat(rates.getSgd()));
                        return Observable.just(result);
                    }
                })
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler);
    }

    // TODO refactor me into separate layer with flexible BASE currency
    private String convertCurrencyToDisplayFormat(Pair<String, Double> pair) {
        final int amount = 10;
        final String prefix = amount + " CNY = ";
        StringBuilder builder = new StringBuilder();
        return  builder.append(prefix)
                .append(String.format("%.2f", pair.second * amount))
                .append(" ")
                .append(pair.first).toString();
    }
}
