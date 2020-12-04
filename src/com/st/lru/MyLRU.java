package com.st.lru;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyLRU<K,V> {
    private int capacity;
    private LRUImpl lru;

    public MyLRU() {
    }

    public MyLRU(int capacity) {
        this.capacity = capacity;
        lru=new LRUImpl();
    }

    public Set<K> keySet(){
        return lru.map.keySet();
    }

    private class Node{
        private K key;
        private V value;
        private Node prev;
        private Node next;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    private class LRUImpl{
        private Node head;
        private Node tail;
        private Map<K,Node> map;

        public LRUImpl() {
            this.head=new Node();
            this.tail=new Node();
            map=new HashMap<>(capacity);
            head.next=tail;
            tail.prev=head;
        }
    }
    public void put(K key,V value){
        if(lru.map.containsKey(key)){
            removeKey(key);
        }
        if(lru.map.size()==capacity){//移除最后一个
            K rkey=lru.head.next.key;
            removeKey(rkey);
        }
        Node node=new Node(key,value);
        node.prev=lru.tail.prev;
        node.prev.next=node;
        node.next=lru.tail;
        lru.tail.prev=node;
        lru.map.put(key,node);
    }
    public V get(K key){
        if(lru.map.containsKey(key)){
            V value=lru.map.get(key).value;
            put(key,value);
            return value;
        }
        return null;
    }

    public void removeKey(K key){
        if(lru.map.containsKey(key)){
            Node node=lru.map.get(key);
            node.prev.next=node.next;
            node.next.prev=node.prev;
            lru.map.remove(key);
        }
    }


    public static void main(String args[]) {
        MyLRU<Integer,String> map=new MyLRU<>(3);
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        System.out.println(map.keySet());
        map.put(4,"d");
        System.out.println(map.keySet());
        map.put(5,"d");
        map.put(4,"d");
        map.put(7,"d");
        map.put(8,"d");
        System.out.println(map.keySet());
        int[] a=new int[]{};

    }
}
