package com.coderbunker.assistant.utils;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class TestObservable<T> extends Observable<T> {

    @Override
    protected void subscribeActual(Observer<? super T> observer) {

    }

    private static class TestObserver<T> implements Observer<T> {

        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(@NonNull T t) {

        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
