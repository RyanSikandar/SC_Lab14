// Thread 1: Prints numbers from 1 to 10
class NumberPrinter implements Runnable {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
            try {
                Thread.sleep(100);  // Sleep for 100 milliseconds to simulate a delay
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

// Thread 2: Prints squares of numbers from 1 to 10
class SquarePrinter implements Runnable {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Square of " + i + ": " + (i * i));
            try {
                Thread.sleep(100);  // Sleep for 100 milliseconds to simulate a delay
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class MultithreadingExample {
    public static void main(String[] args) {
        // Create Runnable objects
        Runnable numberPrinter = new NumberPrinter();
        Runnable squarePrinter = new SquarePrinter();
        
        // Create Thread objects and pass the Runnable objects to them
        Thread thread1 = new Thread(numberPrinter);
        Thread thread2 = new Thread(squarePrinter);
        
        // Start the threads to run concurrently
        thread1.start();
        thread2.start();
    }
}

