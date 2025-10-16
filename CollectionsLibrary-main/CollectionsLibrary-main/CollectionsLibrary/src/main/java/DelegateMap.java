import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

abstract class DelegateMap<K, V> implements Map<K, V> {
    protected final Map<K, V> delegate;

    protected DelegateMap(Map<K, V> delegate) {
        this.delegate = delegate;
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return delegate.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return delegate.get(key);
    }

    @Override
    public Set<K> keySet() {
        return delegate.keySet();
    }

    @Override
    public Collection<V> values() {
        return delegate.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return delegate.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return delegate.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        delegate.forEach(action);
    }

    @Override
    public abstract V put(K key, V value);

    @Override
    public abstract V remove(Object key);

    @Override
    public abstract void putAll(Map<? extends K, ? extends V> m);

    @Override
    public abstract void clear();

    @Override
    public abstract V putIfAbsent(K key, V value);

    @Override
    public abstract boolean remove(Object key, Object value);

    @Override
    public abstract boolean replace(K key, V oldValue, V newValue);

    @Override
    public abstract V replace(K key, V value);

    @Override
    public abstract V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction);

    @Override
    public abstract V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    public abstract V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    @Override
    public abstract V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction);

    @Override
    public abstract void replaceAll(BiFunction<? super K, ? super V, ? extends V> function);
}