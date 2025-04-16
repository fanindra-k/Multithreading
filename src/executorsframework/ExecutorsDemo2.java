package executorsframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
    Purpose: To create and manage threads for parallel execution of factorial calculations for numbers 0 through 8.

    Main functionality:
        - It creates 9 threads.
        - Each thread computes the factorial of a number and prints the result.
        - The main thread waits for all threads to finish before printing the total execution time.
 */
public class ExecutorsDemo2 {
    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        for(int i=0; i<9; i++){
            int num = i;
            executorService.submit(()-> System.out.println(factorial(num)));
        }
        executorService.shutdown();
        try{
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total Time: "+(System.currentTimeMillis() - startTime));
    }

    private static long factorial(int n){
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        long res = 1;
        for(int i=1; i<=n; i++){
            res *= i;
        }
        return res;
    }
}
