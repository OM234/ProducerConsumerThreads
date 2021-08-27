package shared.bufferQueue;

import shared.resource.Resource;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class BufferQueue<T extends Resource> {

    private Queue<T> sharedResourceQueue = new LinkedList<>();

    public void addToQueue(T object) {
        sharedResourceQueue.add(object);
        System.out.println(String.format("Producer added to Queue. New size: %d", sharedResourceQueue.size()));
    }

    synchronized public Optional<T> removeFromQueue(String name) {
        if (resourceExists()) {
            return returnResource(name);
        } else {
            return returnNoResource();
        }
    }

    public boolean resourceExists() {
        return !sharedResourceQueue.isEmpty();
    }

    private Optional<T> returnNoResource() {
        return Optional.empty();
    }

    private Optional<T> returnResource(String name) {
        Optional<T> resource = Optional.of(sharedResourceQueue.remove());
        System.out.println(String.format("Thread %s removed from Queue. New size: %d", name, sharedResourceQueue.size()));
        return resource;
    }
}
