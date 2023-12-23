package collections;

import java.util.*;

public class CollectionApp {
    public static void main(String[] args) {
        // only for loop all elements and does not support data modification
        iterator();

        // Collection: List, set, queue
        listCollection();
        immutableList();

        setCollection();
        immutableSet();

        queueCollection();

        // Map
        map();

    }

    private static void iterator() {
        // Iterable
        Iterable<String> names = List.of("One", "Two", "Three");

        for (String name : names) {
            System.out.println(name);
        }

        // Iterator
        Iterator iterator = names.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void listCollection() {
        Collection<String> collection = new ArrayList<>();

        collection.add("One");
        collection.add("Two");
        collection.addAll(List.of("Three", "Four"));

        collection.remove("Four");
        collection.removeAll(List.of("One", "Two"));

        System.out.println("Size: " + collection.size());
    }

    private static void immutableList() {
        List<String> stringList = new ArrayList<>();
        stringList.addAll(List.of("One", "Two"));

        List<String> immutableList = Collections.unmodifiableList(stringList);

        // cannot modify (add or remove) the list
        // immutableList.remove("One");
        // immutableList.add("Three");
    }

    private static void setCollection() {
        // HashSet guarantees uniqueness of values, but the order is not guaranteed to follow any specific sequence.
        Set<String> nameHashSet = new HashSet<>();
        nameHashSet.add("One");
        nameHashSet.add("One");

        // LinkedHashSet maintains the order of insertion, providing a predictable iteration order based on entry time.
        Set<String> nameLinkedHashSet = new LinkedHashSet<>();
        nameLinkedHashSet.add("One");
        nameLinkedHashSet.add("One");
    }

    private static void immutableSet() {
        Set<String> stringSet = new HashSet<>();
        stringSet.addAll(List.of("One", "Two"));

        Set<String> immutableSet = Collections.unmodifiableSet(stringSet);

        // cannot modify (add or remove) the list
        // immutableSet.remove("One");
        // immutableSet.add("Three");
    }

    private static void queueCollection() {
        // Queue (FIFO -- first in first out)

        // 1. LinkedList
        // New values are inserted into last position
        Queue<String> queue = new LinkedList<>();

        // add last
        queue.add("1");
        queue.add("3");
        queue.add("2");

        // 1, 3, 2
        System.out.println("LinkedList");
        while (queue.size() > 0) {
            System.out.println(queue.poll());
        }

        // 2. Double ended queue
        // New values could be inserted from first or last position
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.addFirst("1");
        deque.addFirst("3");
        // add means addLast
        deque.add("2");

        // 3, 1, 2
        System.out.println("Double ended queue");
        while (deque.size() > 0) {
            System.out.println(deque.poll());
        }

        // 3. Priority Queue
        // New values are sorted
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("1");
        priorityQueue.add("3");
        priorityQueue.add("4");
        priorityQueue.add("2");

        // 1, 2, 3, 4
        System.out.println("Priority queue");
        while (priorityQueue.size() > 0) {
            System.out.println(priorityQueue.poll());
        }
    }

    private static void map() {
        // key are unique
        Map<String, String> map = new HashMap<>();

        // put
        map.putIfAbsent("key", "value");
        map.put("key", "value");

        // get all keys and values
        map.keySet();
        map.values();

        // get the size, and check if empty
        map.size();
        map.isEmpty();

        // check if contains
        map.containsKey("key");
        map.containsValue("value");
    }
}
