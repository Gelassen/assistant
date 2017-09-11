package com.coderbunker.assistant.utils;

import org.junit.rules.ExternalResource;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

public class MockWebServerRule extends ExternalResource {

    private MockWebServer mockWebServer;

    @Override
    protected void before() throws Throwable {
        mockWebServer.start(1010);
    }

    @Override
    protected void after() {
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
