// ThreadOperation.java - Lab 4: Demonstrate suspend, resume, and stop (simulated safely)

class ControlledThread extends Thread {
    private volatile boolean running = true;
    private volatile boolean suspended = false;

    public ControlledThread(String name) {
        super(name);
    }

    public void run() {
        int i = 1;
        while (running) {
            if (!suspended) {
                System.out.println(getName() + " - Count: " + i);
                i++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(getName() + " interrupted.");
                }
            } else {
                try {
                    Thread.sleep(100); // check suspension flag periodically
                } catch (InterruptedException e) {
                    System.out.println(getName() + " suspended sleep interrupted.");
                }
            }
        }
        System.out.println(getName() + " stopped.");
    }

    public void requestStop() {
        running = false;
    }

    public void requestSuspend() {
        suspended = true;
    }

    public void requestResume() {
        suspended = false;
    }
}

public class ThreadOperation {
    public static void main(String[] args) {
        ControlledThread t1 = new ControlledThread("DemoThread");
        t1.start();

        try {
            Thread.sleep(2000);
            System.out.println("Main: Suspending thread");
            t1.requestSuspend();

            Thread.sleep(2000);
            System.out.println("Main: Resuming thread");
            t1.requestResume();

            Thread.sleep(2000);
            System.out.println("Main: Stopping thread");
            t1.requestStop();

            t1.join();
            System.out.println("Main: Thread has finished.");
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
    }
}
