package multithreading;

public class LockDemo {
    public static void main(String[] args) {
//        withdrawSynchronized();
        withdrawLock();
    }
    public static void withdrawLock(){
        MyBankAccount bankAccount = new MyBankAccount();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                bankAccount.withdrawWithLock(50);
            }
        };
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        t1.start();
        t2.start();
    }
    public static void withdrawSynchronized(){
        MyBankAccount bankAccount = new MyBankAccount();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                bankAccount.withdraw(50);
            }
        };
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread daemon = new Thread(()->{
            while(t1.isAlive() || t2.isAlive()){
                try {
                    System.out.println("Thread 1 status: " + t1.getState());
                    System.out.println("Thread 2 status: " + t2.getState());
                    Thread.sleep(1000); // Print every second
                } catch (InterruptedException e) {
                    // Handle the exception if the daemon thread is interrupted
                    e.printStackTrace();
                }
            }
            System.out.println("Daemon thread is existing ...");
        });
        daemon.setDaemon(true);
        t1.start();
        t2.start();
        daemon.start();
    }

}
