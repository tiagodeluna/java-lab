package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeSheepProgramming1 {

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);

            System.out.println("Counting sheep...");
            ThreadSafeSheepProgramming1 sheepManager = new ThreadSafeSheepProgramming1();
            for(int i = 0; i<10; i++) {
                //PLEASE CHOOSE ONE OF THE LINES BELOW TO SEE THE RESULT
                //service.submit(sheepManager::incrementAndReport);
                //service.submit(sheepManager::incrementAndReportAtomic);
                //service.submit(sheepManager::incrementAndReportSynch1);
                service.submit(sheepManager::incrementAndReportSynch2);
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    /* Using AtomicInt instead of int we ensure that the write+read operations are executed atomically, so it's not
        possible for two threads to compete and overwrite each others result, for example. Otherwise we would see
        the same number printed multiple times. */
    private AtomicInteger sheepCountAtomic = new AtomicInteger(0);
    private int sheepCount = 0;

    void incrementAndReport() {
        // In ++sheepCount the write and read operations are not atomic, causing the threads to overwrite each others result.
        System.out.print(++sheepCount + " ");
    }

    void incrementAndReportAtomic() {
        // incrementAndGet() is equivalent to ++sheepCount, but it is handled as an atomic operation.
        System.out.print(this.sheepCountAtomic.incrementAndGet() + " ");
    }

    void incrementAndReportSynch1() {
        /* While it is random which thread will run next, the 'synchronized' makes sure that each thread will wait for
        this block to be executed by the previous thread before it starts executing it */
        synchronized (this) {
            // No need to use atomic class since we are using synchronized
            System.out.print(++sheepCount + " ");
        }
    }

    // The 'synchronized' modifier makes the whole method a monitor. It can also be applied to static methods.
    synchronized void incrementAndReportSynch2() {
        // Again, no need to use atomic class here
        System.out.print(++sheepCount + " ");
    }

}
