package thread;

import java.util.concurrent.*;

public class SynchronizerApp {
    public static void main(String[] args) {
        // classes for synchronizer :
        // 1. semaphore
        // 2. countDownLatch
        // 3. cyclicBarrier
        // 4. phaser
        // 5. exchanger

        // semaphore();
        // countDownLatch();
        // cyclicBarrier();
        // phaser();
        // exchanger();
    }

    private static void semaphore() {
        // Semaphore for managing a counter
        // When the semaphore reaches a certain number, all threads beyond that limit will wait

        Semaphore semaphore = new Semaphore(10);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("Finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Release the acquired permit
                    semaphore.release();
                }
            });
        }
    }

    private static void countDownLatch() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("DO SOME TASKS");
                    Thread.sleep(3000);
                    System.out.println("TASKS FINISHED");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // decrement the latch count after task completion
                    countDownLatch.countDown();
                }
            });
        }

        // Create a waiting task that proceeds once the latch count becomes zero
        executorService.execute(() -> {
            try {
                System.out.println("START WAITING");
                // await is a blocking call and only continues when the counter is zero
                countDownLatch.await();
                System.out.println("START DOING");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void cyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("WAITING");
                    Thread.sleep(1000);
                    // This await call is a blocking call and will only be released once a certain number (in this case, 5) of threads meet
                    // If there are fewer than 5 threads, all threads will wait
                    cyclicBarrier.await();
                    System.out.println("TASKS FINISHED");
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void phaser() {
        // count down behavior
        Phaser countDownPhaser = new Phaser();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // add 10 to phaser
        countDownPhaser.bulkRegister(5);
        countDownPhaser.bulkRegister(5);

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("Start task");
                    Thread.sleep(1000);
                    System.out.println("Finish task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // each arrive will decrement the phaser
                    countDownPhaser.arrive();
                }
            });
        }

        executorService.execute(() -> {
            System.out.println("Start waiting");
            // awaitAdvance is a blocking method and will keep waiting until match certain number in this case 0
            countDownPhaser.awaitAdvance(0);
            System.out.println("All tasks done");
        });

        // cyclic barrier behavior
        Phaser cyclicBarrierPhaser = new Phaser();
        cyclicBarrierPhaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println("Start task cyclic");
                    Thread.sleep(1000);
                    // arriveAndAwaitAdvance is a blocking call; all threads will keep waiting
                    // and only continue once a certain number of threads meet the barrier
                    cyclicBarrierPhaser.arriveAndAwaitAdvance();
                    System.out.println("Finish task cyclic");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void exchanger() {
        Exchanger<String> exchanger = new Exchanger<String>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Thread 1 sends "first" and receives "second"
        executorService.execute(() -> {
            try {
                System.out.println("Thread 1 sending first");
                Thread.sleep(1000);
                // exchanger requires 2 threads to exchange data
                // exchange operation is a blocking call and keep waiting until both threads submit their data
                String exchangeResult = exchanger.exchange("first");
                // Thread 1 receive second
                System.out.println("Thread 1 receive " + exchangeResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread 2 sends "second" and receives "first"
        executorService.execute(() -> {
            try {
                System.out.println("Thread 2");
                Thread.sleep(3000);
                String exchangeResult = exchanger.exchange("second");
                // Thread 2 receive first
                System.out.println("Thread 2 : " + exchangeResult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

