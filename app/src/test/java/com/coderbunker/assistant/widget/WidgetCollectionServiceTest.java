package com.coderbunker.assistant.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.coderbunker.assistant.BaseTest;
import com.coderbunker.assistant.currency.CurrencyProvider;
import com.coderbunker.assistant.currency.model.Currency;
import com.coderbunker.assistant.widget.contracts.IWidgetCollectionAdapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RuntimeEnvironment;

import io.reactivex.Observable;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WidgetCollectionServiceTest extends BaseTest {

    @Mock
    private WidgetRemoteViewsFactory widgetRemoteViewsFactory;

    @Mock
    private IWidgetCollectionAdapter remoteViewsAdapter;

    @Mock
    private CurrencyProvider currencyProvider;

    private WidgetCollectionService subject;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(widgetRemoteViewsFactory.newInstance(RuntimeEnvironment.application)).thenReturn(remoteViewsAdapter);

        subject = new WidgetCollectionService(currencyProvider, widgetRemoteViewsFactory);

        doAnswer(new CurrencyAnswer(new Currency()))
                .when(currencyProvider)
                .getCurrency("USD");
    }

    @Test
    public void onGetViewFactory_returnsRemoteViewsFactory() {
        Intent intent = new Intent(RuntimeEnvironment.application, WidgetCollectionService.class);
        RemoteViewsService.RemoteViewsFactory viewsFactory = subject.onGetViewFactory(intent);

        assertNotNull(viewsFactory);
    }

    @Test
    public void onGetViewFactory_callsGetCurrencies() {
        Intent intent = new Intent(RuntimeEnvironment.application, WidgetCollectionService.class);
        subject.onGetViewFactory(intent);

        verify(currencyProvider).getCurrency(anyString());
    }

    @Test
    public void onGetViewFactory_getData_callsRemoteViewsFactoryDataSetChange() {
        Intent intent = new Intent(RuntimeEnvironment.application, WidgetCollectionService.class);
        subject.onGetViewFactory(intent);

        verify(remoteViewsAdapter).updateDataSet(any(Currency.class));
    }

    // region private classes

    private class CurrencyAnswer implements Answer<Observable<Currency>> {

        private Currency reply;

        public CurrencyAnswer(Currency reply) {
            this.reply = reply;
        }

        @Override
        public Observable<Currency> answer(InvocationOnMock invocation) throws Throwable {
            return Observable.just(reply);
        }
    }

    // endregion
}