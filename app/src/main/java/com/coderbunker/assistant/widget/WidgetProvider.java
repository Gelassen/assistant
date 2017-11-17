package com.coderbunker.assistant.widget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.coderbunker.assistant.App;
import com.coderbunker.assistant.R;
import com.coderbunker.assistant.currency.model.Currency;

import java.util.ArrayList;

import javax.inject.Inject;

public class WidgetProvider extends AppWidgetProvider {

    public static final String DATA_FETCHED = "com.coderbunker.assistant.widget.DATA_FETCHED";

    @Inject
    AppWidgetManager appWidgetManager;

    @Inject
    WidgetRemoteViewsFactory remoteViewsFactory;

    public WidgetProvider() {
        remoteViewsFactory = new WidgetRemoteViewsFactory();
    }

    public WidgetProvider(AppWidgetManager appWidgetManager,
                          WidgetRemoteViewsFactory remoteViewsFactory) {
        this.appWidgetManager = appWidgetManager;
        this.remoteViewsFactory = remoteViewsFactory;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(App.TAG, "[3] receive broadcast");

        int widgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        RemoteViews views = remoteViewsFactory.newRemoteViews(context.getPackageName(), R.layout.widget_simple);
        views.setRemoteAdapter(widgetId, R.id.list, WidgetCollectionService.getIntent(context, widgetId, WidgetCollectionService.getPayload(intent)));
        getAppWidgetManager(context).updateAppWidget(widgetId, views);
        getAppWidgetManager(context).notifyAppWidgetViewDataChanged(widgetId, R.id.list);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int widgetId : appWidgetIds) {
            RemoteViews views = remoteViewsFactory.newRemoteViews(context.getPackageName(), R.layout.widget_simple);
            views.setRemoteAdapter(widgetId, R.id.list, WidgetCollectionService.getIntent(context, widgetId, new ArrayList<String>()));
            getAppWidgetManager(context).updateAppWidget(widgetId, views);
            getAppWidgetManager(context).notifyAppWidgetViewDataChanged(widgetId, R.id.list);
        }
    }

    // region private methods

    private AppWidgetManager getAppWidgetManager(Context context) {
        if (appWidgetManager == null) {
            appWidgetManager = AppWidgetManager.getInstance(context);
        }
        return appWidgetManager;
    }

    // endregion
}
