package com.coderbunker.assistant.widget;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

import com.coderbunker.assistant.App;
import com.coderbunker.assistant.IApplication;
import com.coderbunker.assistant.currency.CurrencyProvider;
import com.coderbunker.assistant.currency.model.Currency;
import com.coderbunker.assistant.utils.SimpleObserver;
import com.coderbunker.assistant.widget.contracts.IWidgetCollectionAdapter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class WidgetCollectionService extends RemoteViewsService {

    private static final String EXTRA_WIDGET_ID = "EXTRA_WIDGET_ID";

    private CurrencyProvider currencyProvider;

    private WidgetRemoteViewsFactory widgetRemoteViewsFactory;

    private IWidgetCollectionAdapter viewsFactory;

    private int widgetId;

    public static void startService(Context context, int widgetId) {
        Intent intent = new Intent(context, WidgetCollectionService.class);
        intent.putExtra(EXTRA_WIDGET_ID, widgetId);
        context.startService(intent);
    }

    public WidgetCollectionService() {
        widgetRemoteViewsFactory = new WidgetRemoteViewsFactory();
    }

    @Inject
    public WidgetCollectionService(CurrencyProvider currencyProvider,
                                   WidgetRemoteViewsFactory widgetRemoteViewsFactory) {
        this.currencyProvider = currencyProvider;
        this.widgetRemoteViewsFactory = widgetRemoteViewsFactory;
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d(App.TAG, "onGetViewFactory");
        widgetId = intent.getIntExtra(EXTRA_WIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        viewsFactory = widgetRemoteViewsFactory.newInstance(this);
        Observable<Currency> data = getCurrencyProvider().getCurrency("USD");
        data.subscribe(getCurrencyObserver());
        return viewsFactory;
    }

    // region private methods

    private CurrencyProvider getCurrencyProvider() {
        if (currencyProvider == null) {
            currencyProvider = new CurrencyProvider((IApplication) getApplication());
        }
        return currencyProvider;
    }

    private Observer<Currency> getCurrencyObserver() {
        return new SimpleObserver<Currency>() {

            @Override
            public void onError(Throwable error) {
                super.onError(error);
                Log.e(App.TAG, "onError", error);
            }

            @Override
            public void onNext(Currency data) {
                super.onNext(data);
                Log.d(App.TAG, "viewsFactory.updateDataSet(data)");
                viewsFactory.updateDataSet(data);
                updateDataSet();
            }
        };
    }

    private void updateDataSet() {
        Intent widgetUpdateIntent = new Intent();
        widgetUpdateIntent.setAction(WidgetProvider.DATA_FETCHED);
        widgetUpdateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        sendBroadcast(widgetUpdateIntent);
    }

    // endregion
}
