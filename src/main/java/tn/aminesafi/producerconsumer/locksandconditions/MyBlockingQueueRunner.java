package tn.aminesafi.producerconsumer.locksandconditions;

import tn.aminesafi.producerconsumer.models.Item;

public class MyBlockingQueueRunner {

    public static void main(String[] args) throws InterruptedException {

        MyBlockingQueue<Item> queue = new MyBlockingQueue<>(10);

        queue.put(new Item());
        queue.put(new Item());

        var fetchedItem = queue.take();
    }
}
