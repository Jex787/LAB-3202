// ThreadMethod.java - Lab 6: Demonstrate yield(), sleep(), and stop (via interrupt) in threads

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(getName() + " detected interrupt; exiting.");
                    return;
                }

                System.out.println(getName() + " - Count: " + i);

                if (i == 2) {
                    System.out.println(getName() + " yielding...");
                    Thread.yield();
                }

                Thread.sleep(500);
            }
            System.out.println(getName() + " finished normally.");
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted during sleep; exiting.");
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
            Thread.sleep(1200);
            System.out.println("Main thread: interrupting " + t2.getName());
            t2.interrupt();

            t2.join();
            System.out.println(t2.getName() + " has terminated.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
