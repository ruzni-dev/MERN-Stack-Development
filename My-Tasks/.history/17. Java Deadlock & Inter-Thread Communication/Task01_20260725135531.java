class Resource {

    private final String name;
    private boolean busy = false;

    public Resource(String name) {
        this.name = name;
    }

    // Acquire the resource
    public synchronized void acquire(String user) throws InterruptedException {
        while (busy) {
            System.out.println(user + " is waiting for " + name);
            wait();
        }
        busy = true;
        System.out.println(user + " acquired " + name);
    }

    // Release the resource
    public synchronized void release(String user) {
        busy = false;
        System.out.println(user + " released " + name);
        notifyAll();
    }
}

class User extends Thread {
    private Resource printer;
    private Resource scanner;

    public User(String name, Resource printer, Resource scanner) {
        super(name);
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        try {
            // Lock ordering prevents deadlock
            printer.acquire(getName());
            Thread.sleep(500);
            scanner.acquire(getName());
            System.out.println(getName() + " is printing and scanning...");
            Thread.sleep(1000);
            scanner.release(getName());
            printer.release(getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Task01 {
    public static void main(String[] args) {
        Resource printer = new Resource("Printer");
        Resource scanner = new Resource("Scanner");

        User user1 = new User("User1", printer, scanner);
        User user2 = new User("User2", printer, scanner);

        user1.start();
        user2.start();
    }
}