package com.coderbunker.assistant.widget.old;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.coderbunker.assistant.App;
import com.coderbunker.assistant.R;

public class OldWidgetProvider extends AppWidgetProvider {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(App.TAG, "onReceive");

        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = updateRemoteViews(context, appWidgetId);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(App.TAG, "onUpdate");
        // TODO go through all widget items and update each widget
        // TODO assign click action for each item
        Log.d(App.TAG, "=======================");
        for (int appWidgetId : appWidgetIds) {
            Intent svcIntent = new Intent(context, WidgetCollectionService.class);
            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
            //setting adapter to listview of the widget
            RemoteViews remoteViews = new RemoteViews(
                    context.getApplicationContext().getPackageName(),
                    R.layout.widget_simple);
//            remoteViews.setRemoteAdapter(appWidgetId, svcIntent);
            remoteViews.setRemoteAdapter(appWidgetId, R.id.list, svcIntent);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    private RemoteViews updateRemoteViews(Context context, int appWidgetId) {
        Intent svcIntent = new Intent(context, WidgetCollectionService.class);
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        //setting adapter to listview of the widget
        RemoteViews remoteViews = new RemoteViews(
                context.getApplicationContext().getPackageName(),
                R.layout.widget_simple);
//            remoteViews.setRemoteAdapter(appWidgetId, svcIntent);
        remoteViews.setRemoteAdapter(appWidgetId, R.id.list, svcIntent);
        return remoteViews;
    }
}
