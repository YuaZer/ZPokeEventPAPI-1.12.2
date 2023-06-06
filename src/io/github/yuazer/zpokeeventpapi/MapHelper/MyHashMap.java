package io.github.yuazer.zpokeeventpapi.MapHelper;

import java.util.HashMap;

public class MyHashMap<K, String, Integer> {
    private HashMap<K, Pair<String, Integer>> map;

    public MyHashMap() {
        map = new HashMap<>();
    }

    public void put(K key, String value1, Integer value2) {
        map.put(key, new Pair<>(value1, value2));
    }

    public Pair<String, Integer> get(K key) {
        return map.get(key);
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public void remove(K key) {
        map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public static class Pair<String, Integer> {
        private String value1;
        private Integer value2;

        public Pair(String value1, Integer value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public String getValue1() {
            return value1;
        }

        public Integer getValue2() {
            return value2;
        }
    }
}
