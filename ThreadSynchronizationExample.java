class Counter {
    private int count = 0;

    // Synchronized method to increment the counter safely
    public synchronized void increment() {
        count++;
    }

    // Getter for the counter value
    public int getCount() {
        return count;
    }
}

class CounterThread extends Thread {
    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        // Each thread increments the counter 100 times
        for (int i = 0; i < 100; i++) {
            counter.increment();
        }
    }
}

public class ThreadSynchronizationExample {
    public static void main(String[] args) throws InterruptedException {
        // Create the shared counter object
        Counter counter = new Counter();

        // Create three threads that share the same counter
        Thread thread1 = new CounterThread(counter);
        Thread thread2 = new CounterThread(counter);
        Thread thread3 = new CounterThread(counter);

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for all threads to finish
        thread1.join();
        thread2.join();
        thread3.join();

        // Print the final value of the counter
        System.out.println("Final counter value: " + counter.getCount());
    }
}
