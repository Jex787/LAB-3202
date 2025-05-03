// Define a thread class by extending the Thread class
class MyThread extends Thread {
    private String threadName;

    // Constructor to set the thread name
    public MyThread(String name) {
        this.threadName = name;
    }

    // Code that runs when the thread starts
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            // Print the thread name and count
            System.out.println(threadName + " - Count: " + i);

            // When count is 2, the thread suggests yielding
            if (i == 2) {
                System.out.println(threadName + " yielding...");
                Thread.yield(); // Suggests the scheduler to let another thread run
            }

            // Pause the thread for 500 milliseconds
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // If the thread is interrupted, print message and exit
                System.out.println(threadName + " was interrupted and stopping...");
                return; // End the thread early
            }
        }

        // Message after finishing the loop
        System.out.println(threadName + " finished.");
    }
}

public class ThreadMethodsExample {
    public static void main(String[] args) {
        // Create two thread objects
        MyThread t1 = new MyThread("Thread-A");
        MyThread t2 = new MyThread("Thread-B");

        // Start both threads
        t1.start();
        t2.start();

        // Main thread sleeps for 1.2 seconds to let others run
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        // Interrupt Thread-B to simulate stopping it
        t2.interrupt(); // Safe way to stop a thread
    }
}
