package concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConcurrentCollections {

    public static void main(String[] args) {
        usingLinkedBlockingQueue();
        usingCopyOnWriteCollection();
    }

    private static void usingCopyOnWriteCollection() {
        System.out.println("\nLETS RELEASE THE BIRDS!");
    /* A "CopyOnWrite" collection copies its structure every time the content gets changed (i.e an element is added,
        replaced, or removed). By doing that, the collection does not throw an exception when read and write
        operations are performed at the same time. */
        List<String> cage = new CopyOnWriteArrayList<>();
        cage.add("Bird 1");
        cage.add("Bird 2");
        cage.add("Bird 3");
        System.out.println("Birds in the cage: " + cage.size());

        for (String bird : cage) {
            System.out.println("Releasing " + bird);
            // If cage was a regular ArrayList, this line would throw a ConcurrentModificationException
            cage.remove(bird);
        }
        System.out.println("Birds in the cage: " + cage.size());
    }

    private static void usingLinkedBlockingQueue() {
        System.out.println("LETS FEED THE HUNGRY BIRDS!");
        try {
            var blockingQueue = new LinkedBlockingQueue<String>();
            /* LinkedBlockingQueue implements both Queue and BlockingQueue interfaces. The second adds methods that
                accept a time limit. The offer/poll operation waits the specified time for the space/item to be available. */
            blockingQueue.offer("Bird 1");
            blockingQueue.offer("Bird 2", 1, TimeUnit.SECONDS);
            System.out.println("Hungry birds: " + blockingQueue.size());
            System.out.println("Feeding " + blockingQueue.poll());
            System.out.println("Feeding " + blockingQueue.poll(2, TimeUnit.SECONDS));
            System.out.println("Hungry birds: " + blockingQueue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
