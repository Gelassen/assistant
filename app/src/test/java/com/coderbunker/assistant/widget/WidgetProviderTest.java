package com.coderbunker.assistant.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.coderbunker.assistant.BaseTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WidgetProviderTest extends BaseTest {

    @Mock
    private AppWidgetManager appWidgetManager;

    @Mock
    private WidgetRemoteViewsFactory widgetRemoteViewsFactory;

    @Mock
    private RemoteViews remoteViews;

    private WidgetProvider subject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(widgetRemoteViewsFactory.newRemoteViews(anyString(), anyInt())).thenReturn(remoteViews);

        subject = new WidgetProvider(appWidgetManager, widgetRemoteViewsFactory);
    }

    @Test
    public void onReceive_updateAppWidget() {
        subject.onReceive(RuntimeEnvironment.application, getReceiveIntent());

        verify(appWidgetManager).updateAppWidget(anyInt(), any(RemoteViews.class));
    }

    @Test
    public void onReceive_notifyAppWidget() {
        subject.onReceive(RuntimeEnvironment.application, getReceiveIntent());

        verify(appWidgetManager).notifyAppWidgetViewDataChanged(anyInt(), anyInt());
    }

    @Test
    public void onReceive_callsRemoteViewsSetAdapter() {
        subject.onReceive(RuntimeEnvironment.application, getProviderIntent());

        verify(remoteViews).setRemoteAdapter(anyInt(), anyInt(), any(Intent.class));
    }

    @Test
    public void onUpdate_updateAppWidget() {
        int[] ids = new int[] { 10 };
        subject.onUpdate(RuntimeEnvironment.application, appWidgetManager, ids);

        verify(appWidgetManager).updateAppWidget(anyInt(), any(RemoteViews.class));
    }

    @Test
    public void onUpdate_notifyAppWidget() {
        int[] ids = new int[] { 10 };
        subject.onUpdate(RuntimeEnvironment.application, appWidgetManager, ids);

        verify(appWidgetManager).notifyAppWidgetViewDataChanged(anyInt(), anyInt());
    }

    @Test
    public void onUpdate_callsRemoteViewsSetAdapter() {
        int[] ids = new int[] { 10 };
        subject.onUpdate(RuntimeEnvironment.application, appWidgetManager, ids);

        verify(remoteViews).setRemoteAdapter(anyInt(), anyInt(), any(Intent.class));
    }



    // region private methods

    private Intent getReceiveIntent() {
        Intent intent = new Intent(RuntimeEnvironment.application, WidgetProvider.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        return intent;
    }

    private Intent getProviderIntent() {
        Intent intent = new Intent(RuntimeEnvironment.application, WidgetCollectionService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        return intent;
    }

    // endregion
}