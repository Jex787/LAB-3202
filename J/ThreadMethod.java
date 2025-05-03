// ThreadMethod.java - Lab 6: Demonstrate yield(), sleep(), and stop (via interrupt) in threads

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                // Check if thread has been requested to stop
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(getName() + " - [STOP]: Detected interrupt; exiting.");
                    return;
                }

                // Print current iteration
                System.out.println(getName() + " - Count: " + i);

                // Demonstrating yield()
                if (i == 2) {
                    System.out.println(getName() + " - [YIELD]: Yielding control to other threads.");
                    Thread.yield(); // Suggest to scheduler to switch threads
                }

                // Demonstrating sleep()
                System.out.println(getName() + " - [SLEEP]: Sleeping for 500ms.");
                Thread.sleep(500); // Sleep for 500 milliseconds
            }

            System.out.println(getName() + " - Finished normally.");
        } catch (InterruptedException e) {
            // Thread was interrupted during sleep
            System.out.println(getName() + " - [STOP]: Interrupted during sleep; exiting.");
        }
    }
}

public class ThreadMethod {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread-A");
        MyThread t2 = new MyThread("Thread-B");

        t1.start();
        t2.start();

        try {
            // Let both threads run for a while
            Thread.sleep(1200);

            // Demonstrating stop() by interrupting one thread
            System.out.println("Main thread - [STOP]: Interrupting " + t2.getName());
            t2.interrupt(); // Graceful stop using interrupt

            // Wait for the interrupted thread to finish
            t2.join();
            System.out.println("Main thread: " + t2.getName() + " has terminated.");

        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
}
