package org.example;

import java.util.Collections;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public final class CollectionUtils {
    private CollectionUtils(){
        throw new AssertionError("Нельзя создавать экземпляры утилитного класса");
    }
    public static<T> List<T> emptyList(){
        return Collections.emptyList();
    }
    public static<T> Set<T> emptySet(){
        return Collections.emptySet();
    }
    public static <K, V> Map<K, V> emptyMap() {
        return Collections.emptyMap();
    }
    public static<T> Queue<T> emptyQueue() {
        return new AbstractQueue<T>() {
            @Override
            public Iterator<T> iterator() {
                return Collections.emptyIterator();
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean offer(T t) {
                return false;
            }

            @Override
            public T poll() {
                return null;
            }

            @Override
            public T peek() {
                return null;
            }
        };
    }
    public static<T> List<T> singletonList(T element){
        return Collections.singletonList(element);
    }
    public static<T> Set<T> singletonSet(T element){
        return Collections.singleton(element);
    }
    public static <K, V> Map<K, V> singletonMap(K key, V value) {
        return Collections.singletonMap(key, value);
    }
    public static<T> Queue<T> singletonQueue(T element) {
        return singletonQueue(element);
    }
    public static<T> Set<T> readOnlySet(T... elements){
        return Set.of(elements);
    }
    public static<T> List<T> readOnlyList(T... elements){
        return List.of(elements);
    }
    public static <K, V> Map<K, V> readOnlyMap(K[]keys, V[]values) {
        if (keys.length != values.length) {
            throw new IllegalArgumentException("Количество ключей и значений должно совпадать");
        }

        Map<K, V> map = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return Map.copyOf(map);
    }
    public static <E> ReadOnlyList<E> readOnlyList2(List<E> list) {
        return new ReadOnlyList<>(list);
    }
    public static <E> WriteOnlyList<E> writeOnlyList(List<E> list) {
        return new WriteOnlyList<>(list);
    }
    public static <E> ReadOnlySet<E> readOnlySet2(Set<E> set) {
        return new ReadOnlySet<>(set);
    }
    public static <E> WriteOnlySet<E> writeOnlySet2(Set<E> set) {
        return new WriteOnlySet<>(set);
    }
    public static<K, V> ReadOnlyMap<K,V> readOnlyMap2(Map<K, V> map){
        return new ReadOnlyMap<>(map);
    }
    public static<K, V> WriteOnlyMap<K,V> writeOnlyMap2(Map<K, V> map){
        return new WriteOnlyMap<>(map);
    }
    public static <E> ReadOnlyQueue<E> readOnlyQueue(Queue<E> queue) {
        return new ReadOnlyQueue<>(queue);
    }
    public static <E> WriteOnlyQueue<E> writeOnlyQueue(Queue<E> queue) {
        return new WriteOnlyQueue<>(queue);
    }

        public static <T extends Comparable<? super T>> List<T> sortList(List<T> list) {
            List<T> sorted = new ArrayList<>(list);
            Collections.sort(sorted);
            return sorted;
        }

    public static <E> Set<E> sortSet(
            Set<E> set,
            Comparator<? super E> comparator
    ) {
        return set.stream()
                .sorted(comparator)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public static <E extends Comparable<? super E>> Set<E> sortSet(Set<E> set) {
        return set.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }




    public static <K, V> Map<K, V> sortByValue(
            Map<K, V> map,
            Comparator<? super V> valueComparator
    ) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(valueComparator))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new // preserves order
                ));
    }

    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public static <T> T findFirst(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public static <T> List<T> findAll(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }


    public static <K, V> K findFirstKey(Map<K, V> map, Predicate<K> predicate) {
        return map.keySet().stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }


    public static <K, V> V findFirstValue(Map<K, V> map, Predicate<V> predicate) {
        return map.values().stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }


    public static <K, V> Map.Entry<K, V> findFirstEntry(Map<K, V> map,
                                                        Predicate<Map.Entry<K, V>> predicate) {
        return map.entrySet().stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }


    public static <K, V> List<K> findAllKeys(Map<K, V> map, Predicate<K> predicate) {
        return map.keySet().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }


    public static <K, V> List<V> findAllValues(Map<K, V> map, Predicate<V> predicate) {
        return map.values().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }


    public static <K, V> List<Map.Entry<K, V>> findAllEntries(Map<K, V> map,
                                                              Predicate<Map.Entry<K, V>> predicate) {
        return map.entrySet().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}



