package com.coderbunker.assistant;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.coderbunker.assistant.currency.CurrencyProvider;
import com.coderbunker.assistant.currency.CurrencyService;
import com.coderbunker.assistant.currency.model.Currency;

import org.reactivestreams.Subscriber;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private CurrencyProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        provider = new CurrencyProvider((App) getApplication());

        Button button = (Button) findViewById(R.id.ask_server);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    provider.getCurrency("USD")
                            .subscribe(new Observer<Currency>() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    Log.d(App.TAG, "onSubscribe");
                                }

                                @Override
                                public void onNext(@NonNull Currency currency) {
                                    Log.d(App.TAG, "onNext ");
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.e(App.TAG, "onError", e);
                                }

                                @Override
                                public void onComplete() {
                                    Log.d(App.TAG, "onComplete");
                                }
                            });
                } catch (Exception ex) {
                    Log.e(App.TAG, "Failed", ex);
                }
            }
        });
    }
}
