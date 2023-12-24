package thread;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadApp {
    public static void main(String[] args) throws InterruptedException {
        // System.out.println("Thread in low level");
        // basic();

        // demo interrupt thread
        // System.out.println("Thread interrupt");
        // threadInterrupt();

        // race condition and the solutions
        // System.out.println("Race condition and the solutions");
        // raceCondition();

        // wait and notify
        // waitAndNotify();

        // periodic timer job
        // periodicJob();

        // System.out.println("Thread in high level using concurrency utilities");
        // thread pool
        // threadPool();

        // executor service
        // executorService();

        // completable future
        // completableFuture();

        // delayed job and periodic executor
        // periodicExecutor();
    }

    private static void basic() throws InterruptedException {
        // Thread (worker) is an execution unit to process a specific task
        // Concurrency is an ability to manage and progress multiple threads or workers, but only one thread runs at any given time.
        // Parallelism is an ability to run more than 1 thread or worker at the same time.
        // Synchronous means the process is blocking, the next task only can be executed after the current task is done
        // Asynchronous means the process is not blocking, tasks can run without waiting for each other.

        // initiate a new thread
        // but a thread without runnable will stand still
        // like a worker without a specific assignment will stand still
        Thread thread = new Thread();

        // that's why we need runnable
        Runnable runnable = () -> {
            System.out.println("This task is executed by : " + Thread.currentThread().getName());
        };

        // the runnable will be assigned to a thread or worker
        thread = new Thread(runnable);

        // to trigger the task execution use start()
        thread.start();

        // join() method for blocking purpose
        // the next task after this line will only be executed
        // after the thread has finished the current task
        thread.join();
    }

    private static void threadInterrupt() throws InterruptedException {
        Runnable runnable = () -> {
            for (int counter = 0; counter < 10; counter++) {
                try {
                    System.out.println("This " + counter + " task is executed by : " + Thread.currentThread().getName());
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // this return is a must to force stop the execution
                    // after we received an interrupt signal
                    return;
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000L);
        // without interrupt, the program will print 10 times, each print to the next print will take 1 second
        // with interrupt, since we wait for 5 seconds, we should expect to see task 1 to 5 gets printed, but not the 6th task
        thread.interrupt();
    }

    private static void raceCondition() throws InterruptedException {
        // race condition
        Counter counter = new Counter();

        Runnable runnable = () -> {
            for (int i = 0; i < 1_000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        // We should expect 3000
        // But since there is race condition happens
        // We only get 2245 instead of 3000
        System.out.println(counter.getValue());

        // solution 1 : synchronized
        // to address race condition
        // we could use add synchronized the increment method

        Counter counterThreadSafe = new Counter();

        Runnable runnableThreadSafe = () -> {
            for (int i = 0; i < 1_000; i++) {
                counterThreadSafe.incrementThreadSafe();
            }
        };

        Thread thread4 = new Thread(runnableThreadSafe);
        Thread thread5 = new Thread(runnableThreadSafe);
        Thread thread6 = new Thread(runnableThreadSafe);

        thread4.start();
        thread5.start();
        thread6.start();

        thread4.join();
        thread5.join();
        thread6.join();

        // here we get 3000
        // meaning race condition could be solved using synchronized
        System.out.println("Synchronized : " + counterThreadSafe.getValue());

        // solution 2 : atomic
        // Java provides atomic package that contains class that supports lock-free and thread-safe for variable
        // By using below atomic variable, doing synchronized manually is no longer needed
        // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html

        CounterAtomic counterAtomic = new CounterAtomic();

        Runnable runnableAtomic = () -> {
            for (int i = 0; i < 1_000; i++) {
                counterAtomic.increment();
            }
        };

        Thread threadAtomic1 = new Thread(runnableAtomic);
        Thread threadAtomic2 = new Thread(runnableAtomic);
        Thread threadAtomic3 = new Thread(runnableAtomic);

        threadAtomic1.start();
        threadAtomic2.start();
        threadAtomic3.start();

        threadAtomic1.join();
        threadAtomic2.join();
        threadAtomic3.join();

        System.out.println("Atomic : " + counterAtomic.getValue());

        // solution 3 : using reentrant lock in the increment setter
        CounterLock counterLock = new CounterLock();

        Runnable runnableLock = () -> {
            for (int i = 0; i < 1_000; i++) {
                counterLock.increment();
            }
        };

        Thread threadLock1 = new Thread(runnableLock);
        Thread threadLock2 = new Thread(runnableLock);
        Thread threadLock3 = new Thread(runnableLock);

        threadLock1.start();
        threadLock2.start();
        threadLock3.start();

        threadLock1.join();
        threadLock2.join();
        threadLock3.join();

        System.out.println("Lock : " + counterLock.getValue());
    }

    private static String message = null;
    private static void waitAndNotify() throws InterruptedException {
        Object lock = new Object();

        System.out.println("Solution 1 : Handle lock using object manually using notify and wait");
        Thread thread1 = new Thread(() -> {
            synchronized(lock) {
                try {
                    // the wait method is blocking and will wait until another thread calls notify
                    lock.wait();
                    // this println method only gets executed after another thread calls notify
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread thread2 = new Thread(() -> {
            synchronized(lock) {
                try {
                    // Simulate some work before updating the shared variable
                    Thread.sleep(2000L);
                    message = "The value is updated";
                    // notify method sends signal to another thread to stop waiting and could continue the execution
                    // but if there is more than 1 thread waiting, notify will only release one thread
                    lock.notify();
                    // to release all threads that are waiting we could use notifyAll
                    // lock.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Solution 2 :  lock using ReentrantLock using await and signal");
        Lock lockObject = new ReentrantLock();
        Condition condition = lockObject.newCondition();

        Thread threadLock1 = new Thread(() -> {
            try {
                lockObject.lock();
                Thread.sleep(2000);
                message = "Latest update";
                // signal method sends signal to another thread to stop waiting and could continue the execution
                // but if there is more than 1 thread waiting, signal will only release one thread
                // condition.signal();

                // To release all waiting threads, use signalAll
                condition.signalAll();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } finally {
                lockObject.unlock();
            }
        });

        Thread threadLock2 = new Thread(() -> {
            try {
                lockObject.lock();
                // await is a blocking call, will keep waiting until it receives signal / signalAll
                condition.await();
                System.out.println("Thread 2 " + message);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } finally {
                lockObject.unlock();
            }
        });

        Thread threadLock3 = new Thread(() -> {
            try {
                lockObject.lock();
                // await is a blocking method, and will keep waiting until receive signal / signalAll
                condition.await();
                System.out.println("Thread 3 " + message);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } finally {
                lockObject.unlock();
            }
        });

        threadLock2.start();
        threadLock3.start();
        // It is important to place threadLock1 at the end to ensure that it signals after other threads start waiting
        // If placed at the top, threadLock1 might signal before other threads start waiting, leading to missed signals
        threadLock1.start();

        threadLock1.join();
        threadLock2.join();
        threadLock3.join();
    }

    private static int counter = 0;
    private static void periodicJob() throws InterruptedException {
        Timer timer = new Timer();


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Scheduler job");
                counter++;
                if (counter >= 5) {
                    timer.cancel();
                }
            }
        };

        // Schedule the TimerTask to run after an initial delay of 1000 milliseconds (1 second)
        // and then repeatedly every 1000 milliseconds (1 second)
        timer.schedule(timerTask, 1000, 1000);
    }

    private static void threadPool() throws InterruptedException {
        // thread pool - for thread management, and reuse thread for next task execution

        // core pool - minimum thread that will be created
        int minThread = 10;
        // max pool - maximum thread that will be created
        int maxThread = 100;
        // keep alive time - how long an idle thread stay before gets removed
        int alive = 1;
        TimeUnit aliveTimeunit = TimeUnit.MINUTES;
        // queue - maximum number of task queueing and  waiting for execution
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(1000);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTimeunit, arrayBlockingQueue);

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Running" + Thread.currentThread().getName());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        // execute method needs runnable for task assignment
        // the method will trigger the execution of the task assignment
        for (int i=0; i<100; i++) {
            executor.execute(runnable);
        }

        // Shutdown() method will stop the thread pool immediately
        // All threads that were running and had not finished yet get terminated
        // executor.shutdown();

        // shutdownNow() method will stop the thread pool immediately
        // All threads that were running and had not finished yet get terminated
        // But the difference is shutdownNow returns a list of unfinished threads
        // List<Runnable> unfinishedRunnable = executor.shutdownNow();
        // System.out.println(unfinishedRunnable);

        // awaitTermination() will stop the thread pool only once all the thread have been executed
        // executor.awaitTermination(1, TimeUnit.HOURS);

        // When the queue pool is full, then the next request comes in will get rejected
        // But we can implement custom exception handling
        LogRejectedExecutionHandler handler = new LogRejectedExecutionHandler();
        ThreadPoolExecutor rejectedExecutionExecutor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTimeunit, arrayBlockingQueue, handler);

        // for (int i=0; i<10000; i++) {
        //     rejectedExecutionExecutor.execute(runnable);
        // }
    }

    private static void executorService() {
        // in previous method threadPool()
        // we create thread pool manually, but actually we could create thread pool using executor service
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Running" + Thread.currentThread().getName());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        // create single (one) thread executor
        // ExecutorService executorService = Executors.newSingleThreadExecutor();

        // create a pool with the min and max is 10
        // but the problem here is the queue poll has no limit when using ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(runnable);
        }

        // Callable
        // Almost the same with Runnable, but Callable could return data
        Callable<String> callable = () -> {
            Thread.sleep(3000);
            return "This is the response";
        };
        // and use submit method to execute callable
        Future<String> future = executorService.submit(callable);
        try {
            String resp = future.get();
            System.out.println(resp);

            // isDone to check if the future has finished the task
            // boolean isDone = future.isDone();
            // to trigger cancel
            // future.cancel(true);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        // Multiple callables
        List<Callable<String>> callableList = List.of(callable);
        try {
            // InvokeAll will execute all callables, and the futureList will wait all the response
            List<Future<String>> futureList = executorService.invokeAll(callableList);
            for (Future<String> f : futureList) {
                System.out.println(f.get());
            }

            // InvokeAny will execute all callables, but the future only expect one response, which is the fastest one
            String fastestFuture = executorService.invokeAny(callableList);
            System.out.println(fastestFuture);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void completableFuture() {
        // Getting future using completable future
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = new CompletableFuture<>();

        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                future.complete("Success");
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
                e.printStackTrace();
            }
        });

        try {
            String response = future
                    .thenApply(resp -> resp.toUpperCase())
                    .thenApply(resp -> "NOT " + resp)
                    .get();
            System.out.println(response);
        } catch (InterruptedException | ExecutionException ex) {

        }
    }

    private static void periodicExecutor() {
        // delayed job
        // only run once in the future
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        executorService.schedule(() -> System.out.println("This is scheduled job"), 5, TimeUnit.SECONDS);

        // periodic job
        // will keep executing repetitively every 5 seconds
        int initialDelay = 5;
        int period = 5;
        executorService.scheduleAtFixedRate(() -> System.out.println("This is periodic job"), initialDelay, period, TimeUnit.SECONDS);
    }
}
