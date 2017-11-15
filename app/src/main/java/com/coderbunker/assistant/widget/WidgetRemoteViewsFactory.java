package com.coderbunker.assistant.widget;


import android.content.Context;
import android.widget.RemoteViews;

import com.coderbunker.assistant.widget.contracts.IWidgetCollectionAdapter;

public class WidgetRemoteViewsFactory {

    public IWidgetCollectionAdapter newInstance(Context context) {
        return new WidgetRemoteViewsAdapter(context);
    }

    public RemoteViews newRemoteViews(String packageName, final int viewId) {
        return new RemoteViews(packageName, viewId);
    }
}
