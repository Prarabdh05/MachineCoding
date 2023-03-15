package com.prarabdhgupta.cache;

import com.prarabdhgupta.cache.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CacheTest {

    Cache<Integer, Integer> cache;

    @BeforeEach
    public void setup(){
        cache = new CacheFactory<Integer,Integer>().defaultCache(3);
    }


    @Test
    public void getAndAddItemInCache(){
        cache.put(1,1);
        cache.put(2,2);
        Assertions.assertEquals(1,cache.get(1));
        cache.put(3,3);
        Assertions.assertEquals(3,cache.get(3));
        cache.put(4,4);
        Assertions.assertThrows(NotFoundException.class, ()->{
            cache.get(2);
        });
    }
}
