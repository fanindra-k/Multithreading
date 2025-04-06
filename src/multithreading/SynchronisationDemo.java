package multithreading;
class Counter{
    private int counter=0;
    private int sum = 0;
    public synchronized void increment(){
        this.counter++;
        sum+=2;
    }
    public  void increment1(){
        synchronized (this) {
            this.counter++;
        }
        sum+=2;
    }
    public int getCounter(){
        return this.counter;
    }
    public int getSum(){
        return this.sum;
    }
}
class MyThread extends Thread{
    private Counter counter;
    public MyThread(Counter counter){
        this.counter = counter;
    }
    public void run(){
        for(int i= 0; i<1000; i++){
//            counter.increment();
            counter.increment1();
        }

    }
}
public class SynchronisationDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new MyThread(counter);
        Thread t2 = new MyThread(counter);
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(counter.getCounter());
        System.out.println(counter.getSum());
    }
}
