package multithreading;


class Pen{
    public synchronized  void writeWithPenAndPaper(Paper paper){
        System.out.println(Thread.currentThread().getName()+" is using pen "+ this + " and trying to write on paper");
        paper.finishWriting();
    }
    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName()+ " finished using pen "+ this);
    }
}
class Paper{
    public synchronized  void writeWithPenAndPaper(Pen pen){
        System.out.println(Thread.currentThread().getName()+" is using paper "+ this + " and trying to write with pen");
        pen.finishWriting();
    }
    public synchronized void finishWriting(){
        System.out.println(Thread.currentThread().getName()+ " finished using paper "+ this);
    }
}
class Task1 implements Runnable{
    private Pen pen;
    private Paper paper;

    public Task1(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run(){
        pen.writeWithPenAndPaper(paper);
    }
}
class Task2 implements Runnable{
    private Pen pen;
    private Paper paper;

    public Task2(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run(){
        paper.writeWithPenAndPaper(pen);
    }

    /*
    UNCOMMENT THIS CODE TO RESOLVE DEADLOCK
     */
//    @Override
//    public void run(){
//        synchronized (pen){
//            paper.writeWithPenAndPaper(pen);
//        }
//    }
}
public class DeadLock {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1 = new Thread(new Task1(pen, paper),"Thread 1");
        Thread t2 = new Thread(new Task2(pen, paper), "Thread 2");

        t1.start();
        t2.start();
    }
}
