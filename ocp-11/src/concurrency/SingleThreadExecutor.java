package concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor {

    public static void main(String ... args) throws ExecutionException, InterruptedException {
        ExecutorService service = null;

        Runnable task1 = () ->
                System.out.println("Printing something..");
        Runnable task2 = () -> {
            for(int i = 0; i<3; i++) {
                System.out.println("Printing record: " + i);
            }
        };

        try {
            service = Executors.newSingleThreadExecutor();

            System.out.println("begin");

            // The three threads below are executed one-by-one, since we are using the Single-Thread Executor
            // execute() runs a Runnable without returning anything
            service.execute(task1);
            // submit() runs a Runnable or Callable and returns a Future
            Future future1 = service.submit(task2);
            //isDone() returns true if the task was completed, cancelled or threw an exception.
            System.out.println("Is it done now? "
                    + (future1.isDone() ? "yes" : "no"));
            // submit() running with a Callable that produces a Double
            Future<Double> future2 = service.submit(() -> Math.random()*100);
            // When this line will be printed is unpredictable, since the main thread is not controlled by the ExecutorService
            System.out.println("end");
            // Future.get() waits till the end of the task and returns its result
            System.out.println("Your lucky number is " + future2.get().longValue());
            System.out.println("Is it done now? " + (future2.isDone() ? "yes" : "no"));
        } finally {
            if (service != null) {
                // Rejects any new tasks and completes the existing ones
                service.shutdown();
                /* Obs: After shutdown() is called, isShutdown() returns true, but isTerminated() might return false
                (i.e in case there are tasks still running). */
            }
        }

        if (service != null) {
            // Awaits for all tasks to finish. By the end of the timeout it's still not guaranteed that the service will have terminated.
            service.awaitTermination(1, TimeUnit.SECONDS);
        }
    }
}
