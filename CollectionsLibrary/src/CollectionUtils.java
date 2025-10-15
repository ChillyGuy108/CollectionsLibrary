import javax.print.DocFlavor;
import java.util.Collections;
import java.util.*;

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





}



