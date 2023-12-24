package thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeDataType {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        // not thread safe data type
        Map<String, Integer> map = new HashMap<>();
        List<String> stringList = new ArrayList<>();

        // thread safe data type
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        List<String> synchronizedStringList = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 100; i++) {
            final Integer index = i;

            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                    // by utilizing count down latch
                    // there are 100 threads will insert the data at the same time
                    map.putIfAbsent("Data-" + index, index);
                    stringList.add("Data-" + index);

                    concurrentHashMap.putIfAbsent("Data-" + index, index);
                    synchronizedStringList.add("Data-" + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        executorService.execute(() -> {
            try {
                countDownLatch.await();
                // the result is 98 meaning there are 2 data is missing
                System.out.println("Normal hash map size : " + map.size());
                // the result is 100 meaning the data type is thread safe
                System.out.println("Concurrent hash map size : " + concurrentHashMap.size());

                // the result is 96 meaning there are 4 data is missing
                System.out.println("Normal array string list : " + stringList.size());
                // the result is 100 meaning the data type is thread safe
                System.out.println("Concurrent array string list : " + synchronizedStringList.size());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
