package com.coderbunker.assistant.di;


import com.coderbunker.assistant.network.NetworkService;

import javax.inject.Singleton;

@Singleton
@dagger.Component(
        modules = { NetworkModule.class }
)
public interface Component extends IComponent{
}
