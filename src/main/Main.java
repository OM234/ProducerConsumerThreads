package main;

import shared.bufferQueue.BufferQueue;
import shared.resource.SharedResource;
import shared.resource.factory.SharedResourceFactory;

public class Main {
    public static void main(String[] args) {
        BufferQueue<SharedResource> bufferQueue = new BufferQueue<>();
        SharedResourceFactory factory = new SharedResourceFactory();
        ThreadsBuilder threadsBuilder = new ThreadsBuilder(bufferQueue, factory);
        threadsBuilder.start();
    }

}
