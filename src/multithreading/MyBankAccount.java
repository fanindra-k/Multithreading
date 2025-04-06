package multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBankAccount {
    private int balance = 100;
    private final Lock lock = new ReentrantLock();

    public void withdrawWithLock(int amount){
        System.out.println( Thread.currentThread().getName()+ " is attempting to acquire lock");
        try{
            if(lock.tryLock(3000, TimeUnit.MILLISECONDS)){
                System.out.println("Your transaction is being processed by " + Thread.currentThread().getName());
                if(balance>= amount){
                    try{
                        Thread.sleep(2000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() +" successfully completed transaction");
                        System.out.println("Remaining balance : "+balance);
                    }
                    catch (InterruptedException e){
                        System.out.println(e.getMessage());
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        lock.unlock();
                    }
                }
                else{
                    System.out.println("Insufficient balance "+Thread.currentThread().getName());
                }
            }
            else{
                System.out.println(Thread.currentThread().getName()+" please try again after sometime");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public synchronized void withdraw(int amount){
        System.out.println("Your transaction is being processed by " + Thread.currentThread().getName());
        if(balance>= amount){
            try{
                Thread.sleep(10000);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() +" successfully completed transaction");
            System.out.println("Remaining balance : "+balance);
        }
        else{
            System.out.println("Insufficient balance "+Thread.currentThread().getName());
        }
    }
}
