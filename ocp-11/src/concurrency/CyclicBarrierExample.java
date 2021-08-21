package concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        System.out.println("Time to clean up the lion pen!");
        // Retrieves the number of CPUs available
        int cpus = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of workers available: " + cpus);

        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(cpus);
            var lionPenManager = new CyclicBarrierExample();
            /* CyclicBarrier accepts a limit, and optionally a Runnable to be called upon completion. It is important to
                make sure that you set the number of threads in the pool at least as large as this limit, otherwise the
                code will hang indefinitely! */
            var c1 = new CyclicBarrier(cpus);
            var c2 = new CyclicBarrier(cpus,
                    () -> System.out.println("*** Pen cleaned!"));
            for (int i = 0; i < cpus; i++) {
                service.submit(() -> lionPenManager.performTask(c1, c2));
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private void removeLions() {
        System.out.println("* Removing lions");
    }

    private void cleanPen() {
        System.out.println("* Cleaning the pen");
    }

    private void addLions() {
        System.out.println("* Adding lions");
    }

    private void performTask(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeLions();
            // The barrier ensures that all threads (up to the barrier limit) will stop here before going further.
            c1.await();
            cleanPen();
            c2.await();
            addLions();
        } catch (InterruptedException | BrokenBarrierException e) {
            // Handle checked exceptions here
        }
    }
}
