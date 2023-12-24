package thread;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ForkJoinApp {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // total CPU core parallelism
        // let's say our computer has 10 core, then we could expect there are 10 workers run in parallel
        // ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        // ExecutorService executorService = Executors.newWorkStealingPool();

        // if we want to custom the number of CPU core, use below code
        // ForkJoinPool customForkJoinPool = new ForkJoinPool(5);
        // ExecutorService customExecutorService = Executors.newWorkStealingPool(5);

        // recursive action
        // create 1000 integers, and keep forking
        // until each of the forked list size is less or equal than 10
        // then println
        recursiveAction();

        // need some return
        recursiveTask();
    }

    private static void recursiveAction() throws InterruptedException {
        ForkJoinPool customForkJoinPool = new ForkJoinPool(5);

        // generate 1000 integers
        List<Integer> integers = IntStream.range(0, 1000).boxed().collect(Collectors.toList());

        ForkJoinActionWorker worker = new ForkJoinActionWorker(integers);

        customForkJoinPool.execute(worker);
        customForkJoinPool.shutdown();
        customForkJoinPool.awaitTermination(1, TimeUnit.DAYS);
    }

    private static void recursiveTask() throws InterruptedException, ExecutionException {
        ForkJoinPool customForkJoinPool = new ForkJoinPool(5);

        // generate 1000 integers
        List<Integer> integers = IntStream.range(0, 1000).boxed().collect(Collectors.toList());

        ForkJoinTaskWorker worker = new ForkJoinTaskWorker(integers);
        ForkJoinTask<Long> submit = customForkJoinPool.submit(worker);

        Long result = submit.get();
        System.out.println(result);

        customForkJoinPool.shutdown();
        customForkJoinPool.awaitTermination(1, TimeUnit.DAYS);
    }
}
