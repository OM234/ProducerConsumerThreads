package consumer;

import shared.bufferQueue.BufferQueue;
import shared.resource.Resource;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class ConsumerThread<T extends Resource> extends Thread {

    private final BufferQueue<T> bufferQueue;
    private final String name;
    private final AtomicBoolean shouldConsume = new AtomicBoolean(true);

    public ConsumerThread(BufferQueue<T> bufferQueue, String name) {
        this.bufferQueue = bufferQueue;
        this.name = name;
    }

    public void stopConsuming() {
        shouldConsume.set(false);
    }

    @Override
    public void run() {
        System.out.println(String.format("*** Consumer thread %s starting ***", name));
        while (shouldTryToConsume()) {
            consume();
        }
        System.out.println(String.format("*** Consumer thread %s stopping ***", name));
    }

    private boolean shouldTryToConsume() {
        return shouldConsume.get() || bufferQueue.resourceExists();
    }

    private void consume() {
        Optional<T> object = bufferQueue.removeFromQueue(name);
        object.ifPresent(executeResource());
    }

    private Consumer<T> executeResource() {
        return Resource::execute;
    }
}
