package com.st.lru;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU_DependOnJDK {

    private class MyLRUClass<K,V> extends LinkedHashMap<K,V>{
        static final float DEFAULT_LOAD_FACTOR = 0.75f;
        private int capacity;

        public MyLRUClass(int capacity) {
            super(capacity, DEFAULT_LOAD_FACTOR, true);
            this.capacity = capacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return super.size()>capacity;
        }
    }

    @Test
    public  void main() {
        LRU_DependOnJDK.MyLRUClass<Integer,String> map=new LRU_DependOnJDK.MyLRUClass<Integer,String>(3);
        map.put(1,"a");
        map.put(2,"b");
        map.put(3,"c");
        System.out.println(map.keySet());
        map.put(4,"d");
        map.put(3,"d");
        System.out.println(map.keySet());
    }
}
