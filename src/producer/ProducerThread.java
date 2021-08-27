package producer;

import shared.bufferQueue.BufferQueue;
import shared.resource.Resource;
import shared.resource.factory.ResourceFactory;

import java.util.Random;

public class ProducerThread<T extends Resource> extends Thread {

    final BufferQueue<T> bufferQueue;
    final ResourceFactory<T> factory;
    final private int NUM_RESOURCES = 30;

    public ProducerThread(BufferQueue<T> bufferQueue, ResourceFactory<T> factory){
        this.bufferQueue = bufferQueue;
        this.factory = factory;
    }

    @Override
    public void run() {
        System.out.println("*** Producer thread starting ***");
        for(int resource = 0; resource < NUM_RESOURCES; resource++) {
            addToBuffer();
            sleepThread();
        }
        System.out.println("*** Producer thread stopping ***");
    }

    public void addToBuffer() {
        bufferQueue.addToQueue(factory.produce());
    }

    private void sleepThread() {
        Random r = new Random();
        try {
            Thread.sleep(r.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
