package executorsframework;

import java.util.concurrent.*;

public class FutureDemo2 {
    public static void main(String[] args) {
        // Create an ExecutorService with a single worker thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Submit the first task: immediately returns 2
        Future<Integer> future1 = executorService.submit(() -> 2);

        // Submit the second task: sleeps for 3 seconds before returning 42
        Future<Integer> future2 = executorService.submit(() -> {
            try {
                Thread.sleep(3000); // Simulate long-running task
                return 42;
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e); // Rethrow as unchecked exception
            }
        });

        Integer i = null;
        try {
            // Get the result of future1 (will complete immediately)
            i = future1.get();
            System.out.println(i); // Output: 2
            System.out.println(future1.isDone()); // Output: true (since task is done)
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Demonstrating time based get() method");

        Integer j = null;
        try {
            // Try to get the result of future2 within 1 second
            // Since future2 sleeps for 3 seconds, this will timeouts
            j = future2.get(1, TimeUnit.SECONDS);
            System.out.println(j);
            System.out.println(future2.isDone());
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            // TimeoutException will be thrown here
            // e.getMessage() might be null, better to use e.printStackTrace() to see the actual exception
            e.printStackTrace();
        }

        // Shut down the executor service properly
        executorService.shutdown();
    }
}
