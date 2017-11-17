package com.coderbunker.assistant.widget;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.coderbunker.assistant.App;
import com.coderbunker.assistant.R;
import com.coderbunker.assistant.widget.contracts.IWidgetCollectionAdapter;

import java.util.ArrayList;
import java.util.Collections;

import static com.coderbunker.assistant.widget.WidgetProvider.DATA_FETCHED;

public class WidgetRemoteViewsAdapter implements IWidgetCollectionAdapter {

    private ArrayList<String> data = new ArrayList<>();
    private Repository repository;
    private Context context;

    public WidgetRemoteViewsAdapter(Context context, Intent intent, Repository repository) {
        this.context = context;
        this.repository = repository;
        this.data.add("FAKE ROW");

        Log.d(App.TAG, "[5] update adapter. Try to extract data");

        if (intent.hasExtra(DATA_FETCHED)) {
            Log.d(App.TAG, "Update data from intent");
            intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);
            this.data.add(intent.getStringExtra(DATA_FETCHED));
            Log.d(App.TAG, "[6] data is extracted");
        }
    }

    public void updateDataSet(String data) {
        updateDataSet(new ArrayList<>(Collections.singletonList(data)));
    }

    @Override
    public void onCreate() {
        // no op
    }

    @Override
    public void onDataSetChanged() {
        Log.d(App.TAG, "onDataSetChanged");
        data.clear();
        data.addAll(repository.getData());
    }

    @Override
    public void onDestroy() {
        // no op
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        views.setTextViewText(R.id.data, data.get(position));
        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public void updateDataSet(ArrayList<String> currency) {
        data.clear();
        data.addAll(currency);
    }
}
