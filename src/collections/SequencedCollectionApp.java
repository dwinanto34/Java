package collections;

import java.util.*;

public class SequencedCollectionApp {
    // Java Sequenced Collection was introduced in Java 21.
    // Provides advanced operations for collections, sets, and maps,
    // such as adding and removing elements from the first or last positions.
    public static void main(String[] args) {
        sequencedCollection();
        sequencedSet();
        sequencedMap();
    }

    private static void sequencedCollection() {
        System.out.println("\n\nSequenced Collection");
        SequencedCollection<String> list = new ArrayList<>();

        list.addFirst("First");
        list.addLast("Last");
        System.out.println(list); // Output: [First, Last]

        list = list.reversed();
        System.out.println(list); // Output: [Last, First]

        list.removeFirst();
        list.removeLast();
        System.out.println(list); // Output: []
    }

    private static void sequencedSet() {
        // Normal HashSet does not maintain the insertion order, but LinkedHashSet does.
        // Operations like addFirst and addLast can't be used here,
        // but reversing, removing the first, and removing the last elements can be done.
        System.out.println("\n\nSequenced Set");

        SequencedSet<String> set = new TreeSet<>();
        set.add("John");
        set.add("Christian");
        set.add("Mary");
        set.add("Kristen");
        System.out.println(set); // Output: [Christian, John, Kristen, Mary]

        set = set.reversed();
        System.out.println(set); // Output: [Mary, Kristen, John, Christian]

        set.removeFirst();
        set.removeLast();
        System.out.println(set); // Output: [Kristen, John]
    }

    private static void sequencedMap() {
        // Sequenced HashMap is sorted by the key.
        // Operations like putFirst and putLast can't be used here,
        // but reversing can be done.
        System.out.println("\n\nSequenced Map");

        SequencedMap<String, Integer> map = new TreeMap<>();
        map.put("a", 1);
        map.put("z", 2);
        map.put("c", 3);

        System.out.println(map.firstEntry().getKey()); // Output: a
        System.out.println(map.lastEntry().getKey()); // Output: z

        map = map.reversed();
        System.out.println(map.firstEntry().getKey()); // Output: z
        System.out.println(map.lastEntry().getKey()); // Output: a
    }
}
