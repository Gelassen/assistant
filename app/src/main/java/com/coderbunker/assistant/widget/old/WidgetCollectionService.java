package com.coderbunker.assistant.widget.old;


import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.coderbunker.assistant.App;
import com.coderbunker.assistant.R;

public class WidgetCollectionService extends RemoteViewsService {

    public static final String[] DATA = new String[] {
            "Apple", "Avocado", "Banana",
            "Blueberry", "Coconut", "Durian",
            "Guava", "Kiwifruit", "Jackfruit",
            "Mango", "Olive", "Pear",
            "Sugar-apple" };

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetCollectionFactory();
    }

    public class WidgetCollectionFactory implements WidgetCollectionService.RemoteViewsFactory {

        @Override
        public void onCreate() {
            Log.d(App.TAG, "onCreate");
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
            return DATA.length;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            Log.d(App.TAG, "Position: " + position);
            RemoteViews views;
            if (position % 2 == 0) {
                views = new RemoteViews(getPackageName(), R.layout.widget_item);
                views.setTextViewText(R.id.widget_item, DATA[position]);
            } else {
                views = new RemoteViews(getPackageName(), R.layout.widget_item);
                views.setTextViewText(R.id.widget_item, DATA[position]);

            }
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return new RemoteViews(getPackageName(), R.layout.widget_item_secondary);
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
