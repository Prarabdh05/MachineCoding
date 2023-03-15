package com.prarabdhgupta.cache;


import com.prarabdhgupta.cache.exceptions.NotFoundException;
import com.prarabdhgupta.cache.exceptions.StorageNotFoundException;
import com.prarabdhgupta.cache.policies.EvictionPolicy;
import com.prarabdhgupta.cache.storage.Storage;

public class Cache<Key,Value> {


    private final Storage<Key,Value> storage;
    private final EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }


    public void put(Key key, Value value){
        try {
            storage.add(key,value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageNotFoundException e) {
            Key keyToRemove = evictionPolicy.evictKey();
            if(keyToRemove == null){
                throw new RuntimeException("Unexpected exception. Storage full and no key to evict");
            }
            storage.remove(keyToRemove);
            put(key,value);
        }

    }

    public Value get(Key key){
        try {
           Value value = storage.get(key);
           evictionPolicy.keyAccessed(key);
           return value;
        }catch (NotFoundException e){
            throw new NotFoundException("Tried accessing non existing key");
        }
    }

}
