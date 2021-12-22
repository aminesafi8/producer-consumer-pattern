package tn.aminesafi.producerconsumer.waitnotify;

import tn.aminesafi.producerconsumer.models.Item;

public class WaitNotifyRunner {

    public static void main(String[] args) throws InterruptedException {

        WaitAndNotify<Item> queue = new WaitAndNotify<>(10);

        queue.put(new Item());
        queue.put(new Item());

        var fetchedItem = queue.take();
    }

}
