package tn.aminesafi.producerconsumer.withblockingqueue;

import tn.aminesafi.producerconsumer.models.Item;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerMain {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Item> queue = new ArrayBlockingQueue<>(10);

        // Producer
        final Runnable producer = () -> {
            while (true) {

                try {
                    queue.put(new Item());
                    System.out.println("Put!");
                } catch (InterruptedException e) {
                    // handle exception
                }

            }
        };
        new Thread(producer).start();
        new Thread(producer).start();


        // Consumer
        final Runnable consumer = () -> {
            while (true) {
                try {
                    Item item = queue.take();
                    System.out.println("Take!");
                } catch (InterruptedException e) {
                    // handle exception
                }
                // process(item);
            }
        };
        new Thread(consumer).start();
        new Thread(consumer).start();

        Thread.sleep(1000);
    }
}
