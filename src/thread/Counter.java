package thread;

public class Counter {
    private Long value = 0L;

    // method to increment the value by one - not thread safe
    public void increment() {
        value++;
    }

    // method to increment the value by one - thread safe
    // to ensure our method is thread safe
    // we should add synchronized
    // synchronized guarantees only one execution happens at the same time
    // below is an example synchronized for the whole method
    public synchronized void incrementThreadSafe() {
        value++;

        // or we could synchronized for certain lines
        // synchronized (this) {
        //    value++;
        // }
    }

    // getter
    public Long getValue() {
        return value;
    }
}
