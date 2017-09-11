package com.coderbunker.assistant.utils;

import com.coderbunker.assistant.currency.CurrencyService;
import com.coderbunker.assistant.utils.responses.CurrencyResponseBuilder;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

public class MockDispatcher extends Dispatcher {

    private static final String CURRENCY_LATEST = CurrencyService.API_LATEST;

    @Override
    public MockResponse dispatch(RecordedRequest recordedRequest) throws InterruptedException {
        MockResponse response = null;
        String requestLine = recordedRequest.getRequestLine();
        switch (requestLine) {
            case CURRENCY_LATEST:
                response = getCurrencyLatestResponse();
                break;
            default:
                throw new RuntimeException("Unsupported API path");
        }
        return response;
    }

    public MockResponse getCurrencyLatestResponse() {
        return new MockResponse()
                .setResponseCode(200)
                .setBody(new CurrencyResponseBuilder().getDefault());
    }
}
