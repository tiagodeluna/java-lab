package collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * The Java Collection Framework includes 4 types of data structures:
 *  - List: extends Collection, ordered (by index), allow duplicates, implemented by ArrayList and LinkedList.
 *  - Set: extends Collection, does not allow duplicates, implemented by HashSet and TreeSet.
 *  - Queue: extends Collection, FIFO or LIFO, implemented by LinkedList.
 *  - Map: does not extend Collection, has unique keys to values, implemented by HashMap and TreeMap.
 */
public class CollectionsAndMap {

    public static void main(String... args) {
        listOperations();
        mapOperations();
        setOperations();
        queueOperations();
    }

    private static void listOperations() {
        System.out.println("Creating an IMMUTABLE LIST with 3 elements...");
        var list1 = List.of(1, 2, 3);
        System.out.println("> var list1 = List.of(1,2,3)");
        System.out.println(String.format("list1 = %s (size: %s)", list1, list1.size()));
        System.out.println("> list1 instanceof Collection = " + (list1 instanceof Collection));

        System.out.println("Creating a new MUTABLE LIST from the previous list...");
        var list2 = new ArrayList<>(list1);
        System.out.println("> var list2 = new ArrayList<>(list1)");
        System.out.println(String.format("list2 = %s (size: %s)", list2, list2.size()));

        System.out.println("> list2.remove(1) = " + list2.remove(1));
        System.out.println(String.format("list1 = %s (size: %s)", list1, list1.size()));
        System.out.println(String.format("list2 = %s (size: %s)", list2, list2.size()));

        System.out.println("> Collections.binarySearch(list1, 2) = " + Collections.binarySearch(list1, 2));
        System.out.println(String.format("list1 = %s (size: %s)", list1, list1.size()));
        System.out.println("> Collections.binarySearch(list2, 2) = " + Collections.binarySearch(list2, 2));
        System.out.println(String.format("list2 = %s (size: %s)", list2, list2.size()));

    }

    private static void mapOperations() {
        System.out.println("\nCreating a MAP and adding 4 elements (1 is duplicate)...");
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("1", 1);
        myMap.put("2", 2);
        myMap.put("3", 3);
        myMap.put("1", 4);
        System.out.println(String.format("Map = %s (size: %s)", myMap, myMap.size()));
        System.out.println("> Map instanceof Collection = " + (myMap instanceof Collection));

        System.out.println("> Map.containsValue(\"1\") = " + myMap.containsValue(1));
        System.out.println(String.format("Map = %s (size: %s)", myMap, myMap.size()));

        System.out.println("> Map.getOrDefault(\"4\", 99) = " + myMap.getOrDefault("4", 99));
        System.out.println(String.format("Map = %s (size: %s)", myMap, myMap.size()));

        System.out.println("> Map.putIfAbsent(\"4\", 4) = " + myMap.putIfAbsent("4", 4));
        System.out.println(String.format("Map = %s (size: %s)", myMap, myMap.size()));

        System.out.println("> Map.replaceAll((key,value) -> value+1)");
        myMap.replaceAll((k,v) -> v+1);
        System.out.println(String.format("Map = %s (size: %s)", myMap, myMap.size()));

        System.out.println("> Map.merge(\"4\", 10, (currentVal, newVal) -> currentVal > newVal ? currentVal : newVal)");
        myMap.merge("4", 10, (currentV, newV) -> currentV > newV ? currentV : newV);
        System.out.println(String.format("Map = %s (size: %s)", myMap, myMap.size()));
    }

    private static void setOperations() {
        System.out.println("\nCreating a SET and adding 5 elements (1 is duplicate)...");
        Set<String> mySet = new HashSet<>();
        mySet.add("D");
        mySet.add("C");
        mySet.add("B");
        mySet.add("A");
        mySet.add("C");
        System.out.println(String.format("Set = %s (size: %s)", mySet, mySet.size()));
        System.out.println("> Set instanceof Collection = " + (mySet instanceof Collection));

        System.out.println("> Set.remove(\"D\") = " + mySet.remove("D"));
        System.out.println(String.format("Set = %s (size: %s)", mySet, mySet.size()));

        System.out.println("> Queue.retainAll([\"C\",\"D\",\"E\"])");
        mySet.retainAll(List.of("C","D","E"));
        System.out.println(String.format("Set = %s (size: %s)", mySet, mySet.size()));
    }

    private static void queueOperations() {
        System.out.println("\nCreating a QUEUE and adding 4 elements...");
        Queue<Integer> myQueue = new LinkedList<>();
        myQueue.add(1);
        myQueue.add(2);
        myQueue.add(3);
        myQueue.add(4);
        System.out.println(String.format("Queue = %s (size: %s)", myQueue, myQueue.size()));
        System.out.println("> Queue instanceof Collection = " + (myQueue instanceof Collection));

        System.out.println("> Queue.remove() = " + myQueue.remove());
        System.out.println(String.format("Queue = %s (size: %s)", myQueue, myQueue.size()));

        System.out.println("> Queue.element() = " + myQueue.element());
        System.out.println(String.format("Queue = %s (size: %s)", myQueue, myQueue.size()));

        System.out.println("> Queue.offer(1) = " + myQueue.offer(1));
        System.out.println(String.format("Queue = %s (size: %s)", myQueue, myQueue.size()));

        System.out.println("> Queue.peek() = " + myQueue.peek());
        System.out.println(String.format("Queue = %s (size: %s)", myQueue, myQueue.size()));

        System.out.println("> Queue.poll() = " + myQueue.poll());
        System.out.println(String.format("Queue = %s (size: %s)", myQueue, myQueue.size()));

        System.out.println("> Queue.removeIf(item -> item <= 2)");
        myQueue.removeIf(item -> item <= 2);
        System.out.println(String.format("Queue = %s (size: %s)", myQueue, myQueue.size()));
    }

}
