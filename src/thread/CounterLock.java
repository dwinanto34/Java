package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterLock {
    private final Lock lock = new ReentrantLock();

    // Additionally, there is an option for a ReadWriteLock
    // The ReadWriteLock differentiates between locks for read access and write access.
    // When multiple threads are reading concurrently, using a ReadWriteLock may improve
    // performance by allowing concurrent reads without blocking each other.
    // private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Long value = 0L;

    public void increment() {
        try {
            // lock methos is blocking and will wait until the lock is unlocked
            lock.lock();
            // readWriteLock.writeLock().lock();
            value++;
        } finally {
            lock.unlock();
            // readWriteLock.writeLock().unlock();
        }
    }

    public Long getValue() {
        try {
            // readWriteLock.readLock().lock();
            return value;
        } finally {
            // readWriteLock.readLock().unlock();
        }
    }
}
