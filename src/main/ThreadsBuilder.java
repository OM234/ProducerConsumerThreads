package main;

import consumer.ConsumerThread;
import producer.ProducerThread;
import shared.bufferQueue.BufferQueue;
import shared.resource.Resource;
import shared.resource.factory.ResourceFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadsBuilder<T extends Resource> extends Thread {

    private final BufferQueue<T> bufferQueue;
    private final ResourceFactory<T> factory;
    private final ProducerThread<T> producerThread;
    private List<ConsumerThread<T>> consumerThreads;

    public ThreadsBuilder(BufferQueue<T> bufferQueue, ResourceFactory<T> factory){
        this.bufferQueue = bufferQueue;
        this.factory = factory;
        this.producerThread = new ProducerThread<T>(bufferQueue, factory);
    }

    @Override
    public void run(){
        runProducerThreads();
        runConsumerThreads();
        waitForProducerToStop();
        stopConsumerThreads();
    }

    private void runProducerThreads(){
        producerThread.start();
    }

    private void runConsumerThreads() {
        populateConsumerThreadsList();
        consumerThreads.forEach(Thread::start);
    }

    private void waitForProducerToStop() {
        while(producerThread.isAlive());
    }

    private void stopConsumerThreads() {
        consumerThreads.forEach(ConsumerThread::stopConsuming);
    }

    private void populateConsumerThreadsList() {
        consumerThreads = Stream
                .iterate(0, n-> n+1)
                .limit(5)
                .map(i -> new ConsumerThread<>(bufferQueue, generateConsumerThreadName(i)))
                .collect(Collectors.toList());
    }

    private String generateConsumerThreadName(Integer i) {
        return Integer.toString(i);
    }
}
