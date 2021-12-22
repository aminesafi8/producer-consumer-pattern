package tn.aminesafi.producerconsumer.waitnotify;

import java.util.LinkedList;
import java.util.Queue;

public class WaitAndNotify<E> {


    private final Queue<E> queue;
    private int max = 16;
    private final Object sharedQ = new Object();

    public WaitAndNotify(int size) {
        queue = new LinkedList<>();
        this.max = size;
    }

    public void put(E e) throws InterruptedException {
        while (queue.size() == max) {
            synchronized (sharedQ) {
                sharedQ.wait();
            }
        }
        queue.add(e);
        synchronized (sharedQ) {
            sharedQ.notifyAll();
        }
    }

    public E take() throws InterruptedException {
        while (queue.isEmpty()) {
            synchronized (sharedQ) {
                sharedQ.wait();
            }
        }
        E item = queue.remove();
        synchronized (sharedQ) {
            sharedQ.notifyAll();
        }
        return item;

    }
}
