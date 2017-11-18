package com.coderbunker.assistant.widget;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViewsService;

import com.coderbunker.assistant.App;
import com.coderbunker.assistant.IApplication;
import com.coderbunker.assistant.currency.CurrencyProvider;
import com.coderbunker.assistant.utils.SimpleObserver;
import com.coderbunker.assistant.widget.contracts.IWidgetCollectionAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class WidgetCollectionService extends RemoteViewsService {

    public static final String EXTRA_PAYLOAD = "EXTRA_PAYLOAD";

    private CurrencyProvider currencyProvider;
    private WidgetRemoteViewsFactory widgetRemoteViewsFactory;
    private IWidgetCollectionAdapter viewsFactory;
    private Repository repository;

    private int widgetId;

    public static Intent getIntent(Context context, int appWidgetId, ArrayList<String> payload) {
        Intent intent = new Intent(context, WidgetCollectionService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        intent.putExtra(WidgetCollectionService.EXTRA_PAYLOAD, payload);
        return intent;
    }

    public static ArrayList<String> getPayload(Intent intent) {
        ArrayList<String> result = new ArrayList<>();
        if (intent != null && intent.hasExtra(EXTRA_PAYLOAD)) {
            result = intent.getStringArrayListExtra(EXTRA_PAYLOAD);
        }
        Log.d(App.TAG, "[4] assign intent to the widget with payload. Size: " + result.size());
        return result;
    }

    public WidgetCollectionService() {
        widgetRemoteViewsFactory = new WidgetRemoteViewsFactory();
        currencyProvider = new CurrencyProvider(App.getApplication());
    }

    @Inject
    public WidgetCollectionService(CurrencyProvider currencyProvider,
                                   WidgetRemoteViewsFactory widgetRemoteViewsFactory,
                                   Repository repository) {
        this.currencyProvider = currencyProvider;
        this.widgetRemoteViewsFactory = widgetRemoteViewsFactory;
        this.repository = repository;
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d(App.TAG, "onGetViewFactory");
        widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        viewsFactory = widgetRemoteViewsFactory.newInstance(this, intent, getRepository());
        Observable<ArrayList<String>> data = currencyProvider.getCurrencyBoard("CNY");
        data.subscribe(getCurrencyObserver());
        return viewsFactory;
    }

    // region private methods

    private Repository getRepository() {
        if (repository == null) {
            repository = new Repository(this);
        }
        return repository;
    }

    private Observer<ArrayList<String>> getCurrencyObserver() {
        return new SimpleObserver<ArrayList<String>>() {

            @Override
            public void onError(Throwable error) {
                super.onError(error);
                Log.e(App.TAG, "onError", error);
            }

            @Override
            public void onNext(ArrayList<String> data) {
                super.onNext(data);
                Log.d(App.TAG, "[1] get data from network. Size: " + data.size());
                repository.saveData(widgetId, data);
                notifyDataSource(data);
            }
        };
    }

    private void notifyDataSource(ArrayList<String> data) {
        Log.d(App.TAG, "[2] Send broadcast. Size: " + data.size());
        Intent widgetUpdateIntent = new Intent();
        widgetUpdateIntent.setAction(WidgetProvider.DATA_FETCHED);
        widgetUpdateIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        widgetUpdateIntent.putExtra(WidgetCollectionService.EXTRA_PAYLOAD, data);
        sendBroadcast(widgetUpdateIntent);
    }

    // endregion
}
