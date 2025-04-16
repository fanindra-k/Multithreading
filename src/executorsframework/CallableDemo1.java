package executorsframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /*
            Lambda Expressions and Task Types:
                - If the lambda expression has no return value, it’s treated as a Runnable.
                - If the lambda expression returns a value, it’s treated as a Callable.
         */
        Future<?> future1 =executorService.submit(()-> System.out.println("Hello Gouri !! from Runnable")); // Interpreted as Runnable
        Future<?> future2 =executorService.submit(()-> "Hello Gouri !! from Callable"); // Interpreted as Callable
        try {
            System.out.println(future1.get()); // Output will be null because Runnable does not return a value.
            System.out.println(future2.get()); //  Output will be 'Hello from Callable' because it's a Callable.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();

    }
}
