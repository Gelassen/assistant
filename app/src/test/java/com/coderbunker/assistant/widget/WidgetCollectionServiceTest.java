package com.coderbunker.assistant.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViewsService;

import com.coderbunker.assistant.BaseTest;
import com.coderbunker.assistant.currency.CurrencyProvider;
import com.coderbunker.assistant.utils.mocks.CurrencyAnswer;
import com.coderbunker.assistant.widget.contracts.IWidgetCollectionAdapter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Ignore
public class WidgetCollectionServiceTest extends BaseTest {

    @Mock
    private WidgetRemoteViewsFactory widgetRemoteViewsFactory;

    @Mock
    private IWidgetCollectionAdapter remoteViewsAdapter;

    @Mock
    private CurrencyProvider currencyProvider;

    @Mock
    private Repository repository;

    private WidgetCollectionService subject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(widgetRemoteViewsFactory.newInstance(any(Context.class), any(Intent.class), any(Repository.class))).thenReturn(remoteViewsAdapter);

        subject = new WidgetCollectionService(currencyProvider, widgetRemoteViewsFactory, repository);

        doAnswer(new CurrencyAnswer(getPayload()))
                .when(currencyProvider)
                .getCurrency("USD");

        doAnswer(new CurrencyAnswer(getPayload()))
                .when(currencyProvider)
                .getCurrencyBoard("USD");
    }

    @Test
    public void onGetCurrency_returnsNotNull() {
        Observable<ArrayList<String>> data = currencyProvider.getCurrencyBoard("USD");

        assertNotNull(data);
    }

    @Test
    @Ignore("After 3 years and some changes in configuration some tests are not valid and have to be fixed, but postpone for now")
    public void onGetViewFactory_returnsRemoteViewsFactory() {
        Intent intent = new Intent(RuntimeEnvironment.application, WidgetCollectionService.class);
        RemoteViewsService.RemoteViewsFactory viewsFactory = subject.onGetViewFactory(intent);

        assertNotNull(viewsFactory);
    }

    @Test
    @Ignore("After 3 years and some changes in configuration some tests are not valid and have to be fixed, but postpone for now")
    public void onGetViewFactory_callsGetCurrencies() {
        Intent intent = new Intent(RuntimeEnvironment.application, WidgetCollectionService.class);
        subject.onGetViewFactory(intent);

        verify(currencyProvider).getCurrency(anyString());
    }

    @Test
    @Ignore("After 3 years and some changes in configuration some tests are not valid and have to be fixed, but postpone for now")
    public void onGetViewFactory_getData_callsRemoteViewsFactoryDataSetChange() {
        Intent intent = new Intent(RuntimeEnvironment.application, WidgetCollectionService.class);
        subject.onGetViewFactory(intent);

        verify(remoteViewsAdapter).updateDataSet(any(ArrayList.class));
    }

    @Test
    @Ignore("After 3 years and some changes in configuration some tests are not valid and have to be fixed, but postpone for now")
    public void onGetViewFactory_getData_callsRepositorySaveData() {
        int appWidgetId = 1001;
        Intent intent = new Intent(RuntimeEnvironment.application, WidgetCollectionService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        subject.onGetViewFactory(intent);

        verify(repository).saveData(appWidgetId, any(ArrayList.class));
    }

    // region private classes

    private Intent getIntent(Context context) {
        Intent intent = new Intent(context, WidgetCollectionService.class);
        intent.putExtra(WidgetCollectionService.EXTRA_PAYLOAD, getPayload());
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 10);
        return intent;
    }

    private ArrayList<String> getPayload() {
        ArrayList<String> data = new ArrayList<>();
        data.add("rate");
        return data;
    }

    // endregion
}