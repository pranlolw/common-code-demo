package com.st.lru;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LRU {
    private class Node<K,V>{
        private K key;
        private V value;
        private Node prev;
        private Node next;

        public Node() {
        }

        public <V> Node(Object key, V value) {
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
    private class MyLRUClass<K,V>{
        private Node head;
        private Node tail;
        private int capacity;
        private Map<K,Node<K,V>> map;

        public MyLRUClass(int capacity) {
            this.capacity = capacity;
            this.head=new Node();
            this.tail=new Node();
            head.next=tail;
            tail.prev=head;
            map=new HashMap<>(capacity);
        }

        public void removeNode(Node node){
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }

        public void removeKey(Object key){
            if(map.containsKey(key)){
                Node rNode=map.get(key);
                removeNode(rNode);
                map.remove(key);
            }
        }
        public void addNode(Node node){
            tail.prev.next=node;
            node.prev=tail.prev;
            node.next=tail;
            tail.prev=node;
        }
        public void put(Object key,V value){
            if(map.containsKey(key)){
                map.get(key).value= value;
            }else if(map.size()==capacity){//移除最后一个
                Node rnode=tail.prev;
                removeKey(rnode.key);
                Node node=new Node(key,value);
                addNode(node);
                map.put((K) key,node);
            }else{
                Node node=new Node(key,value);
                addNode(node);
                map.put((K) key,node);
            }
        }
        public Object get(Object key){
            if(map.containsKey(key)){
                Node getNode=map.get(key);
                removeNode(getNode);
                addNode(getNode);
                map.put((K) key,getNode);
                return getNode.getValue();

            }
            return -1;
        }
        public Set<K> keySet(){
            return map.keySet();
        }
    }
    @Test
    public  void main() {
        LRU.MyLRUClass<Integer,String> map=new LRU.MyLRUClass<Integer,String>(3);
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        System.out.println(map.keySet());
        map.put(4,"d");
        map.put(3,"d");
        System.out.println(map.keySet());
    }
}
