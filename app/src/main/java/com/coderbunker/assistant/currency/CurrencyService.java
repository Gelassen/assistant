package com.coderbunker.assistant.currency;


import com.coderbunker.assistant.currency.model.Currency;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyService {

    String API_LATEST = "/latest";

    @GET("/latest")
    Observable<Currency> getCurrency(@Query("base") String currency);

    Observable<ArrayList<String>> getCurrencyBoard(String currency);
}
