package com.coderbunker.assistant.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.coderbunker.assistant.BaseTest;
import com.coderbunker.assistant.currency.model.Currency;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

public class WidgetRemoteViewsAdapterTest extends BaseTest {

    @Mock
    private Context context;

    @Mock
    private Repository repository;

    private WidgetRemoteViewsAdapter subject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        subject = new WidgetRemoteViewsAdapter(context, getIntent(RuntimeEnvironment.application), repository);
    }

    @Test
    public void updateDataSet_oneItems_getCountReturnsOne() {
        subject.updateDataSet("USD");

        assertEquals(1, subject.getCount());
    }

    @Test
    public void updateDataSet_threeItems_getCountReturnsThree() {
        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");

        subject.updateDataSet(data);

        assertEquals(3, subject.getCount());
    }

    @Test
    public void updateDataSet_fromThreeToOne_getCountReturnsThree() {
        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        subject.updateDataSet(data);

        subject.updateDataSet("");

        assertEquals(1, subject.getCount());
    }

    @Test
    public void getCount_onZeroItems_returnsZero() {
        int count = subject.getCount();

        assertEquals(0, count);
    }

    @Test
    public void getCount_onThreeItems_returnsThree() {
        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        subject.updateDataSet(data);

        int count = subject.getCount();

        assertEquals(3, count);
    }

    @Test
    public void getViewAt_anyPosition_returnsRemoteViews() {
        subject.updateDataSet("");

        RemoteViews views = subject.getViewAt(0);

        assertNotNull(views);
        verify(context).getPackageName();
    }

    @Test
    public void getLoadingView_returnsDefault() {
        RemoteViews loadingView = subject.getLoadingView();

        assertNull(loadingView);
    }

    @Test
    public void getViewTypeCount_returnsOne() {
        int typeCount = subject.getViewTypeCount();

        assertEquals(1, typeCount);
    }

    @Test
    @Ignore("Postpone with fix to to setup CI first")
    public void onDataSetChanged_repositoryGetData() {
        subject.onDataSetChanged();

        verify(repository).saveData(anyInt(), any(ArrayList.class));
    }

    // region private methods

    private Intent getIntent(Context context) {
        Intent intent = new Intent(context, WidgetCollectionService.class);
        intent.putExtra(WidgetCollectionService.EXTRA_PAYLOAD, getPayload());
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 10);
        return intent;
    }

    private Currency getPayload() {
        Currency currency = new Currency();
        currency.setBase("USD");
        return currency;
    }

    // endregion
}