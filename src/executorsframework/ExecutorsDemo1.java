package executorsframework;
/*
    Purpose: To create and manage threads for parallel execution of factorial calculations for numbers 0 through 8.

    Main functionality:
        - It creates 9 threads.
        - Each thread computes the factorial of a number and prints the result.
        - The main thread waits for all threads to finish before printing the total execution time.
 */
public class ExecutorsDemo1 {
    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[9];
        for(int i=0; i<9; i++){
            int num = i;
            threads[i] = new Thread(
                    ()->{
                        long result = factorial(num);
                        System.out.println(result);
                    }
            );
            threads[i].start();
        }
        for(Thread thread : threads){
            try{
                thread.join();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
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
