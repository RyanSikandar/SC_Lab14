import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

// BankAccount Class (Shared Resource)
class BankAccount {
    // Using AtomicInteger for thread-safe integer operations
    private AtomicInteger balance;

    public BankAccount(int initialBalance) {
        balance = new AtomicInteger(initialBalance);
    }

    // Deposit method - add amount to the balance
    public void deposit(int amount) {
        // Atomically add amount to the balance
        balance.addAndGet(amount);
    }

    // Withdraw method - subtract amount from the balance
    public void withdraw(int amount) {
        // Check if there are sufficient funds before withdrawing
        if (balance.get() >= amount) {
            balance.addAndGet(-amount);
        } else {
            System.out.println("Insufficient funds for withdrawal: " + amount);
        }
    }

    // Get the current balance
    public int getBalance() {
        return balance.get();
    }
}

// BankClient Class (Threaded Client)
class BankClient extends Thread {
    private BankAccount account;

    public BankClient(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        Random random = new Random();
        // Each client performs 10 transactions
        for (int i = 0; i < 10; i++) {
            int operation = random.nextInt(2);  // Randomly choose deposit (0) or withdraw (1)
            int amount = random.nextInt(100) + 1;  // Random transaction amount between 1 and 100

            if (operation == 0) {
                // Deposit
                account.deposit(amount);
                System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
            } else {
                // Withdraw
                account.withdraw(amount);
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
            }

            // Sleep for a short period to simulate time delay between transactions
            try {
                Thread.sleep(random.nextInt(100) + 50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

// Main Class (Simulation of Bank Transaction)
public class BankTransactionSimulation {
    public static void main(String[] args) {
        // Create a bank account with an initial balance of 1000
        BankAccount account = new BankAccount(1000);

        // Create 5 client threads (simulating 5 customers)
        Thread client1 = new BankClient(account);
        Thread client2 = new BankClient(account);
        Thread client3 = new BankClient(account);
        Thread client4 = new BankClient(account);
        Thread client5 = new BankClient(account);

        // Start all client threads
        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        // Wait for all threads to finish before printing the final balance
        try {
            client1.join();
            client2.join();
            client3.join();
            client4.join();
            client5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Print the final balance after all transactions
        System.out.println("Final account balance: " + account.getBalance());
    }
}

