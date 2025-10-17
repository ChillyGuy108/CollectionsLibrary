package org.example;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtilsTest {

    @Test
    void testEmptyList() {
        List<String> emptyList = CollectionUtils.emptyList();
        assertTrue(emptyList.isEmpty());
        assertEquals(0, emptyList.size());
    }

    @Test
    void testEmptySet() {
        Set<Integer> emptySet = CollectionUtils.emptySet();
        assertTrue(emptySet.isEmpty());
        assertEquals(0, emptySet.size());
    }

    @Test
    void testEmptyMap() {
        Map<String, Integer> emptyMap = CollectionUtils.emptyMap();
        assertTrue(emptyMap.isEmpty());
        assertEquals(0, emptyMap.size());
    }

    @Test
    void testEmptyQueue() {
        Queue<Double> emptyQueue = CollectionUtils.emptyQueue();
        assertTrue(emptyQueue.isEmpty());
        assertEquals(0, emptyQueue.size());
        assertNull(emptyQueue.poll());
        assertNull(emptyQueue.peek());
        assertFalse(emptyQueue.offer(1.0));
    }

    @Test
    void testSingletonList() {
        List<String> singletonList = CollectionUtils.singletonList("test");
        assertEquals(1, singletonList.size());
        assertEquals("test", singletonList.get(0));
    }

    @Test
    void testSingletonSet() {
        Set<Integer> singletonSet = CollectionUtils.singletonSet(42);
        assertEquals(1, singletonSet.size());
        assertTrue(singletonSet.contains(42));
    }

    @Test
    void testSingletonMap() {
        Map<String, Integer> singletonMap = CollectionUtils.singletonMap("key", 100);
        assertEquals(1, singletonMap.size());
        assertEquals(100, singletonMap.get("key"));
    }

    @Test
    void testReadOnlySet() {
        Set<String> readOnlySet = CollectionUtils.readOnlySet("a", "b", "c");
        assertEquals(3, readOnlySet.size());
        assertTrue(readOnlySet.contains("a"));
        assertTrue(readOnlySet.contains("b"));
        assertTrue(readOnlySet.contains("c"));

        // Проверяем, что коллекция действительно read-only
        assertThrows(UnsupportedOperationException.class, () -> readOnlySet.add("d"));
    }

    @Test
    void testReadOnlyList() {
        List<Integer> readOnlyList = CollectionUtils.readOnlyList(1, 2, 3);
        assertEquals(3, readOnlyList.size());
        assertEquals(1, readOnlyList.get(0));
        assertEquals(2, readOnlyList.get(1));
        assertEquals(3, readOnlyList.get(2));

        // Проверяем, что коллекция действительно read-only
        assertThrows(UnsupportedOperationException.class, () -> readOnlyList.add(4));
    }

    @Test
    void testReadOnlyMap() {
        String[] keys = {"k1", "k2", "k3"};
        Integer[] values = {1, 2, 3};

        Map<String, Integer> readOnlyMap = CollectionUtils.readOnlyMap(keys, values);
        assertEquals(3, readOnlyMap.size());
        assertEquals(1, readOnlyMap.get("k1"));
        assertEquals(2, readOnlyMap.get("k2"));
        assertEquals(3, readOnlyMap.get("k3"));

        // Проверяем, что коллекция действительно read-only
        assertThrows(UnsupportedOperationException.class, () -> readOnlyMap.put("k4", 4));
    }

    @Test
    void testReadOnlyMapWithDifferentLengths() {
        String[] keys = {"k1", "k2"};
        Integer[] values = {1, 2, 3}; // Разная длина

        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.readOnlyMap(keys, values));
    }

    @Test
    void testSortList() {
        List<Integer> unsorted = Arrays.asList(3, 1, 4, 1, 5);
        List<Integer> sorted = CollectionUtils.sortList(unsorted);

        assertEquals(Arrays.asList(1, 1, 3, 4, 5), sorted);
        // Проверяем, что оригинальный список не изменился
        assertEquals(Arrays.asList(3, 1, 4, 1, 5), unsorted);
    }

    @Test
    void testSortSetWithComparator() {
        Set<String> unsorted = new HashSet<>(Arrays.asList("banana", "apple", "cherry"));
        Set<String> sorted = CollectionUtils.sortSet(unsorted, String::compareTo);

        // Проверяем порядок в LinkedHashSet
        List<String> sortedList = new ArrayList<>(sorted);
        assertEquals(Arrays.asList("apple", "banana", "cherry"), sortedList);
    }

    @Test
    void testSortSetNaturalOrder() {
        Set<Integer> unsorted = new HashSet<>(Arrays.asList(5, 2, 8, 1));
        Set<Integer> sorted = CollectionUtils.sortSet(unsorted);

        List<Integer> sortedList = new ArrayList<>(sorted);
        assertEquals(Arrays.asList(1, 2, 5, 8), sortedList);
    }

    @Test
    void testSortByValue() {
        Map<String, Integer> unsorted = new HashMap<>();
        unsorted.put("a", 3);
        unsorted.put("b", 1);
        unsorted.put("c", 2);

        Map<String, Integer> sorted = CollectionUtils.sortByValue(unsorted, Integer::compareTo);

        // Проверяем порядок по значениям
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(sorted.entrySet());
        assertEquals("b", entries.get(0).getKey());
        assertEquals(1, entries.get(0).getValue());
        assertEquals("c", entries.get(1).getKey());
        assertEquals(2, entries.get(1).getValue());
        assertEquals("a", entries.get(2).getKey());
        assertEquals(3, entries.get(2).getValue());
    }

    @Test
    void testSortByKey() {
        Map<Integer, String> unsorted = new HashMap<>();
        unsorted.put(3, "c");
        unsorted.put(1, "a");
        unsorted.put(2, "b");

        Map<Integer, String> sorted = CollectionUtils.sortByKey(unsorted);

        // Проверяем порядок по ключам
        List<Map.Entry<Integer, String>> entries = new ArrayList<>(sorted.entrySet());
        assertEquals(1, entries.get(0).getKey());
        assertEquals("a", entries.get(0).getValue());
        assertEquals(2, entries.get(1).getKey());
        assertEquals("b", entries.get(1).getValue());
        assertEquals(3, entries.get(2).getKey());
        assertEquals("c", entries.get(2).getValue());
    }

    @Test
    void testFindFirst() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> evenPredicate = n -> n % 2 == 0;

        Integer result = CollectionUtils.findFirst(numbers, evenPredicate);
        assertEquals(2, result);

        // Тест, когда элемент не найден
        Predicate<Integer> greaterThan10 = n -> n > 10;
        assertNull(CollectionUtils.findFirst(numbers, greaterThan10));
    }

    @Test
    void testFindAll() {
        List<String> words = Arrays.asList("apple", "banana", "avocado", "cherry", "apricot");
        Predicate<String> startsWithA = s -> s.startsWith("a");

        List<String> result = CollectionUtils.findAll(words, startsWithA);
        assertEquals(Arrays.asList("apple", "avocado", "apricot"), result);

        // Тест, когда элементы не найдены
        Predicate<String> startsWithZ = s -> s.startsWith("z");
        List<String> emptyResult = CollectionUtils.findAll(words, startsWithZ);
        assertTrue(emptyResult.isEmpty());
    }

    @Test
    void testFindFirstKey() {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("avocado", 3);

        Predicate<String> startsWithA = s -> s.startsWith("a");
        String result = CollectionUtils.findFirstKey(map, startsWithA);
        assertTrue(result.startsWith("a")); // Может быть "apple" или "avocado"

        Predicate<String> startsWithZ = s -> s.startsWith("z");
        assertNull(CollectionUtils.findFirstKey(map, startsWithZ));
    }

    @Test
    void testFindFirstValue() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 10);
        map.put("b", 20);
        map.put("c", 30);

        Predicate<Integer> greaterThan15 = n -> n > 15;
        Integer result = CollectionUtils.findFirstValue(map, greaterThan15);
        assertTrue(result > 15); // Может быть 20 или 30

        Predicate<Integer> greaterThan100 = n -> n > 100;
        assertNull(CollectionUtils.findFirstValue(map, greaterThan100));
    }

    @Test
    void testFindFirstEntry() {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 5);
        map.put("banana", 3);
        map.put("cherry", 7);

        Predicate<Map.Entry<String, Integer>> valueGreaterThan4 =
                entry -> entry.getValue() > 4;

        Map.Entry<String, Integer> result = CollectionUtils.findFirstEntry(map, valueGreaterThan4);
        assertNotNull(result);
        assertTrue(result.getValue() > 4);

        Predicate<Map.Entry<String, Integer>> valueGreaterThan10 =
                entry -> entry.getValue() > 10;
        assertNull(CollectionUtils.findFirstEntry(map, valueGreaterThan10));
    }

    @Test
    void testFindAllKeys() {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("avocado", 3);
        map.put("cherry", 4);

        Predicate<String> startsWithA = s -> s.startsWith("a");
        List<String> result = CollectionUtils.findAllKeys(map, startsWithA);

        assertEquals(2, result.size());
        assertTrue(result.contains("apple"));
        assertTrue(result.contains("avocado"));
    }

    @Test
    void testFindAllValues() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 1);

        Predicate<Integer> valueEquals1 = n -> n == 1;
        List<Integer> result = CollectionUtils.findAllValues(map, valueEquals1);

        assertEquals(2, result.size());
        assertTrue(result.contains(1));
    }

    @Test
    void testFindAllEntries() {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 5);
        map.put("banana", 3);
        map.put("avocado", 5);
        map.put("cherry", 2);

        Predicate<Map.Entry<String, Integer>> valueGreaterThan3 =
                entry -> entry.getValue() > 3;

        List<Map.Entry<String, Integer>> result = CollectionUtils.findAllEntries(map, valueGreaterThan3);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(entry -> entry.getValue() > 3));
    }










    @Test
    void testReadOnlySet2() {
        Set<String> original = new HashSet<>(Arrays.asList("a", "b", "c"));
        ReadOnlySet<String> readOnlySet = CollectionUtils.readOnlySet2(original);

        // Проверяем доступ к элементам
        assertEquals(3, readOnlySet.size());
        assertTrue(readOnlySet.contains("a"));
        assertTrue(readOnlySet.contains("b"));
        assertTrue(readOnlySet.contains("c"));
        assertFalse(readOnlySet.contains("d"));

        // Проверяем итератор
        Iterator<String> iterator = readOnlySet.iterator();
        assertNotNull(iterator);

        // Проверяем, что изменения в оригинальном Set отражаются в read-only обёртке
        original.add("d");
        assertEquals(4, readOnlySet.size());
        assertTrue(readOnlySet.contains("d"));



    }

    @Test
    void testReadOnlySet2_Empty() {
        Set<Integer> original = new HashSet<>();
        ReadOnlySet<Integer> readOnlySet = CollectionUtils.readOnlySet2(original);

        assertTrue(readOnlySet.isEmpty());
        assertEquals(0, readOnlySet.size());
    }

    // Тесты для WriteOnlySet
    @Test
    void testWriteOnlySet2() {
        Set<String> original = new HashSet<>(Arrays.asList("a", "b", "c"));
        WriteOnlySet<String> writeOnlySet = CollectionUtils.writeOnlySet2(original);

        // Проверяем добавление элементов
        writeOnlySet.add("d");
        writeOnlySet.add("e");

        // Проверяем, что элементы добавились в оригинальный Set
        assertEquals(5, original.size());
        assertTrue(original.contains("d"));
        assertTrue(original.contains("e"));

        // Проверяем, что нельзя получить доступ к элементам через write-only обёртку
        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlySet.contains("a");
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlySet.iterator();
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlySet.size();
        });
    }

    @Test
    void testWriteOnlySet2_Empty() {
        Set<Integer> original = new HashSet<>();
        WriteOnlySet<Integer> writeOnlySet = CollectionUtils.writeOnlySet2(original);

        writeOnlySet.add(1);
        writeOnlySet.add(2);

        assertEquals(2, original.size());
        assertTrue(original.contains(1));
        assertTrue(original.contains(2));
    }

    // Тесты для ReadOnlyMap
    @Test
    void testReadOnlyMap2() {
        Map<String, Integer> original = new HashMap<>();
        original.put("one", 1);
        original.put("two", 2);
        original.put("three", 3);

        ReadOnlyMap<String, Integer> readOnlyMap = CollectionUtils.readOnlyMap2(original);

        // Проверяем доступ к элементам
        assertEquals(3, readOnlyMap.size());
        assertEquals(1, readOnlyMap.get("one"));
        assertEquals(2, readOnlyMap.get("two"));
        assertEquals(3, readOnlyMap.get("three"));
        assertNull(readOnlyMap.get("four"));

        // Проверяем наличие ключей и значений
        assertTrue(readOnlyMap.containsKey("one"));
        assertTrue(readOnlyMap.containsValue(2));
        assertFalse(readOnlyMap.containsKey("four"));

        // Проверяем entrySet, keySet и values
        assertNotNull(readOnlyMap.entrySet());
        assertNotNull(readOnlyMap.keySet());
        assertNotNull(readOnlyMap.values());

        // Проверяем, что изменения в оригинальном Map отражаются в read-only обёртке
        original.put("four", 4);
        assertEquals(4, readOnlyMap.size());
        assertEquals(4, readOnlyMap.get("four"));



    }

    @Test
    void testReadOnlyMap2_Empty() {
        Map<String, String> original = new HashMap<>();
        ReadOnlyMap<String, String> readOnlyMap = CollectionUtils.readOnlyMap2(original);

        assertTrue(readOnlyMap.isEmpty());
        assertEquals(0, readOnlyMap.size());
        assertNull(readOnlyMap.get("nonexistent"));
    }

    // Тесты для WriteOnlyMap
    @Test
    void testWriteOnlyMap2() {
        Map<String, Integer> original = new HashMap<>();
        original.put("one", 1);
        original.put("two", 2);

        WriteOnlyMap<String, Integer> writeOnlyMap = CollectionUtils.writeOnlyMap2(original);

        // Проверяем добавление элементов
        writeOnlyMap.put("three", 3);
        writeOnlyMap.put("four", 4);

        // Проверяем, что элементы добавились в оригинальный Map
        assertEquals(4, original.size());
        assertEquals(3, original.get("three"));
        assertEquals(4, original.get("four"));

        // Проверяем, что нельзя получить доступ к элементам через write-only обёртку
        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyMap.get("one");
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyMap.containsKey("one");
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyMap.containsValue(1);
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyMap.size();
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyMap.entrySet();
        });
    }

    @Test
    void testWriteOnlyMap2_Empty() {
        Map<Integer, String> original = new HashMap<>();
        WriteOnlyMap<Integer, String> writeOnlyMap = CollectionUtils.writeOnlyMap2(original);

        writeOnlyMap.put(1, "one");
        writeOnlyMap.put(2, "two");

        assertEquals(2, original.size());
        assertEquals("one", original.get(1));
        assertEquals("two", original.get(2));
    }

    // Тесты для ReadOnlyQueue
    @Test
    void testReadOnlyQueue() {
        Queue<String> original = new LinkedList<>(Arrays.asList("first", "second", "third"));
        ReadOnlyQueue<String> readOnlyQueue = CollectionUtils.readOnlyQueue(original);

        // Проверяем доступ к элементам
        assertEquals(3, readOnlyQueue.size());
        assertEquals("first", readOnlyQueue.peek());
        assertFalse(readOnlyQueue.isEmpty());

        // Проверяем итератор
        Iterator<String> iterator = readOnlyQueue.iterator();
        assertNotNull(iterator);
        assertTrue(iterator.hasNext());

        // Проверяем, что изменения в оригинальной Queue отражаются в read-only обёртке
        original.add("fourth");
        assertEquals(4, readOnlyQueue.size());



    }

    @Test
    void testReadOnlyQueue_Empty() {
        Queue<Integer> original = new LinkedList<>();
        ReadOnlyQueue<Integer> readOnlyQueue = CollectionUtils.readOnlyQueue(original);

        assertTrue(readOnlyQueue.isEmpty());
        assertEquals(0, readOnlyQueue.size());
        assertNull(readOnlyQueue.peek());
    }

    // Тесты для WriteOnlyQueue
    @Test
    void testWriteOnlyQueue() {
        Queue<String> original = new LinkedList<>(Arrays.asList("first", "second"));
        WriteOnlyQueue<String> writeOnlyQueue = CollectionUtils.writeOnlyQueue(original);

        // Проверяем добавление элементов
        writeOnlyQueue.offer("third");
        writeOnlyQueue.add("fourth"); // альтернативный метод добавления

        // Проверяем, что элементы добавились в оригинальную Queue
        assertEquals(4, original.size());

        // Создаем список для проверки порядка элементов
        List<String> elements = new ArrayList<>();
        while (!original.isEmpty()) {
            elements.add(original.poll());
        }

        assertEquals(Arrays.asList("first", "second", "third", "fourth"), elements);

        // Проверяем, что нельзя получить доступ к элементам через write-only обёртку
        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyQueue.peek();
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyQueue.poll();
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyQueue.size();
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyQueue.iterator();
        });
    }

    @Test
    void testWriteOnlyQueue_Empty() {
        Queue<Integer> original = new LinkedList<>();
        WriteOnlyQueue<Integer> writeOnlyQueue = CollectionUtils.writeOnlyQueue(original);

        writeOnlyQueue.offer(1);
        writeOnlyQueue.add(2);

        assertEquals(2, original.size());
        assertEquals(1, original.poll());
        assertEquals(2, original.poll());
    }

    // Дополнительные интеграционные тесты
    @Test
    void testReadOnlyList2() {
        List<String> original = new ArrayList<>(Arrays.asList("a", "b", "c"));
        ReadOnlyList<String> readOnlyList = CollectionUtils.readOnlyList2(original);

        assertEquals(3, readOnlyList.size());
        assertEquals("a", readOnlyList.get(0));
        assertEquals("b", readOnlyList.get(1));
        assertEquals("c", readOnlyList.get(2));

        // Проверяем итератор
        Iterator<String> iterator = readOnlyList.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        assertEquals(3, count);

        // Проверяем, что изменения в оригинальном списке отражаются в read-only обёртке
        original.add("d");
        assertEquals(4, readOnlyList.size());
        assertEquals("d", readOnlyList.get(3));
    }

    @Test
    void testWriteOnlyList() {
        List<String> original = new ArrayList<>(Arrays.asList("a", "b", "c"));
        WriteOnlyList<String> writeOnlyList = CollectionUtils.writeOnlyList(original);

        // Проверяем добавление элементов
        writeOnlyList.add("d");
        writeOnlyList.add(1, "inserted"); // добавление по индексу

        // Проверяем, что элементы добавились в оригинальный список
        assertEquals(5, original.size());
        assertEquals("a", original.get(0));
        assertEquals("inserted", original.get(1));
        assertEquals("b", original.get(2));
        assertEquals("c", original.get(3));
        assertEquals("d", original.get(4));

        // Проверяем, что нельзя получить доступ к элементам через write-only обёртку
        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyList.get(0);
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyList.size();
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyList.iterator();
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            writeOnlyList.contains("a");
        });
    }

    // Тест на взаимодействие разных обёрток
    @Test
    void testMultipleWrappers() {
        // Создаем оригинальную коллекцию
        List<String> originalList = new ArrayList<>(Arrays.asList("apple", "banana"));

        // Оборачиваем в разные обёртки
        ReadOnlyList<String> readOnly = CollectionUtils.readOnlyList2(originalList);
        WriteOnlyList<String> writeOnly = CollectionUtils.writeOnlyList(originalList);

        // Добавляем через write-only
        writeOnly.add("cherry");

        // Проверяем через read-only
        assertEquals(3, readOnly.size());
        assertEquals("cherry", readOnly.get(2));

        // Меняем оригинал напрямую
        originalList.add("date");

        // Проверяем, что все обёртки видят изменения
        assertEquals(4, readOnly.size());
        assertEquals("date", readOnly.get(3));
    }

    // Тест на исключения при null параметрах
    @Test
    void testNullParameters() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.readOnlyList2(null));
        assertThrows(NullPointerException.class, () -> CollectionUtils.writeOnlyList(null));
        assertThrows(NullPointerException.class, () -> CollectionUtils.readOnlySet2(null));
        assertThrows(NullPointerException.class, () -> CollectionUtils.writeOnlySet2(null));
        assertThrows(NullPointerException.class, () -> CollectionUtils.readOnlyMap2(null));
        assertThrows(NullPointerException.class, () -> CollectionUtils.writeOnlyMap2(null));
        assertThrows(NullPointerException.class, () -> CollectionUtils.readOnlyQueue(null));
        assertThrows(NullPointerException.class, () -> CollectionUtils.writeOnlyQueue(null));
    }
}