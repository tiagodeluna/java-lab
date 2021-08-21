package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeProgramming {

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);

            System.out.println("Counting sheep...");
            ThreadSafeProgramming sheepManager = new ThreadSafeProgramming();
            for(int i = 0; i<10; i++) {
                //PLEASE CHOOSE ONE OF THE OPTIONS BELOW TO SEE THE RESULT
                final char OPTION = 6;
                switch (OPTION) {
                    case 1:
                        service.submit(sheepManager::incrementAndReport);
                        break;
                    case 2:
                        service.submit(sheepManager::incrementAndReportAtomic);
                        break;
                    case 3:
                        service.submit(sheepManager::incrementAndReportWithSynchBlock);
                        break;
                    case 4:
                        service.submit(sheepManager::incrementAndReportWithSynchModifier);
                        break;
                    case 5:
                        service.submit(sheepManager::incrementAndReportWithLock);
                        break;
                    case 6:
                        service.submit(sheepManager::incrementAndReportWithTryLock);
                }
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    /* Using AtomicInt instead of int we ensure that the write+read operations are executed atomically, so it's not
        possible for two threads to compete and overwrite each others result, for example. Otherwise we would see
        the same number printed multiple times. */
    private AtomicInteger sheepCountAtomic = new AtomicInteger(0);
    /* Lock is a monitor that supports mutual exclusion. In order to work properly, all threads must have access to the
        same instance of Lock (hence I'm using an attribute here). The optional boolean param 'fair', when set to true,
        makes sure the lock is granted to threads in the order it was requested. By default it is false. */
    private Lock lock = new ReentrantLock();
    private int sheepCount = 0;

    void incrementAndReport() {
        // In ++sheepCount the write and read operations are not atomic, causing the threads to overwrite each others result.
        System.out.print(++sheepCount + " ");
    }

    void incrementAndReportAtomic() {
        /* incrementAndGet() is equivalent to ++sheepCount, but it is handled as an atomic operation. The threads will
        still print the result in a random order since the print() call is not part of the atomic operation. */
        System.out.print(this.sheepCountAtomic.incrementAndGet() + " ");
    }

    void incrementAndReportWithSynchBlock() {
        /* While it is random which thread will run next, the 'synchronized' makes sure that each thread will wait for
        this block to be executed by the previous thread before it starts executing it */
        synchronized (this) {
            // No need to use atomic class since we are using synchronized
            System.out.print(++sheepCount + " ");
        }
    }

    // The 'synchronized' modifier makes the whole method a monitor. It can also be applied to static methods.
    synchronized void incrementAndReportWithSynchModifier() {
        // Again, no need to use atomic class here
        System.out.print(++sheepCount + " ");
    }

    void incrementAndReportWithLock() {
        /* The Lock ensures that once a thread has call the lock(), all other threads that call lock() will have to wait
        until the lock is released with unlock(). The use of try/finally is not required, but it is a good practice. */
        try {
            lock.lock();
            System.out.print(++sheepCount + " ");
        } finally {
            lock.unlock();
        }
    }

    void incrementAndReportWithTryLock() {
        /* The tryLock() attempts to acquire the lock and returns immediately. If we use the overloaded version with a
        time limit, it will wait up to the specified time for the lock. In the block below the thread is retrying
        to get the lock by calling the same method again, but I don't think this is recommended in the real world. =] */
        try {
            if (lock.tryLock(300, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(++sheepCount + " ");
                    Thread.sleep(100);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Unable to acquire lock. Retrying...");
                this.incrementAndReportWithTryLock();
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException was thrown");
        }
    }
}
