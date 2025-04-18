package executorsframework;

import java.util.concurrent.*;

public class ScheduledExecutorDemo1 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(1);
        scheduler1.schedule(
                () -> System.out.println("Task Executed after 5 second delay !"),
                5,
                TimeUnit.SECONDS
        );
        scheduler1.shutdown();
        System.out.println("Demonstrating Schedule at fixed rate");
        ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);
        scheduler2.scheduleAtFixedRate(
                () -> System.out.println("Task Executed after every 5 second delay !"),
                5,
                5,
                TimeUnit.SECONDS
        );
        scheduler2.schedule(()-> {
            System.out.println("Initiating shoutdown....");
            scheduler2.shutdown();
        }, 20, TimeUnit.SECONDS);
//         Demonstrating schedule with fixed delay
        ScheduledExecutorService scheduler3 = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> future = scheduler3.scheduleWithFixedDelay(
                () -> System.out.println("Task Executed after every 5 second delay !"),
                5,
                5,
                TimeUnit.SECONDS
        );
        scheduler3.schedule(()-> {
            System.out.println("Initiating shoutdown....");
            scheduler3.shutdown();
        }, 20, TimeUnit.SECONDS);
        System.out.println("Schedule with fixed delay is completed : "+future.isDone());
        try {
            System.out.println(future.get(25, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
