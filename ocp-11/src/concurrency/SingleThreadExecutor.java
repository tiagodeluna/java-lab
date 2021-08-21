package concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor {

    public static void main(String... args) throws ExecutionException, InterruptedException {
        ExecutorService service = null;
        System.out.println("Begin");

        try {
            service = Executors.newSingleThreadExecutor();

            //PLEASE CHOOSE WHICH METHOD YOU WANT TO USE TO RUN THE TASKS!
            //runWithExecuteAndSubmit(service);
            runWithInvokeAll(service);
            //runWithInvokeAny(service);
        } finally {
            if (service != null) {
                // Rejects any new tasks and completes the existing ones
                service.shutdown();
                /* Obs: After shutdown() is called, isShutdown() returns true, but isTerminated() might return false
                (i.e in case there are tasks still running). */
            }
        }

        if (service != null) {
            /* Awaits for all tasks to finish. By the end of the timeout it's still not guaranteed that the service will have terminated.
                Throws InterruptedException. */
            service.awaitTermination(1, TimeUnit.SECONDS);
        }

        System.out.println("End");
    }

    private static void runWithExecuteAndSubmit(ExecutorService service) throws InterruptedException, ExecutionException {
        Runnable task1 = () ->
                System.out.println("Printing something..");
        Runnable task2 = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Printing record: " + i);
            }
        };

        // The three threads below are executed one-by-one, since we are using the Single-Thread Executor
        // execute() runs a Runnable without returning anything
        service.execute(task1);
        // submit() runs a Runnable or Callable and returns a Future
        Future future1 = service.submit(task2);
        //isDone() returns true if the task was completed, cancelled or threw an exception.
        System.out.println("Is it done now? " + (future1.isDone() ? "yes" : "no"));
        // submit() running with a Callable that produces a Double
        Future<Double> future2 = service.submit(() -> Math.random() * 100);

        // When this line will be printed is unknown, since the main thread is not controlled by the ExecutorService
        System.out.println("All tasks requested!");
        // Future.get() waits till the end of the task and returns its result. Throws ExecutionException
        System.out.println("Your lucky number is " + future2.get().longValue());
        System.out.println("Is it done now? " + (future2.isDone() ? "yes" : "no"));
    }

    private static void runWithInvokeAll(ExecutorService service) throws InterruptedException, ExecutionException {
        Callable<String> task1 = () -> "Result 1";
        Callable<String> task2 = () -> {TimeUnit.SECONDS.sleep(2); return "Result 2";};
        Callable<String> task3 = () -> {TimeUnit.SECONDS.sleep(3); return "Result 3";};

        // The execution will take about 5 seconds since we are using a SingleThreadExecutor
        List<Future<String>> futures = service.invokeAll(List.of(task1, task2, task3));
        // This line will be printed as soon as all tasks get completed, since invokeAll() is synchronous
        System.out.println("All tasks requested!");

        for (Future f : futures) {
            // isDone() always returns true
            System.out.println("Is it done now? " + (f.isDone() ? "yes" : "no"));
            System.out.println(f.get());
        }
    }

    private static void runWithInvokeAny(ExecutorService service) throws InterruptedException, ExecutionException {
        Callable<String> task1 = () -> {
            System.out.println("Runner 1 reached the finish line!");
            return "Runner 1";
        };
        Callable<String> task2 = () -> {
            System.out.println("Runner 2 reached the finish line!");
            return "Runner 2";
        };
        Callable<String> task3 = () -> {
            System.out.println("Runner 3 reached the finish line!");
            return "Runner 3";
        };
        System.out.println("The race will begin...");
        /* invokeAny() executes the given tasks, returning the result of one that has completed successfully (without
            throwing an exception), if any do. */
        String result = service.invokeAny(List.of(task1, task2, task3));
        // This line will always be printed after the tasks run, since invokeAny() is synchronous
        System.out.println("The race is over!");

        System.out.println("...and the winner is: " + result);
    }
}
