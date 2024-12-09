package thread;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadApp {
    // A conventional thread is an OS-level thread, which consumes significant memory resources.
    // From now on, conventional thread is called Platform Thread.

    // Virtual threads were introduced in Java 21.
    // In contrast, a virtual thread is lightweight and more resource-efficient.
    public static void main(String[] args) throws IOException {
        // platformThread();
        // virtualThread();
        // threadExecutor();
    }

    private static void platformThread() throws IOException {
        for (int i = 0; i < 20_000; i++) {
            Thread thread = Thread.ofPlatform().name("test-platform-" + i).daemon(false).unstarted(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(3));
                    System.out.println("hello-platform-" + Thread.currentThread());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            thread.start(); // Output: unable to create native thread: possibly out of memory or process/resource limits reached
        }

        System.in.read();
    }

    private static void virtualThread() throws IOException {
        for (int i = 0; i < 20_000; i++) {
            Thread thread = Thread.ofVirtual().name("test-virtual-" + i).unstarted(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(3));
                    System.out.println("hello-virtual-" + Thread.currentThread());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            thread.start();
        }

        System.in.read();
    }

    private static void threadExecutor() {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Running" + Thread.currentThread().getName());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        ExecutorService platformThreadExecutorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            platformThreadExecutorService.execute(runnable);
        }

        ExecutorService virtualThreadExecutorService = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 100; i++) {
            virtualThreadExecutorService.execute(runnable);
        }
    }
}
