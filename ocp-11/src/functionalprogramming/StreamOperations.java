package functionalprogramming;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperations {

    public static void main(String[] args) {
        List<Integer> diceParade = generateStreamUsingSupplier();
        printSummaryStatistics(diceParade);
        iterateWithThreeArguments();
        iterateUsingLimit();
    }

    private static List<Integer> generateStreamUsingSupplier() {
        System.out.println("\nROLL PARADE OF TEN D6");

        System.out.println("> Stream.generate(Math::random).limit(10)....sorted()");
        List<Integer> results = Stream.generate(Math::random)
                .limit(10)
                .mapToInt(d -> (int)(d*6+1))
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        System.out.println("You obtained: " +
                results.stream().map(String::valueOf)
                        .collect(Collectors.joining(",")));
        return results;
    }

    private static void printSummaryStatistics(List<Integer> diceParade) {
        System.out.println("\nSTATISTICS OF DICE PARADE");

        System.out.println("> List.stream().mapToInt(Integer::intValue).summaryStatistics()");
        IntSummaryStatistics summary = diceParade.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("Sum = "+summary.getSum());
        System.out.println("Count = "+summary.getCount());
        System.out.println("Max = "+summary.getMax());
        System.out.println("Min = "+summary.getMin());
        System.out.println("Average = "+summary.getAverage());
    }

    private static void iterateUsingLimit() {
        System.out.println("\nRETURN 1000 INCREMENTS USING PARALLEL STREAM");
        // Thread safe variable
        var value1 = new AtomicLong(0);
        // Normal variable (non-thread safe)
        final long[] value2 = {0};

        /* The below block creates a IntStream by iterating from 1 (seed) to 1 (result of UnitaryOperator).
         * The number of iterations is limited to 1000. And the stream is parallel. */
        System.out.println("> IntStream.iterate(1, i -> i+1).limit(1000).parallel()");
        IntStream.iterate(1, i -> i+1).limit(1000).parallel()
                .forEach(i -> {
                    ++value2[0];
//                    System.out.println(i);
                    value1.incrementAndGet();
                });
        // Result of value1 will always be 1000. See {@link ThreadSafeProgramming} for more details.
        System.out.println("Result:\n  value1 = "+value1+" (thread-safe)");
        // Result of value2 is unpredictable (something between 1 and 1000)
        System.out.println("  value2 = "+value2[0]+" (non-thread-safe)");
    }

    private static void iterateWithThreeArguments() {
        System.out.println("\nRETURN 10 FIRST MULTIPLES OF 3");
        System.out.println("> IntStream.iterate(1, i -> i <= 10, i -> ++i).map(i -> i * 3)");

        // iterate() with three arguments pretty much looks like a traditional for loop. Since it has a Predicate to
        //  determine when the stream must terminate, there is need to call limit()
        Stream<String> numbers = IntStream.iterate(1, i -> i <= 10, i -> ++i)
                .map(i -> i * 3)
                .mapToObj(String::valueOf);

        System.out.println("Result : " + numbers.collect(Collectors.joining(",")));
    }

}
