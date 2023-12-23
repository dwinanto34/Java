package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaApp {
    public static void main(String[] args) {
        // basic knowledge about Lambda
        basic();

        // java.util.function
        // package for lambda
        System.out.println("Consumer and Supplier");
        consumer();
        supplier();

        System.out.println("Function");
        function();

        System.out.println("Predicate");
        predicate();

        // method reference
        System.out.println("Method reference");
        methodReference();

        // collections
        System.out.println("Collection Lambda");
        collectionLambda();

        // lazy parameter
        System.out.println("Lazy parameter");
        lazyParameter();

        // optional class
        System.out.println("Optional");
        optional();

        // passing runnable, consumer, and supplier as method argument
        lambdaParameter();
    }

    private static void basic() {
        // Lambda is a simple version of anonymous class

        // Anonymous class
        SimpleAction simpleActionAnonymous = new SimpleAction() {
            @Override
            public String action(String name) {
                return "This is anonymous class implementation " + name;
            }
        };

        System.out.println(simpleActionAnonymous.action("name"));

        // Lambda
        // Multiple ways to initiate a Lambda
        SimpleAction simpleActionLambda1 = (String name) -> {
            return "Lambda 1 " + name;
        };
        SimpleAction simpleActionLambda2 = (name) -> "Lambda 2 " + name;
        SimpleAction simpleActionLambda3 = name -> "Lambda 3 " + name;

        System.out.println(simpleActionLambda1.action("name"));
        System.out.println(simpleActionLambda2.action("name"));
        System.out.println(simpleActionLambda3.action("name"));
    }

    private static void consumer() {
        Consumer<String> consumer = value -> System.out.println(value);
        consumer.accept("Hi");
    }

    private static void supplier() {
        Supplier<String> supplier = () -> "value";
        String name = supplier.get();

        System.out.println(name);
    }

    private static void function() {
        Function<String, Integer> lengthFunc = value -> value.length();

        System.out.println(lengthFunc.apply("Hello"));

        Function<String, Boolean> isBlankFunc = value -> value.isBlank();

        System.out.println(isBlankFunc.apply("Hello"));
    }

    private static void predicate() {
        Predicate<String> predicateCheckBlank = value -> value.isBlank();

        System.out.println(predicateCheckBlank.test("Hello"));
    }

    private static void methodReference() {
        // method reference only works for single parameter
        LambdaApp app = new LambdaApp();
        Predicate<String> predicateIsLowerCase = app::isLowerCase;
        System.out.println(predicateIsLowerCase.test("ABC"));
        System.out.println(predicateIsLowerCase.test("abc"));

        Function<String, String> functionUpper = String::toUpperCase;
        System.out.println(functionUpper.apply("this is lower case"));
    }

    public boolean isLowerCase(String s) {
        for (Character ch : s.toCharArray()) {
            if (Character.isLowerCase(ch) == false) {
                return false;
            }
        }
        return true;
    }

    private static void collectionLambda() {
        List<String> list = new ArrayList<>();
        list.addAll(List.of("One", "Two"));

        // using lambda
        list.forEach(value -> System.out.println(value));

        // using method reference
        list.forEach(System.out::println);

        list.removeIf(val -> val.equals("One"));
        list.forEach(System.out::println);
    }

    private static void lazyParameter() {
        LambdaApp app = new LambdaApp();
        Consumer<String> consumer = app::heavyProcess;

        executeHeavyProcess(consumer);
    }

    private static void executeHeavyProcess(Consumer<String> consumer) {
        if (false) {
            // the heavyProcess(String name) method never gets executed
            // it will only get execute if the condition is true
            consumer.accept("name");
        }
    }

    private void heavyProcess(String name) {
        System.out.println("DO HEAVY PROCESS " + name);
    }

    private static void optional() {
        String name = "name";
        Optional.ofNullable(name)
                .map(String::toUpperCase)
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Value is not present"));
    }

    private static void lambdaParameter() {
        Runnable runnable = () -> System.out.println("this is runnable");
        Consumer<String> consumer = value -> System.out.println(value);
        Supplier<String> supplier = () -> "this is supplier";

        run(runnable, consumer, supplier);
    }

    private static void run(Runnable runnable, Consumer<String> consumer, Supplier<String> supplier) {
        runnable.run();
        consumer.accept("this is consumer");
        System.out.println(supplier.get());
    }
}
