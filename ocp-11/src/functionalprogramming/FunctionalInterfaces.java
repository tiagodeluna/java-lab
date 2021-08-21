package functionalprogramming;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class FunctionalInterfaces {

    public static void main(String[] args) {
        // These are the possible ways of retrieving the value in an optional
        Optional<String> opt = Optional.ofNullable("not null");
        String result = opt.orElse("something else");
        result = opt.get(); // Will throw NoSuchElementException in case element is null
        result = opt.isPresent() ? opt.get() : "alternative thing"; // This is the noob way!
        result = opt.orElseGet(() -> "something different"); //Uses a supplier to return a value
        result = opt.orElseThrow(); // Again, throws NoSuchElementException in case of null
        result = opt.orElseThrow(() -> new RuntimeException()); // or...
        result = opt.orElseThrow(RuntimeException::new);

        findMaxEvenNumber(List.of(1, 4, 15, 30, 100, 77));
        findMaxEvenNumber(List.of(7, 5, 99));

        predicateConvinienceMethods("brown sugar");
        predicateConvinienceMethods("himalayan pink salt");

        functionConvinienceMethods(2, 3);
    }

    private static void findMaxEvenNumber(List<Integer> numbers) {
        System.out.println("\nFIND MAX EVEN NUMBER IN A LIST");
        System.out.println("Input list : " + numbers);

        numbers.stream().filter(n -> n%2==0)
                .max(Comparator.naturalOrder())
                // ifPresentOrElse() runs a Consumer in case a value is present, or runs a Runnable in case it is not present
                .ifPresentOrElse(
                    n -> System.out.println("Max even number is: " + n),
                    () -> System.out.println("No even numbers found!")
            );
    }

    private static void predicateConvinienceMethods(String grain) {
        System.out.println("\nSUGAR TASTER (using Predicate)");
        System.out.println("Testing grain: " + grain);

        Predicate<String> isSugar = s -> s.contains("sugar");
        Predicate<String> isBrown = s -> s.contains("brown");
        // Combines predicates with the convenience method and()
        Predicate<String> isBrownSugar = isSugar.and(isBrown);

        System.out.println("Is it sugar? -> " + isSugar.test(grain));
        System.out.println("Is it brown sugar? -> " + isBrownSugar.test(grain));
        // Combines predicates and uses negate()
        Predicate<String> isAnotherSugar = isSugar.and(isBrown.negate());
        System.out.println("Is it a different type of sugar? -> " + isAnotherSugar.test(grain));
    }

    private static void functionConvinienceMethods(int x, int y) {
        System.out.println("\nARITHMETIC OPERATIONS (using Function+BiFunction)");
        System.out.println(String.format("Inputs = (%s, %s)",x,y));
        BinaryOperator<Integer> sum = (a,b) -> a + b;
        UnaryOperator<Integer> multiplyBy2 = a -> a * 2;
        UnaryOperator<Integer> increment = a -> a + 1;

        // In Function.compose() you read from right to left (the function passed in the compose is executed first)
        Function<Integer, Integer> incrementAndMultiply = multiplyBy2.compose(increment);
        Function<Integer, Integer> multiplyAndIncrement = increment.compose(multiplyBy2);
        // In BiFunction.andThen() you read from left to right
        BiFunction<Integer, Integer, Integer> sumAndMultiply = sum.andThen(multiplyBy2);

        System.out.println(String.format("> %s * 2 + 1 = %s", x, multiplyAndIncrement.apply(x)));
        System.out.println(String.format("> (%s + 1) * 2 = %s", y, incrementAndMultiply.apply(y)));
        System.out.println(String.format("> (%s + %s) * 2 = %s", x, y, sumAndMultiply.apply(x, y)));
    }
}
