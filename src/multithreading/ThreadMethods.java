package multithreading;
/*
start() - Starts the execution of the thread by invoking its run() method in a new thread of execution.
sleep(long millis) -- Pauses the current thread for the specified number of milliseconds.

yield() -- Temporarily pauses the current thread and allows other threads of the same priority to execute.
interrupt() -- Interrupts the thread. If the thread is sleeping or waiting, it wakes up and throws an InterruptedException
join() -- Causes the current thread to wait until the thread on which it is called finishes executing.
setPriority(int newPriority) --Sets the priority of the thread. Valid values are between Thread.MIN_PRIORITY (1) and Thread.MAX_PRIORITY (10).
getPriority() -- Returns the priority of the thread.
setName(String name) -- Sets the name of the thread
and so on
 */
public class ThreadMethods extends Thread{

    public ThreadMethods(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        System.out.println( "PRIORITY OF : "+Thread.currentThread().getName()+" "+Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        Thread t1 = new ThreadMethods("Low Priority");
        Thread t2 = new ThreadMethods("Medium Priority");
        Thread t3 = new ThreadMethods("High Priority");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }
}
