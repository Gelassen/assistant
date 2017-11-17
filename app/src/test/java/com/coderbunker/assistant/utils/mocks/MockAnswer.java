package com.coderbunker.assistant.utils.mocks;


import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import io.reactivex.Observable;

public abstract class MockAnswer<T> implements Answer<Observable<T>> {

    private T reply;

    public MockAnswer(T reply) {
        this.reply = reply;
    }

    @Override
    public Observable<T> answer(InvocationOnMock invocation) throws Throwable {
        return Observable.just(reply);
    }
}
