package com.coderbunker.assistant.widget.contracts;


import android.widget.RemoteViewsService;

import com.coderbunker.assistant.currency.model.Currency;

import java.util.ArrayList;

public interface IWidgetCollectionAdapter extends RemoteViewsService.RemoteViewsFactory {
    void updateDataSet(ArrayList<String> currency);
}
