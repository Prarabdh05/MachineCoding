package com.prarabdhgupta.cache.policies;

import com.prarabdhgupta.algorithm.DoublyLinkedList;
import com.prarabdhgupta.algorithm.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private DoublyLinkedList<Key> dll;
    Map<Key,DoublyLinkedListNode<Key>>mapper;

    public LRUEvictionPolicy(){
        this.dll = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }


    @Override
    public void keyAccessed(Key key) {
        if(mapper.containsKey(key)){
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        }else {
            DoublyLinkedListNode<Key> doublyLinkedListNode = new DoublyLinkedListNode<>(key);
            dll.addNodeAtLast(doublyLinkedListNode);
            mapper.put(key,doublyLinkedListNode);
        }

    }

    @Override
    public Key evictKey() {
        DoublyLinkedListNode<Key> first = dll.getFirstNode();
        if(first == null){
            return null;
        }
        dll.detachNode(first);
        mapper.remove(first.getElement());
        return first.getElement();
    }
}
