package com.coderbunker.assistant.utils.responses;

import com.coderbunker.assistant.utils.ResourceUtils;

public class CurrencyResponseBuilder implements IResponseBuilder {

    @Override
    public String getDefault() {
        ResourceUtils utils = new ResourceUtils();
        return utils.readString(ResponseType.LATEST.getPath());
    }


    public enum ResponseType {
        LATEST("currency/currency_latest.json"); // TODO you might need to add one more slash

        private String path;

        ResponseType(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }
}
