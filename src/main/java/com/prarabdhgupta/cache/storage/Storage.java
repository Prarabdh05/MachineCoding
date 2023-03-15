package com.prarabdhgupta.cache.storage;

public interface Storage<Key, Value>{

    public void add(Key key, Value value);
    public Value get(Key key);
    public void remove(Key key);
}
