package com.prarabdhgupta.cache.storage;


import com.prarabdhgupta.cache.exceptions.NotFoundException;
import com.prarabdhgupta.cache.exceptions.StorageNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key, Value> implements Storage<Key, Value> {

    Map<Key,Value> storage;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
    }

    @Override
    public void add(Key key, Value value) {
        if(storage.size()==capacity){
            throw new StorageNotFoundException("Capacity full....");
        }
        storage.put(key,value);
    }

    @Override
    public Value get(Key key) {
        if(!storage.containsKey(key)){
            throw new NotFoundException(key + " Not found in cache");
        }
        return storage.get(key);
    }

    @Override
    public void remove(Key key) {
        if(!storage.containsKey(key)){
            throw new NotFoundException(key + " Not found in cache");
        }
        storage.remove(key);
    }
}
