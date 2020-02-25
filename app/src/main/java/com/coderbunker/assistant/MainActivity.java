package com.coderbunker.assistant;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.coderbunker.assistant.currency.CurrencyProvider;
import com.coderbunker.assistant.currency.CurrencyService;
import com.coderbunker.assistant.currency.model.Currency;

import org.reactivestreams.Subscriber;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.clickTheButton)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, getString(R.string.toast_open_the_app), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
