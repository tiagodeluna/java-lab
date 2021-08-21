package concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

    public static void main(String... args) throws ExecutionException, InterruptedException {
        ExecutorService service = null;
        System.out.println("Begin");

        try {
            service = Executors.newScheduledThreadPool(4);
            runScheduledTasks((ScheduledExecutorService) service);
            Thread.sleep(2000);
        } finally {
            // shutdown() will stop the cycle of the scheduled executor, rejecting any new tasks
            if (service != null) service.shutdown();
        }

        System.out.println("End");

    }

    /* The schedule...() methods are not available in the ExecutorService interface, hence using
    ScheduleExecutorService as parameter type. */
    private static void runScheduledTasks(ScheduledExecutorService service) throws InterruptedException, ExecutionException {
        Runnable task1 = () -> System.out.println("Running Task 1...");
        Callable<String> task2 = () -> {System.out.println("Running Task 2..."); return "Result of Task 2";};
        Runnable task3 = () -> System.out.println("Running Task 3...");
        Runnable task4 = () -> System.out.println("Running Task 4...");

        /* scheduleWithFixedDelay() only accepts Runnable. This task will run once after 0.5 second,
            then keep running width a delay of 1.5 seconds */
        service.scheduleWithFixedDelay(task1, 500, 1500, TimeUnit.MILLISECONDS);
        // schedule() accepts either Runnable or Callable
        // This task will run after 3 seconds
        Future<String> future2 = service.schedule(task2, 3, TimeUnit.SECONDS);
        // This task will run once after 1 second
        service.schedule(task3, 1, TimeUnit.SECONDS);
        // This task will run once after 1 second, then keep running at a fixed rate of 2 seconds
        service.scheduleAtFixedRate(task4, 1, 1, TimeUnit.SECONDS);
        // When this line printed is unknown
        System.out.println("All tasks requested!");

        while (!future2.isDone()) {
            System.out.println("Waiting for task 2 to complete...");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(future2.get());
    }
}
