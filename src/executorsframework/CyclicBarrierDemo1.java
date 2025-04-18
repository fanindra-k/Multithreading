package executorsframework;

import java.util.concurrent.*;

class DependentService3 implements Callable<String> {
    private final CyclicBarrier barrier;

    public DependentService3(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println(Thread.currentThread().getName() + " service started");
            Thread.sleep(2000);  // Simulate some work (like calling an external service)
            System.out.println(Thread.currentThread().getName() + " is waiting at barrier");

        } finally {
            barrier.await();  // Decrement the latch count after this service finishes
        }
        return "OK";
    }
}
public class CyclicBarrierDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int numberOfServices = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CyclicBarrier barrier = new CyclicBarrier(numberOfServices);  // Initialize latch with number of tasks

        // Submit 3 services that will complete independently
        executorService.submit(new DependentService3(barrier));
        executorService.submit(new DependentService3(barrier));
        executorService.submit(new DependentService3(barrier));

        /**
         * latch.await():
         * - Causes the current thread (main thread) to **wait** until the latch count reaches zero.
         * - Here, main thread will wait until all 3 DependentService1 tasks call `latch.countDown()`.
         * - Each worker thread calls `latch.countDown()` once it finishes.
         * - When latch count becomes zero, `await()` unblocks, and main thread continues execution.
         */

        System.out.println("Main"); // Will be printed **only after** all services have completed

        executorService.shutdown();
    }
}
