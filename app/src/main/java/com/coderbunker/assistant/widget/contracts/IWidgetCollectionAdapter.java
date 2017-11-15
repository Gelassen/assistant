package com.coderbunker.assistant.widget.contracts;


import android.widget.RemoteViewsService;

import com.coderbunker.assistant.currency.model.Currency;

public interface IWidgetCollectionAdapter extends RemoteViewsService.RemoteViewsFactory {
    void updateDataSet(Currency currency);
}
