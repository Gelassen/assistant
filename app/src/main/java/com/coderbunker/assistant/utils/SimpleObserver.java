package com.coderbunker.assistant.utils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class SimpleObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        // no op
    }

    @Override
    public void onNext(T data) {
        // no op
    }

    @Override
    public void onError(Throwable error) {
        // no op
    }

    @Override
    public void onComplete() {
        // no op
    }
}
