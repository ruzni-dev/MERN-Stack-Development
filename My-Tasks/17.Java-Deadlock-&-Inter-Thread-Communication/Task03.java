// Task 03 - Bank Account Transfer System

class Account {
    String name;
    int balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }
}

class Transfer extends Thread {
    private Account from;
    private Account to;
    private int amount;

    public Transfer(String name, Account from, Account to, int amount) {
        super(name);
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        Account first = from.name.compareTo(to.name) < 0 ? from : to;
        Account second = from.name.compareTo(to.name) < 0 ? to : from;

        synchronized (first) {
            synchronized (second) {
                from.balance -= amount;
                to.balance += amount;
                System.out.println(getName() + " transferred " + amount + " from " + from.name + " to " + to.name);
                System.out.println(from.name + " Balance : " + from.balance);
                System.out.println(to.name + " Balance : " + to.balance);
            }
        }
    }
}

public class Task03 {
    public static void main(String[] args) {
        Account accountA = new Account("Account A", 1000);
        Account accountB = new Account("Account B", 1000);
        
        Transfer t1 = new Transfer( "Thread-1", accountA, accountB, 200);
        Transfer t2 = new Transfer( "Thread-2", accountB, accountA, 100);

        t1.start();
        t2.start();
    }
}