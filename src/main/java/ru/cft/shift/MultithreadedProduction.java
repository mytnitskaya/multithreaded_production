package ru.cft.shift;

public class MultithreadedProduction {
    public void startProduction(ProductionParameters parameters){
        Storage storage = new Storage(parameters.storageSize);

        for (int i = 0; i < parameters.producerCount; i++) {
            var threadName = "Producer" + i;
            Thread producer = new Thread(new Producer(storage, parameters.producerTime), threadName);
            producer.start();
        }

        for (int i = 0; i < parameters.consumerCount; i++) {
            var threadName = "Consumer" + i;
            Thread consumer = new Thread(new Consumer(storage, parameters.consumerTime), threadName);
            consumer.start();
        }
    }

}
