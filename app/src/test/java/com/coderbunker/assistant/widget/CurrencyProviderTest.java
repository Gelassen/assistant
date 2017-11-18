package com.coderbunker.assistant.widget;

import com.coderbunker.assistant.BaseTest;
import com.coderbunker.assistant.IApplication;
import com.coderbunker.assistant.currency.CurrencyProvider;
import com.coderbunker.assistant.currency.model.Currency;
import com.coderbunker.assistant.utils.MockWebServerRule;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.internal.util.VolatileSizeArrayList;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;

public class CurrencyProviderTest extends BaseTest {

    @Rule
    public MockWebServerRule mockWebServerRule = new MockWebServerRule();

    private CurrencyProvider service;

    @Before
    public void setUp() throws Exception {
        service = new CurrencyProvider((IApplication) RuntimeEnvironment.application);
    }

    @Test
    public void getCurrency_onUSD_getBaseReturnsUSD() {
        TestObserver testObserver = new TestObserver();

        Observable<Currency> currencyObservable = service.getCurrency("USD");
        currencyObservable.subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertComplete();

        Currency currency = (Currency) ((VolatileSizeArrayList) testObserver.getEvents().get(0)).get(0);

        Assertions.assertThat(currency.getBase()).isEqualTo("USD");
    }

    @Test
    public void getCurrency_onCNY_getBaseReturnsCNY() {
        TestObserver testObserver = new TestObserver();

        Observable<Currency> currencyObservable = service.getCurrency("CNY");
        currencyObservable.subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertComplete();

        Currency currency = (Currency) ((VolatileSizeArrayList) testObserver.getEvents().get(0)).get(0);

        Assertions.assertThat(currency.getBase()).isEqualTo("CNY");
    }

    @Test
    public void getCurrency_onCNY_getCurrencyBoardArray() {
        TestObserver testObserver = new TestObserver();

        Observable<ArrayList<String>> currencyObservable = service.getCurrencyBoard("CNY");
        currencyObservable.subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertComplete();
        ArrayList<String> result = (ArrayList<String>) ((VolatileSizeArrayList) testObserver.getEvents().get(0)).get(0);
        assertEquals(5, result.size());
    }

    @Test
    public void getCurrency_onCNY_getRatesWith() {
        TestObserver testObserver = new TestObserver();

        Observable<ArrayList<String>> currencyObservable = service.getCurrencyBoard("CNY");
        currencyObservable.subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertComplete();
        ArrayList<String> result = (ArrayList<String>) ((VolatileSizeArrayList) testObserver.getEvents().get(0)).get(0);

        assertEquals("USD", result.get(0).split("\\s+")[4]);
        assertEquals("RUB", result.get(1).split("\\s+")[4]);
        assertEquals("EUR", result.get(2).split("\\s+")[4]);
        assertEquals("HKD", result.get(3).split("\\s+")[4]);
        assertEquals("SGD", result.get(4).split("\\s+")[4]);
    }
}
