package com.coderbunker.assistant.widget;

import android.content.Context;
import android.widget.RemoteViews;

import com.coderbunker.assistant.BaseTest;
import com.coderbunker.assistant.currency.model.Currency;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class WidgetRemoteViewsAdapterTest extends BaseTest {

    @Mock
    private Context context;

    private WidgetRemoteViewsAdapter subject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        subject = new WidgetRemoteViewsAdapter(context);
    }

    @Test
    public void updateDataSet_oneItems_getCountReturnsOne() {
        subject.updateDataSet(new Currency());

        assertEquals(1, subject.getCount());
    }

    @Test
    public void updateDataSet_threeItems_getCountReturnsThree() {
        List<Currency> data = new ArrayList<>();
        data.add(new Currency());
        data.add(new Currency());
        data.add(new Currency());

        subject.updateDataSet(data);

        assertEquals(3, subject.getCount());
    }

    @Test
    public void updateDataSet_fromThreeToOne_getCountReturnsThree() {
        List<Currency> data = new ArrayList<>();
        data.add(new Currency());
        data.add(new Currency());
        data.add(new Currency());
        subject.updateDataSet(data);

        subject.updateDataSet(new Currency());

        assertEquals(1, subject.getCount());
    }

    @Test
    public void getCount_onZeroItems_returnsZero() {
        int count = subject.getCount();

        assertEquals(0, count);
    }

    @Test
    public void getCount_onThreeItems_returnsThree() {
        List<Currency> data = new ArrayList<>();
        data.add(new Currency());
        data.add(new Currency());
        data.add(new Currency());
        subject.updateDataSet(data);

        int count = subject.getCount();

        assertEquals(3, count);
    }

    @Test
    public void getViewAt_anyPosition_returnsRemoteViews() {
        subject.updateDataSet(new Currency());

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
}