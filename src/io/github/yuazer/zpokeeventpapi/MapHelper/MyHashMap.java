package io.github.yuazer.zpokeeventpapi.MapHelper;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap {
    private Map<Pair<String, String>, Integer> map;

    public MyHashMap() {
        map = new HashMap<>();
    }

    public void put(String firstString, String secondString, Integer integerValue) {
        Pair<String, String> key = new Pair<>(firstString, secondString);
        map.put(key, integerValue);
    }

    public Integer get(String firstString, String secondString) {
        Pair<String, String> key = new Pair<>(firstString, secondString);
        return map.get(key);
    }

    public boolean containsKey(String firstString, String secondString) {
        Pair<String, String> key = new Pair<>(firstString, secondString);
        return map.containsKey(key);
    }

    public void remove(String firstString, String secondString) {
        Pair<String, String> key = new Pair<>(firstString, secondString);
        map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    private static class Pair<T1, T2> {
        private T1 first;
        private T2 second;

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

        public T1 getFirst() {
            return first;
        }

        public T2 getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return first.equals(pair.first) && second.equals(pair.second);
        }

        @Override
        public int hashCode() {
            return 31 * first.hashCode() + second.hashCode();
        }
    }
}


