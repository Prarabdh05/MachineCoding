package com.prarabdhgupta.cache.algorithm;

public class DoublyLinkedList<E> {

    DoublyLinkedListNode<E> head;
    DoublyLinkedListNode<E> tail;

    public DoublyLinkedList(){
        head = new DoublyLinkedListNode<>(null);
        tail = new DoublyLinkedListNode<>(null);
        head.next = tail;
        tail.prev = head;
    }

    // KeyAccessed -> detach node -> add Node at last
    public void detachNode(DoublyLinkedListNode<E> node){
        if(node!=null){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void addNodeAtLast(DoublyLinkedListNode<E> node){
         DoublyLinkedListNode<E> temp = tail.prev;
         temp.next = node;
         node.next = tail;
         node.prev = tail.prev;
         tail.prev = node;
    }

    public DoublyLinkedListNode<E> getFirstNode(){
        if(head.next == tail){
            return null;
        }
        return head.next;
    }
}
