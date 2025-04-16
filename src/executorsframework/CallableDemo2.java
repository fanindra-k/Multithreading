package executorsframework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/*
    The invokeAll method is part of the ExecutorService interface in Java,
    and it's used to submit a collection of tasks (usually Callable tasks) to be executed concurrently.
    It’s particularly useful when you need to run multiple tasks and wait for all of them to complete.
 */
public class CallableDemo2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<Integer> callable1 = (() -> {
            Thread.sleep(1000);
            System.out.println("Task 1");
            return 1;
        });
        Callable<Integer> callable2 = (() -> {
            Thread.sleep(1000);
            System.out.println("Task 2");
            return 2;
        });
        Callable<Integer> callable3 = (() -> {
            Thread.sleep(3000);
            System.out.println("Task 3");
            return 3;
        });

        List<Callable<Integer>> callables = Arrays.asList(callable1, callable2, callable3);
        List<Future<Integer>> futures1 = null;
        List<Future<Integer>> futures2 = null;
        try {
            futures1 = executorService.invokeAll(callables);
            futures2 = executorService.invokeAll(callables, 1, TimeUnit.SECONDS);
            /*
            Without timeout: Waits for all tasks to complete, no matter how long it takes.
            With timeout: Waits only for the specified time; tasks that aren't finished in time are cancelled.
             */
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(Future<Integer> future : futures1){
            try {
                System.out.println(future.get());
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
            catch (ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }
        for(Future<Integer> future : futures2){
            try {
                System.out.println(future.get());
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
            catch (CancellationException e){
                System.out.println(e.getMessage());
            }
            catch (ExecutionException e) {
                System.out.println(e.getMessage());
            }

        }
        executorService.shutdown();

    }
}
