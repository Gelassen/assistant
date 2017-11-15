package com.coderbunker.assistant.widget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.coderbunker.assistant.R;

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

        int widgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        RemoteViews views = remoteViewsFactory.newRemoteViews(context.getPackageName(), R.layout.widget_simple);
        views.setRemoteAdapter(widgetId, R.id.list, getIntent(context, widgetId));
        getAppWidgetManager(context).updateAppWidget(widgetId, views);
        getAppWidgetManager(context).notifyAppWidgetViewDataChanged(widgetId, R.id.list);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int widgetId : appWidgetIds) {
            RemoteViews views = remoteViewsFactory.newRemoteViews(context.getPackageName(), R.layout.widget_simple);
            views.setRemoteAdapter(widgetId, R.id.list, getIntent(context, widgetId));
            getAppWidgetManager(context).updateAppWidget(widgetId, views);
            getAppWidgetManager(context).notifyAppWidgetViewDataChanged(widgetId, R.id.list);
        }
    }

    private AppWidgetManager getAppWidgetManager(Context context) {
        if (appWidgetManager == null) {
            appWidgetManager = AppWidgetManager.getInstance(context);
        }
        return appWidgetManager;
    }

    private Intent getIntent(Context context, int appWidgetId) {
        Intent intent = new Intent(context, WidgetCollectionService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        return intent;
    }
}
