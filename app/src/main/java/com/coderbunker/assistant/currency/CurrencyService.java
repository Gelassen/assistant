package com.coderbunker.assistant.currency;


import com.coderbunker.assistant.currency.model.Currency;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyService {

    @GET("/latest")
    Observable<Currency> getCurrency(@Query("base") String currency);
}
