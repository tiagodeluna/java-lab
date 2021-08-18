package concurrency;

/**
 * The code below creates and executes threads in 3 different ways.
 * The order of execution is unknown.
 */
public class ThreadAndRunnable {

    public static void main (String[] args) {

        // Three ways of working with Thread and Runnable:
        //1. Thread with a Runnable defined as a lambda expression
        new Thread(() -> System.out.println("Hey!")).start();
        //2. Thread with a custom Runnable implementation
        new Thread(new Ho()).start();
        //3. Custom Thread overriding the run() method
        new LetsGoThread("Let's Go!").start();
    }

    static class LetsGoThread extends Thread {
        private final String phrase;

        public LetsGoThread(String phrase) {
            this.phrase = phrase;
        }

        @Override
        public void run() {
            System.out.println(phrase);
        }
    }

    static class Ho implements Runnable {

        @Override
        public void run() {
            System.out.println("Ho!");
        }
    }
}
