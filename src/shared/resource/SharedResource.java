package shared.resource;

import java.util.Random;

public class SharedResource extends Resource {

    @Override
    public void execute() {
        try {
            sleepThread();
        } catch (InterruptedException e) {
            catchException(e);
        }
    }

    private void catchException(InterruptedException e) {
        e.printStackTrace();
    }

    private void sleepThread() throws InterruptedException {
        Random r = new Random();
        Thread.sleep(r.nextInt(10000));
    }
}
