package com.st.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyLRU2<K,V> {
    private int capacity;
    private LRUImpl lru;
    private Map<K,Node> map;

    public MyLRU2(int capacity) {
        this.capacity = capacity;
        lru=new LRUImpl();
        map=new HashMap<>(capacity);
    }

    public void addHead(Node node){
        lru.head.next.prev=node;
        node.next=lru.head.next;
        lru.head.next=node;
        node.prev=lru.head;
    }
    public void removeNode(Node node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public void put(K key,V value){
        if(map.containsKey(key)){
            Node node=map.get(key);
            map.remove(key);
            removeNode(node);
        }else  if(map.size()==capacity){
            Node node=lru.tail.prev;
            K k=node.key;
            map.remove(k);
            removeNode(node);
        }
        Node newNode=new Node(key,value);
        addHead(newNode);
        map.put(key,newNode);

    }

    public Set<K> keySet(){
        return map.keySet();
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
        public LRUImpl() {
            this.head=new Node();
            this.tail=new Node();
            head.next=tail;
            tail.prev=head;
        }
    }






    public static void main(String args[]) {
        MyLRU2<Integer,String> map=new MyLRU2<>(3);
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        System.out.println(map.keySet());
        map.put(4,"d");
        System.out.println(map.keySet());
        map.put(5,"d");
        System.out.println(map.keySet());
        map.put(4,"d");
        System.out.println(map.keySet());
        map.put(7,"d");
        System.out.println(map.keySet());
        map.put(8,"d");
        System.out.println(map.keySet());
    }
}
