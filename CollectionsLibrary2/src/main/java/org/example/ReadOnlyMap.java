package org.example;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

class ReadOnlyMap<K, V> extends DelegateMap<K, V> {
    public ReadOnlyMap(Map<K, V> delegate) {
        super(delegate);
    }

    protected UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException("This map is read-only");
    }

    @Override
    public V put(K key, V value) {
        throw unsupported();
    }

    @Override
    public V remove(Object key) {
        throw unsupported();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw unsupported();
    }

    @Override
    public void clear() {
        throw unsupported();
    }

    @Override
    public V putIfAbsent(K key, V value) {
        throw unsupported();
    }

    @Override
    public boolean remove(Object key, Object value) {
        throw unsupported();
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        throw unsupported();
    }

    @Override
    public V replace(K key, V value) {
        throw unsupported();
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        throw unsupported();
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw unsupported();
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        throw unsupported();
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        throw unsupported();
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        throw unsupported();
    }
}