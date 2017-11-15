package com.coderbunker.assistant.widget;


import android.content.Context;
import android.widget.RemoteViews;

import com.coderbunker.assistant.R;
import com.coderbunker.assistant.currency.model.Currency;
import com.coderbunker.assistant.widget.contracts.IWidgetCollectionAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WidgetRemoteViewsAdapter implements IWidgetCollectionAdapter {

    private List<Currency> data = new ArrayList<>();
    private Context context;

    public WidgetRemoteViewsAdapter(Context context) {
        this.context = context;

        Currency currency = new Currency();
        currency.setBase("100");
        this.data.add(currency);
    }

    public void updateDataSet(Currency currency) {
        updateDataSet(Collections.singletonList(currency));
    }

    public void updateDataSet(List<Currency> currency) {
        data.clear();
        data.addAll(currency);
    }

    @Override
    public void onCreate() {
        // no op
    }

    @Override
    public void onDataSetChanged() {
        // no op
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
        views.setTextViewText(R.id.data, data.get(position).getBase());
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
}
