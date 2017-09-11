package com.coderbunker.assistant;

import com.coderbunker.assistant.currency.CurrencyProvider;
import com.coderbunker.assistant.currency.CurrencyService;
import com.coderbunker.assistant.utils.MockWebServerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

public class CurrencyProviderTest extends BaseTest {

    @Rule
    MockWebServerRule mockWebServerRule;

    private CurrencyService service;

    @Before
    public void setUp() throws Exception {
        service = new CurrencyProvider((App) RuntimeEnvironment.application);
    }

    @Test
    public void testCurrency() {
        // TODO you have to add query to request line in mock server dispatcher
        // TODO process data inside
        service.getCurrency("USD");
    }
}
