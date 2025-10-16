import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

class WriteOnlyMap<K, V> extends DelegateMap<K, V> {
    public WriteOnlyMap(Map<K, V> delegate) {
        super(delegate);
    }

    protected UnsupportedOperationException unsupported() {
        return new UnsupportedOperationException("This map is write-only");
    }

    // Переопределяем только методы чтения, делегируя методы модификации
    @Override
    public boolean isEmpty() {
        throw unsupported();
    }

    @Override
    public boolean containsKey(Object key) {
        throw unsupported();
    }

    @Override
    public boolean containsValue(Object value) {
        throw unsupported();
    }

    @Override
    public V get(Object key) {
        throw unsupported();
    }

    @Override
    public Set<K> keySet() {
        throw unsupported();
    }

    @Override
    public Collection<V> values() {
        throw unsupported();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw unsupported();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        throw unsupported();
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        throw unsupported();
    }

    @Override
    public boolean equals(Object o) {
        throw unsupported();
    }

    @Override
    public int hashCode() {
        throw unsupported();
    }

    @Override
    public int size() {
        throw unsupported();
    }

    // Методы модификации делегируем базовой реализации
    @Override
    public V put(K key, V value) {
        return delegate.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return delegate.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        delegate.putAll(m);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return delegate.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return delegate.remove(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return delegate.replace(key, oldValue, newValue);
    }

    @Override
    public V replace(K key, V value) {
        return delegate.replace(key, value);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return delegate.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return delegate.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return delegate.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return delegate.merge(key, value, remappingFunction);
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        delegate.replaceAll(function);
    }
}