package com.coderbunker.assistant.di;


import com.coderbunker.assistant.network.NetworkService;

import javax.inject.Singleton;

@Singleton
@dagger.Component(
        modules = { MockNetworkModule.class }
)
public interface MockComponent extends Component {
    void inject(NetworkService inject);
}
