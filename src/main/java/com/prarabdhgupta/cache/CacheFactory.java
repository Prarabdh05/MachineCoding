package com.prarabdhgupta.cache;

import com.prarabdhgupta.cache.policies.LRUEvictionPolicy;
import com.prarabdhgupta.cache.storage.HashMapBasedStorage;

public class CacheFactory<Key,Value> {

    public Cache<Key,Value> defaultCache(final int capacity){
        return new Cache<>(new HashMapBasedStorage<Key,Value>(capacity),new LRUEvictionPolicy<Key>());
    }
}
