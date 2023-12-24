package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ForkJoinActionWorker extends RecursiveAction {
    private List<Integer> integers = new ArrayList<>();

    public ForkJoinActionWorker(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    protected void compute() {
        // only execute the println when the size of the integers is less than or equal to 10
        // else keep forking
        if (integers.size() <= 10) {
            doExecute();
        } else {
            forkCompute();
        }
    }

    private void doExecute() {
        for (Integer value : integers) {
            System.out.println(Thread.currentThread().getName() + " : " + value);
        }
    }

    private void forkCompute() {
        // split the list into 2 parts

        // this is the first half
        List<Integer> integers1 = this.integers.subList(0, this.integers.size() / 2);
        ForkJoinActionWorker task1 = new ForkJoinActionWorker(integers1);

        // this is the second half
        List<Integer> integers2 = this.integers.subList(this.integers.size() / 2, this.integers.size());
        ForkJoinActionWorker task2 = new ForkJoinActionWorker(integers2);

        List<ForkJoinTask<Void>> tasks = List.of(task1, task2);
        ForkJoinTask.invokeAll(tasks);
    }
}
