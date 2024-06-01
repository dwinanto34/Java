package data_structure;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataStructure {
    public static void main(String[] args) {
        // Difference between primitive and object data types:
        // Primitive data types
        // - Are basic data types provided by the Java language
        // - Store actual values (passing by value)
        // - Have default values, example 0 for int, false for boolean, etc
        primitiveDataTypes();

        // Object data types
        // - Are created using classes, and can hold both data and methods
        // - Store references to the memory location where the actual data is stored (passing by reference)
        // - The default value is null
        objectDataTypes();

        collectionDataTypes();
        synchronizedCollectionDataTypes();
        maps();
        queues();
        stacks();
    }

    private static void primitiveDataTypes() {
        byte b = 10;
        short s = 1000;
        int i = 0;
        long l = 100000L;
        float f = 10.5f;
        double d = 10.5;
        char c = 'A';
        boolean bool = false;
    }

    private static void objectDataTypes() {
        String str = "Hello, World!";
        Byte b = 10;
        Short s = 1000;
        Integer i = 10000;
        Long l = 100000L;
        Float f = 10.5f;
        Double d = 10.5;
        Character c = 'A';
        Boolean bool = true;
    }

    private static void collectionDataTypes() {
        // Arrays
        // An array has a fixed size and is stored in contiguous memory locations. Insertion and deletion operations take time,
        // especially in the middle, due to the need for shifting elements. Accessing elements is faster as they are stored contiguously.

        // READ: O(1), Very fast, direct lookup
        // UPDATE: O(1) Very fast, direct lookup
        // DELETE: O(n) Need to shift all subsequent elements to fill the gap
        // INSERT:
        // - O(1) when insert at the end
        // - O(n) when insert at specific element requires shifting
        System.out.println("\nArray list");
        List<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("0", "1", "1", "2", "2"));
        // Adds to last index
        arrayList.add("2");
        // Adds to specific index
        arrayList.add(5, "2");
        // Removes the element at the specified position
        arrayList.remove(0);
        // Removes the first occurrence
        arrayList.remove("1");
        // Removes from this list all of its elements that are contained in the specified collection
        arrayList.removeAll(Arrays.asList("2"));
        // Result arrayList: [1]
        System.out.println("Result arrayList: " + arrayList);

        // Linked Lists
        // A linked list is not fixed in size, and each element has pointers to the next node. Insertion and deletion operations are faster,
        // but accessing elements takes time as traversal is needed from the head node.

        // READ: O(n), Need to traverse from the head to the specific position
        // UPDATE: O(n), Need to traverse from the head to the specific position to update
        // DELETE: O(n), Need to traverse from the head to the specific position, but once found, deletion requires O(1)
        // INSERT:
        // - O(1) when insert at the beginning
        // - O(n) when insert at specific position which requires traversal
        System.out.println("\nLinked list");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addAll(Arrays.asList("1", "2", "3"));
        linkedList.addFirst("0");
        linkedList.addLast("4");
        // Result linkedList: [0, 1, 2, 3, 4]
        System.out.println("Result linkedList: " + linkedList);
        linkedList.removeFirst();
        linkedList.removeLast();
        // Result linkedList: [1, 2, 3]
        System.out.println("Result linkedList: " + linkedList);

        // Hash set
        // Guarantees unique values in the hash set
        // Uses hash map for storing objects
        // Does not maintain insertion order
        // O(1) time complexity for insertion, retrieving, and removing
        System.out.println("\nHash set");
        Set<String> hashSet = new HashSet<>();
        // True
        System.out.println(hashSet.add("set"));
        // False
        System.out.println(hashSet.add("set"));
        // True
        System.out.println(hashSet.contains("set"));
        hashSet.addAll(Arrays.asList("1", "2", "3", "4"));
        // The elements order is random, example [1, 2, set, 3, 4]
        System.out.println(hashSet);

        // Linked Hash set
        // Guarantees unique values in the set
        // Uses hash map for storing objects
        // Maintains insertion order
        // O(1) time complexity for insertion, retrieving, and removing
        System.out.println("\nLinked Hash set");
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(Arrays.asList("1", "2", "3", "4"));
        linkedHashSet.addAll(Arrays.asList("5", "6", "7", "8"));
        // The result is always consistent : [1, 2, 3, 4, 5, 6, 7, 8]
        System.out.println(linkedHashSet);

        // Tree set
        // Guarantees unique values in the hash set
        // Uses a red-black tree for storing objects
        // Red-black tree is a type of self-balancing binary search tree that is used to maintain sorted data. It ensures that the tree remains balanced, making operations such as insertion, deletion, and lookup efficient.
        // Sorts the elements according to provided comparator (or default comparator)
        // O(log(n) time complexity for insertion, retrieving, and removing
        System.out.println("\nTree set");
        Set<String> treeSet = new TreeSet<>(Collections.reverseOrder());
        treeSet.addAll(Arrays.asList("Banana", "Apple", "Cherry"));
        // The result is always consistent: [Cherry, Banana, Apple]
        System.out.println(treeSet);
    }

    private static void synchronizedCollectionDataTypes() {
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        Map<Integer, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        Map<Integer, String> synchronizedTreeMap = Collections.synchronizedSortedMap(new TreeMap<>());
        Set<Integer> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
        SortedSet<Integer> syncSortedSortedSet = Collections.synchronizedSortedSet(new TreeSet<>());
    }

    private static void maps() {
        // HashMap
        // HashMap is a key-value pairs data structure.
        // When an element is added to the HashSet, its hash code is computed using the hashCode() method. This hash code determines the index in the internal array where the element will be stored.
        // If two elements have the same hash code (a collision), they are stored in the same bucket using a linked list or a balanced tree in more recent implementations.
        // O(1) average time complexity for insertion, retrieval, and removal operations.
        System.out.println("\nHash map");
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("key", 1);
        hashMap.containsKey("key");
        hashMap.remove("key");

        // Linked hash map
        // Linked hash map is similar to hash map but it maintains the insertion order
        // O(1) average time complexity for insertion, retrieval, and removal operations.
        System.out.println("\nLinked hash map");
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("key", 1);

        // Tree map
        // Tree map is a hash map, but it sorts the elements according to the provided comparator (or use default comparator)
        // Uses a red-black tree for storing objects
        // O(log(n) time complexity for insertion, retrieving, and removing
        System.out.println("\nTree map");
        TreeMap<Integer, String> treeMap = new TreeMap<>(Collections.reverseOrder());
        treeMap.put(1, "key");
        treeMap.put(4, "key");
        treeMap.put(5, "key");
        // 4 -> it's floor up because we define it as reverse order
        // if we want it to return 1, then we have to floor down and that requires comparator changes.
        System.out.println(treeMap.floorKey(2));
        // 4
        System.out.println(treeMap.floorKey(4));
        // null
        System.out.println(treeMap.floorKey(6));

        // Hash table
        // Hash table is a legacy class similar to hash map, but it is synchronized.
        // This makes it thread-safe, but at the cost of performance.
        // O(1) average time complexity for insertion, retrieval, and removal operations.
        System.out.println("\nHash table");
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        hashtable.put("key", 1);
    }

    private static void queues() {
        // Linked list as a queue
        // Linked list is an implementation of the list and deque interfaces.
        // When used as a queue, it operates in FIFO mode.
        // O(1) time complexity for insertion, retrieval, and removal
        System.out.println("\nQueue");
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        // Peek only views the head of the queue without removing it
        queue.peek();
        // Poll is a combination of viewing and removing the head of the queue
        queue.poll();

        // Priority queue
        // Priority queue is an implementation of the queue interface based on priority heap.
        // It sorts the elements according to the provided comparator (or use default comparator)
        // O(log(n) time complexity for insertion, retrieving, and removing
        System.out.println("\nPriority queue");
        Queue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        priorityQueue.add(3);
        priorityQueue.add(1);
        priorityQueue.add(2);
        // Result PriorityQueue: 321
        System.out.print("PriorityQueue: ");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll());
        }
        System.out.println("");

        // Linked list as a deque
        // Linked list can be used as a double ended queue by implementing the deque interface.
        // It allows elements to be added or removed from both ends efficiently.
        // O(1) time complexity for insertion, retrieval, and removal
        System.out.println("\nDouble ended queue");
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("first");
        deque.addLast("last");
        deque.removeFirst();
        deque.removeLast();
    }

    private static void stacks() {
        // NOTES
        // If I had to design stack from scratch, I could use :
        // 1. array:
        // - Push operation: O(1) but may involve O(n) if resizing is necessary
        // - Pop operation: O(1)
        // - Memory usage: May lead to unused allocated memory

        // 2. linked list:
        // - Push operation: O(1)
        // - Pop operation: O(1)
        // - Memory usage: Very efficient.

        // Stack
        // Stack is a subclass of Vector in Java that represents a LIFO stack of objects.
        System.out.println("\nStack");
        Stack<String> stack = new Stack<>();
        stack.add("1");
        stack.pop();
        stack.peek();
    }
}
