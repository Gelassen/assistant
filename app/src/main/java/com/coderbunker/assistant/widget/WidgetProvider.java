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
        appWidgetManager = AppWidgetManager.getInstance((Context) App.getApplication());
    }

    public WidgetProvider(AppWidgetManager appWidgetManager,
                          WidgetRemoteViewsFactory remoteViewsFactory) {
        this.appWidgetManager = appWidgetManager;
        this.remoteViewsFactory = remoteViewsFactory;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(WidgetProvider.DATA_FETCHED)) {
            int widgetId = intent.getIntExtra(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            RemoteViews views = remoteViewsFactory.newRemoteViews(context.getPackageName(), R.layout.widget_simple);
            views.setRemoteAdapter(widgetId, R.id.list, WidgetCollectionService.getIntent(context, widgetId, WidgetCollectionService.getPayload(intent)));
            appWidgetManager.updateAppWidget(widgetId, views);
            appWidgetManager.notifyAppWidgetViewDataChanged(widgetId, R.id.list);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int widgetId : appWidgetIds) {
            RemoteViews views = remoteViewsFactory.newRemoteViews(context.getPackageName(), R.layout.widget_simple);
            views.setRemoteAdapter(widgetId, R.id.list, WidgetCollectionService.getIntent(context, widgetId, new ArrayList<String>()));
            appWidgetManager.updateAppWidget(widgetId, views);
            appWidgetManager.notifyAppWidgetViewDataChanged(widgetId, R.id.list);
        }
    }

}
