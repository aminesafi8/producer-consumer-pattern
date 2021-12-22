package tn.aminesafi.producerconsumer.locksandconditions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {

    private final Queue<E> queue;
    private int max = 16;
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public MyBlockingQueue(int size) {
        queue = new LinkedList<>();
        this.max = size;
    }

    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == max) {
                notFull.await();
                // block the thread
            }
            queue.add(e);
            notEmpty.signalAll();  // signal for not empty
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                // block the thread
                notEmpty.await(); // wait for not empty
            }
            E item = queue.remove();
            notFull.signalAll(); // signal for not full
            return item;
        } finally {
            lock.unlock();
        }
    }
}
