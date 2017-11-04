package com.coderbunker.assistant.di;


import com.coderbunker.assistant.network.NetworkService;

public interface IComponent {
    void inject(NetworkService inject);
}
