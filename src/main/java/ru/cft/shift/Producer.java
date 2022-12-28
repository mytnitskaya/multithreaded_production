package ru.cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Producer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private final Storage storage;
    private final int producerTime;

    public Producer(Storage storage, int producerTime) {
        this.storage = storage;
        this.producerTime = producerTime;
    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }

    private void produce() {
        try {
            Thread.sleep(producerTime);
            Resource resource = new Resource(UUID.randomUUID());
            log.info("Resource " + resource.id + " was produced");

            storage.put(resource);
        } catch (InterruptedException e) {
            log.error("Thread interrupted");
            throw new RuntimeException(e);
        }

    }
}
