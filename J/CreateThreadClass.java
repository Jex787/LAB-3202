// CreateThreadExample.java

class MyThread extends Thread {
    private String threadName;

    // Constructor
    public MyThread(String name) {
        this.threadName = name;
    }

    // The code that runs when the thread starts
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + " - Count: " + i);
            try {
                Thread.sleep(500); // pause for 500 milliseconds
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted.");
            }
        }
        System.out.println(threadName + " finished.");
    }
}

public class CreateThreadClass {
    public static void main(String[] args) {
        MyThread t1 = new MyThread("Thread-A");
        MyThread t2 = new MyThread("Thread-B");

        t1.start(); // Start first thread
        t2.start(); // Start second thread
    }
}
