package stream;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.*;

public class StreamApp {
    public static void main(String[] args) {
        basic();

        // stream operation
        // map and flatMap
        System.out.println("Stream operation");
        streamOperations();

        // filter operation
        System.out.println("Filter operation");
        filterOperations();

        // ordering operations
        System.out.println("Ordering operations");
        orderingOperations();

        // aggregate operations
        System.out.println("Aggregate operations");
        aggregateOperations();

        // check operations
        System.out.println("Check operations");
        checkOperations();

        // collectors operations
        System.out.println("Collectors operations");
        collectorsOperations();

        // group by operations
        System.out.println("Group by operations");
        groupByOperations();

        // parallel stream
        System.out.println("Parallel stream");
        parallelStream();

        // streams for primitive data
        System.out.println("Primitive stream");
        primitiveStream();
    }

    private static void basic() {
        // Java streams focus on processing data in a declarative way, allowing various operations to be performed on the data.
        // Streams are lazy by default, meaning operations are not executed until a terminal operation is invoked.
        // Examples of terminal operations include forEach, toArray, collect, etc.

        // Creating a stream using Stream.of
        Stream<String> stream = Stream.of("One", "Two");
        stream.forEach(System.out::println);

        // Without using stream
        List<String> list = List.of("One", "Two");
        list.forEach(System.out::println);

        // With java stream
        list.stream().forEach(System.out::println);

        // Build a stream using builder
        Stream<Object> stringStream = Stream.builder()
                .add("One")
                .add("Two")
                .build();
    }

    private static void streamOperations() {
        // Original list
        List<String> list = List.of("Joey", "John");

        // Using the map operation to transform each element to uppercase
        // The second map operation to add Mr as a prefix
        List<String> modified = list.stream()
                .map(name -> name.toUpperCase())
                .map(name -> "Mr. " + name)
                .collect(Collectors.toList());

        // Original list values remain unchanged
        System.out.println(list);

        // Modified list with uppercase values
        System.out.println(modified);

        // the main difference between map and flatMap is that
        // map applies a function to each element and transforms it into a new element
        // while flatMap applies a function to each element and flattens the resulting streams into a single stream of elements

        // Using flatMap to split each name into characters
        List<Character> characterList = list.stream()
                .flatMap(name -> name.chars().mapToObj(c -> (char) c))
                .collect(Collectors.toList());

        // Displaying the list of characters
        System.out.println("List of characters: " + characterList);
    }

    private static void filterOperations() {
        // There are 2 ways to do filter
        // 1. Distinct (remove duplicate)
        // 2. Filter

        List<String> list = new ArrayList<>();
        list.addAll(List.of("Duplicate", "Duplicate", "Unique"));

        list.stream().distinct()
                .forEach(System.out::println);

        list.stream()
                .filter(name -> name.equals("Duplicate") == false)
                .forEach(System.out::println);

        // Limit and skip
        list.stream().limit(5);
        list.stream().skip(5);
    }

    private static void orderingOperations() {
        List<String> list = new ArrayList<>();
        list.addAll(List.of("1", "3", "2", "4"));

        // ascending
        list.stream()
                .sorted()
                .forEach(System.out::println);

        // descending
        Comparator<String> reverseOrder = Comparator.reverseOrder();
        list.stream()
                .sorted(reverseOrder)
                .forEach(System.out::println);
    }

    private static void aggregateOperations() {
        List<Integer> list = new ArrayList<>();
        list.addAll(List.of(1, 2, 3, 4));

        // min
        Comparator<Integer> naturalOrder = Comparator.naturalOrder();
        list.stream().min(naturalOrder)
                .ifPresent(min -> System.out.println("Min: " + min));

        // max
        list.stream().max(naturalOrder)
                .ifPresent(max -> System.out.println("Max: " + max));

        // count
        long count = list.stream().count();
        System.out.println("Count: " + count);

        // reverse order
        Comparator<Integer> reverseOrder = Comparator.reverseOrder();
        list.stream().min(reverseOrder)
                .ifPresent(reverseMin -> System.out.println("Min in Reverse Order: " + reverseMin));

        // sum integer using reduce operation
        int sumResult = list.stream()
                .reduce(0, Integer::sum);
        System.out.println("Sum (Integer): " + sumResult);

        // sum big decimal using reduce operation
        List<BigDecimal> bigDecimalList = new ArrayList<>();
        bigDecimalList.addAll(List.of(new BigDecimal(100), new BigDecimal(200)));
        bigDecimalList.stream()
                .reduce(BigDecimal::add)
                .ifPresent(bigDecimalSum -> System.out.println("Sum (BigDecimal): " + bigDecimalSum));

        // multiply big decimal using reduce operation
        BigDecimal result = bigDecimalList.stream()
                .reduce(BigDecimal.ONE, BigDecimal::multiply);
        System.out.println(result);
    }

    private static void checkOperations() {
        // for check operations, we should expect a boolean as the end result

        // there are 3 options:
        // 1. anyMatch
        // 2. allMatch
        // 3. noneMatch

        boolean oddNumberExist = List.of(1, 2, 3, 4).stream()
                .anyMatch(num -> num % 2 == 1);

        boolean allNumbersAreOdd = List.of(1, 3, 5).stream()
                .allMatch(num -> num % 2 == 1);

        boolean noneAreOdd = List.of(2, 4, 6).stream()
                .noneMatch(num -> num % 2 == 1);

        // all the values are TRUE
        System.out.println("Does odd number exist? " + oddNumberExist);
        System.out.println("Are all numbers odd? " + allNumbersAreOdd);
        System.out.println("None of the numbers are odd? " + noneAreOdd);
    }

    private static void collectorsOperations() {
        // Collect is a terminal operation
        List<String> list = List.of("One", "Two")
                .stream()
                // to unmodified list
                // .collect(Collectors.toUnmodifiableList());
                .collect(Collectors.toList());

        System.out.println(list);
    }

    private static void groupByOperations() {
        // grouping by
        // we should expect the final result in a map
        // where the key is the grouping key
        // and the value is the list of values
        Map<String, List<Integer>> map = List.of(1, 2, 3, 4, 5, 6).stream()
                .collect(Collectors.groupingBy(number -> {
                    return number % 2 == 0 ? "Even" : "Odd";
                }));
        System.out.println(map);
    }

    private static void parallelStream() {
        // the loops are executed in parallel
        // meaning, we could expect more than 1 thread execution run at the same time (concurrently)
        List.of("1", "2")
                .parallelStream()
                .forEach(System.out::println);
    }

    private static void primitiveStream() {
        int integerSum = IntStream.of(1, 2, 3, 4).sum();
        double doubleSum = DoubleStream.of(1d, 2d, 3d, 4d).sum();
        long longSum = LongStream.of(1, 2, 3, 4).sum();

        System.out.println(integerSum);
        System.out.println(doubleSum);
        System.out.println(longSum);
    }
}
