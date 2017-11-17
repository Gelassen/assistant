package com.coderbunker.assistant.currency;

import com.coderbunker.assistant.IApplication;
import com.coderbunker.assistant.currency.model.Currency;
import com.coderbunker.assistant.network.NetworkService;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

public class CurrencyProvider implements CurrencyService {

    private NetworkService service;

    public CurrencyProvider(IApplication application) {
        service = new NetworkService(application);
    }

    @Override
    public Observable<Currency> getCurrency(@Query("base") String currency) {
        return service.getApi().getCurrency(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ArrayList<String>> getCurrencyBoard(String currency) {
        return getCurrency(currency)
                .flatMap(new Function<Currency, ObservableSource<ArrayList<String>>>() {
                    @Override
                    public ObservableSource<ArrayList<String>> apply(@NonNull Currency currency) throws Exception {
                        // TODO proceed through all data and obtain currency board
                        ArrayList<String> result = new ArrayList<>();
                        result.add("USD");
                        return Observable.just(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
