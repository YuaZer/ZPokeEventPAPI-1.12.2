package io.github.yuazer.zpokeeventpapi.MapHelper;

import io.github.yuazer.zpokeeventpapi.Main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MyHashMap {
    private Map<Pair<String, String>, Integer> map;

    public MyHashMap() {
        map = new HashMap<>();
    }

    public void put(String playerName, String eventName, Integer integerValue) {
        Pair<String, String> key = new Pair<>(playerName, eventName);
        map.put(key, integerValue);
        if (Main.isSqliteMode()) {
            try {
                Main.getDatabase().saveEntry(key.getFirst(), key.getSecond(), integerValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Integer get(String playerName, String eventName) {
        try {
            Pair<String, String> key = new Pair<>(playerName, eventName);
            if (Main.isSqliteMode()) {
                return Main.getDatabase().getEntry(key.getFirst(), key.getSecond());
            }
            return map.get(key);
        } catch (NullPointerException | SQLException e) {
            return -1;
        }
    }

    public boolean containsKey(String playerName, String eventName) {
        Pair<String, String> key = new Pair<>(playerName, eventName);
        return map.containsKey(key);
    }

    public void remove(String playerName, String eventName) {
        Pair<String, String> key = new Pair<>(playerName, eventName);
        map.remove(key);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public static class Pair<T1, T2> {
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


