package multithreading;

public class ThreadLifeCycle extends Thread{
    @Override
    public  void  run(){
        System.out.println("Hello Gouri ! Happy Birthday, wishing you all the happiness in the world");
        System.out.println("Current of Thread 0 is : "+Thread.currentThread().getState());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" starts execution");
        /*
           new Thread() → only creates Java object; start() → creates real system thread and runs the code in parallel.
         */
        Thread thread = new ThreadLifeCycle();
        System.out.println("Current of Thread 0 is : "+thread.getState());
        thread.start();
        Thread.sleep(1000);
        System.out.println("Current of Thread 0 is : "+thread.getState());
        thread.join(); // thread which is executing this line will wait to finish this execution
        System.out.println("Current of Thread 0 is : "+thread.getState());
    }
}
