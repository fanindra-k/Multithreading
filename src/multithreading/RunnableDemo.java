package multithreading;

public class RunnableDemo {

    public static void main(String[] args) {
//        Runnable r = ()-> System.out.println("Creating Thread");
//        new Thread(r).start();
        Thread thread = new Thread(()-> System.out.println("Creating Thread"));
        thread.start();
    }
}
