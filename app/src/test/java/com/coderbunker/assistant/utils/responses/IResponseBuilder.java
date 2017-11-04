package com.coderbunker.assistant.utils.responses;

public interface IResponseBuilder {
    String getDefault();
    String getResponse(CurrencyResponseBuilder.ResponseType responseType);
}
