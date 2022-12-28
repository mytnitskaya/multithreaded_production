package ru.cft.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Logger log = LoggerFactory.getLogger(Storage.class);
    private final int storageSize;
    private final List<Resource> storage;
    public Storage(int storageSize) {
        this.storageSize = storageSize;
        storage = new ArrayList<>(storageSize);
    }

    public void put(Resource resource) {
        synchronized (this.storage) {
            var wasPaused = false;
            while (isFull()) {
                try {
                    if (!wasPaused){
                        log.info("Thread is waiting");
                    }
                    wasPaused = true;
                    this.storage.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (wasPaused){
                log.info("Thread has resume");
            }

            storage.add(resource);
            log.info("Resource added.   Storage full on " + storage.size() + "/" + storageSize);
            this.storage.notify();
        }
    }

    public Resource take() {
        synchronized (this.storage) {
            var wasPaused = false;
            while (isEmpty()) {
                try {
                    if (!wasPaused){
                        log.info("Thread is waiting");
                    }
                    wasPaused = true;
                    this.storage.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if(wasPaused){
                log.info("Thread has resumed");
            }

            var removed = storage.remove(0);
            log.info("Resource removed. Storage full on " + storage.size() + "/" + storageSize);
            this.storage.notify();

            return removed;
        }
    }

    private boolean isEmpty() {
        return storage.isEmpty();
    }

    private boolean isFull() {
        return storage.size() >= storageSize;
    }
}
