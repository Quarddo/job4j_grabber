package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));

    }

    public V get(K key) {
        V reference = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (reference == null) {
            reference = load(key);
            put(key, load(key));
        }
        return reference;
    }

    protected abstract V load(K key);
}
