package com.coderbunker.assistant.widget.old;


import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.coderbunker.assistant.IApplication;
import com.coderbunker.assistant.R;
import com.coderbunker.assistant.currency.CurrencyProvider;
import com.coderbunker.assistant.currency.model.Currency;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class WidgetService extends Service {

    private int[] allWidgetIds;

    public WidgetService() {}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        allWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        CurrencyProvider provider = new CurrencyProvider((IApplication) getApplication());
        provider.getCurrency("USD").subscribe(getObserver());
        return super.onStartCommand(intent, flags, startId);
    }

    private Observer<Currency> getObserver() {
        return new Observer<Currency>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Currency currency) {
                // TODO process data
                for (int widgetId : allWidgetIds) {
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                    // simple_list_item_1

                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void processData(int widgetId, Currency currency) {
        Intent intent = new Intent();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_simple);
        views.setRemoteAdapter(widgetId, intent);
        appWidgetManager.updateAppWidget(widgetId, views);
    }


}
