package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTaskWorker extends RecursiveTask<Long> {
    private List<Integer> integers = new ArrayList<>();

    public ForkJoinTaskWorker(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    protected Long compute() {
        // only execute the computation when the size of the integers is less than or equal to 10
        // else keep forking
        if (integers.size() <= 10) {
            return doCompute();
        } else {
            return forkCompute();
        }
    }

    private Long doCompute() {
        Long total = 0L;
        for (Integer value : integers) {
            total = total + value;
        }
        return total;
    }

    private Long forkCompute() {
        // split the list into 2 parts

        // this is the first half
        List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2);
        ForkJoinTaskWorker task1 = new ForkJoinTaskWorker(integers1);

        // this is the second half
        List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());
        ForkJoinTaskWorker task2 = new ForkJoinTaskWorker(integers2);

        ForkJoinTask.invokeAll(task1, task2);
        return task1.join() + task2.join();
    }
}
