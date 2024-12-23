import java.util.concurrent.CopyOnWriteArrayList;

class ListWriter extends Thread {
    private CopyOnWriteArrayList<Integer> list;

    public ListWriter(CopyOnWriteArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        // Add 100 elements to the list (write operation)
        for (int i = 0; i < 100; i++) {
            list.add(i);
            try {
                // Simulate some delay to allow other threads to access the list
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class ListReader extends Thread {
    private CopyOnWriteArrayList<Integer> list;

    public ListReader(CopyOnWriteArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        // Read 100 times from the list
        for (int i = 0; i < 100; i++) {
            // Print the size of the list to simulate reading the list
            System.out.println("List size: " + list.size());
            try {
                // Simulate some delay to allow write operations
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class ConcurrentDataStructuresExample {
    public static void main(String[] args) throws InterruptedException {
        // Create a thread-safe CopyOnWriteArrayList
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        // Create writer and reader threads
        Thread writer1 = new ListWriter(list);
        Thread writer2 = new ListWriter(list);
        Thread reader1 = new ListReader(list);
        Thread reader2 = new ListReader(list);

        // Start the threads
        writer1.start();
        writer2.start();
        reader1.start();
        reader2.start();

        // Wait for all threads to finish
        writer1.join();
        writer2.join();
        reader1.join();
        reader2.join();

        // Final size of the list
        System.out.println("Final list size: " + list.size());
    }
}


