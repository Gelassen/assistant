package com.coderbunker.assistant.utils;

import com.coderbunker.assistant.utils.responses.CurrencyResponseBuilder;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

public class MockDispatcher extends Dispatcher {

    private static final String CURRENCY_USD = "GET /latest?base=USD";
    private static final String CURRENCY_CNY = "GET /latest?base=CNY";

    @Override
    public MockResponse dispatch(RecordedRequest recordedRequest) throws InterruptedException {
        MockResponse response = null;
        String requestLine = recordedRequest.getRequestLine();

        if (isMatch(CURRENCY_USD, requestLine)) {
            response = getCurrencyLatestResponse(CurrencyResponseBuilder.ResponseType.LATEST_USD);
        } else if (isMatch(CURRENCY_CNY, requestLine)) {
            response = getCurrencyLatestResponse(CurrencyResponseBuilder.ResponseType.LATEST_CNY);
        } else {
            response = new MockResponse().setResponseCode(404);
        }

        return response;
    }

    public MockResponse getCurrencyLatestResponse(CurrencyResponseBuilder.ResponseType responseType) {
        String body = new CurrencyResponseBuilder().getResponse(responseType);
        return new MockResponse()
                .setResponseCode(200)
                .setBody(body);
    }

    private boolean isMatch(String pattern, String request) {
        return request.contains(pattern);
    }
}
