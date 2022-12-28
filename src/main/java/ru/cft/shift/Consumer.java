package ru.cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private final Storage storage;
    private final int consumerTime;

    public Consumer(Storage storage, int consumerTime) {
        this.storage = storage;
        this.consumerTime = consumerTime;
    }

    @Override
    public void run() {
        while (true) {
            consume();
        }
    }

    private void consume() {
        try {
            Resource resource = storage.take();

            Thread.sleep(consumerTime);
            log.info("Resource " + resource.id + " was consumed");
        } catch (InterruptedException e) {
            log.error("Thread interrupted");
            throw new RuntimeException(e);
        }
    }
}
