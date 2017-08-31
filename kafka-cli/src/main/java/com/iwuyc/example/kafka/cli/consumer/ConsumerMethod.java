package com.iwuyc.example.kafka.cli.consumer;

public interface ConsumerMethod<T, V> {
    public void consume(V val);

    public boolean support(T topic);
}
