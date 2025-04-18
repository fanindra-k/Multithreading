package executorsframework;

import java.util.concurrent.*;

// A service that depends on some operation to be completed
class DependentService1 implements Callable<String> {
    private final CountDownLatch latch;

    public DependentService1(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println(Thread.currentThread().getName() + " service started");
            Thread.sleep(2000);  // Simulate some work (like calling an external service)
        } finally {
            latch.countDown();  // Decrement the latch count after this service finishes
        }
        return "OK";
    }
}

public class CountDownLatchDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int numberOfServices = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);  // Initialize latch with number of tasks

        // Submit 3 services that will complete independently
        executorService.submit(new DependentService1(latch));
        executorService.submit(new DependentService1(latch));
        executorService.submit(new DependentService1(latch));

        /**
         * latch.await():
         * - Causes the current thread (main thread) to **wait** until the latch count reaches zero.
         * - Here, main thread will wait until all 3 DependentService1 tasks call `latch.countDown()`.
         * - Each worker thread calls `latch.countDown()` once it finishes.
         * - When latch count becomes zero, `await()` unblocks, and main thread continues execution.
         */
        latch.await();

        System.out.println("Main"); // Will be printed **only after** all services have completed

        executorService.shutdown();
    }
}
