package com.coderbunker.assistant.utils.responses;

import com.coderbunker.assistant.utils.ResourceUtils;

public class CurrencyResponseBuilder implements IResponseBuilder {

    @Override
    public String getDefault() {
        return getResponse(ResponseType.LATEST_USD);
    }

    @Override
    public String getResponse(ResponseType responseType) {
        ResourceUtils utils = new ResourceUtils();
        return utils.readString(responseType.getPath());
    }

    public enum ResponseType {
        LATEST_USD("currency/currency_latest_usd"),
        LATEST_CNY("currency/currency_latest_cny");

        private String path;

        ResponseType(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }
}
