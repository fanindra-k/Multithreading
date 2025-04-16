package executorsframework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Create an ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit multiple tasks and collect their Future objects
        Future<Integer> future1 = executorService.submit(() -> factorial(3));
        Future<Integer> future2 = executorService.submit(() -> factorial(4));
        Future<Integer> future3 = executorService.submit(() -> factorial(5));

        // Retrieve the results from the futures
        try {
            System.out.println("Factorial of 3: " + future1.get());
            System.out.println("Factorial of 4: " + future2.get());
            System.out.println("Factorial of 5: " + future3.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }


        // Shutdown the executor service
        executorService.shutdown();
    }

    // Simple factorial calculation method
    private static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
