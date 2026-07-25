// Bank Account Money Transfer

class BankAccount {
    private String accountName;
    private int balance;

    public BankAccount(String accountName, int balance) {
        this.accountName = accountName;
        this.balance = balance;
    }

    // Deposit money
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(amount + " deposited to " + accountName);
        notifyAll();
    }

    // Withdraw money
    public synchronized void withdraw(int amount) throws InterruptedException {
        while (balance < amount) {
            System.out.println(accountName + " has insufficient balance. Waiting...");
            wait();
        }
        balance -= amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public int getBalance() {
        return balance;
    }
}

class TransferThread extends Thread {
    private BankAccount from;
    private BankAccount to;
    private int amount;

    public TransferThread(String name, BankAccount from, BankAccount to, int amount) {
        super(name);
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            // Lock ordering prevents deadlock
            BankAccount first = from.getAccountName().compareTo(to.getAccountName()) < 0 ? from : to;
            BankAccount second = from.getAccountName().compareTo(to.getAccountName()) < 0 ? to : from;

            synchronized (first) {
                synchronized (second) {
                    from.withdraw(amount);
                    to.deposit(amount);
                    System.out.println(getName() + " transferred " + amount + " from " + from.getAccountName() + " to " + to.getAccountName());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Task02 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount accountA = new BankAccount("Account A", 1000);
        BankAccount accountB = new BankAccount("Account B", 500);

        TransferThread t1 = new TransferThread("Thread-1", accountA, accountB, 700);
        TransferThread t2 = new TransferThread("Thread-2", accountB, accountA, 300);

        t1.start();
        t2.start();
    }
}