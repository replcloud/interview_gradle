package us.matthey.coco.algorithm.epi.ch20parallelcomputing;

public class Semaphore {
    private final int MAX_AVAILABLE;
    private int taken;

    public Semaphore(int MAX_AVAILABLE, int taken) {
        this.MAX_AVAILABLE = MAX_AVAILABLE;
        this.taken = taken;
    }

    public synchronized void acquire() throws InterruptedException {
        while (taken == MAX_AVAILABLE) wait();
        taken++;
    }

    public synchronized void release() throws InterruptedException {
        taken--;
        this.notifyAll();
    }
}
