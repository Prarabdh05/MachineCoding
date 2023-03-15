package com.prarabdhgupta.cache.policies;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LRUEvictionPolicyTest {

    private LRUEvictionPolicy<Integer> lruEvictionPolicy;

    @BeforeEach
    public void setup(){
        lruEvictionPolicy = new LRUEvictionPolicy<Integer>();
    }

    @Test
    public void nullCheckForEvictKeyTest(){
        Assertions.assertNull(lruEvictionPolicy.evictKey());
    }

    @Test
    public void keysEvictionInOrderInWhichTheyAreAccessedTest(){
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(4);
        Assertions.assertEquals(1,lruEvictionPolicy.evictKey());
        Assertions.assertEquals(2,lruEvictionPolicy.evictKey());
        Assertions.assertEquals(3,lruEvictionPolicy.evictKey());
        Assertions.assertEquals(4,lruEvictionPolicy.evictKey());

    }
}
