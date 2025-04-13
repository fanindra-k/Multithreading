package multithreading;

public class LambdaExpression {
    public static void main(String[] args) {
        /*
          In Java, the Runnable interface is a **functional interface**, which means it contains
          exactly one abstract method — `run()`.

          Functional interfaces can be implemented using **lambda expressions**, which are a concise way
          to represent an anonymous function (i.e., a function without a name).

          Traditional approach (anonymous inner class):

          Runnable runnable = new Runnable() {
             @Override
              public void run() {
                  System.out.println("Hello");
              }
          };

          With lambda expression, we can simplify it. To convert to a lambda:
          - Remove the access modifier (`public`)
          - Remove the return type (`void`)
          - Remove the method name (`run`)
          - Add -> between parameter and function body
          The lambda automatically represents the abstract method of Runnable.
         */

        // Lambda expression implementation of Runnable
        Runnable runnable = () -> {
            System.out.println("Hello");
        };
        /*
            How Can We Store an anonymous function in a Runnable Variable?
                Because:
                    - A lambda expression is treated as an instance of the functional interface it's being assigned to.
                    - Runnable is the type, and the lambda provides the method implementation (run() in this case).
                    - So you're not storing a function; you're storing an object that implements Runnable.
         */
        // Creating and starting a thread using the Runnable
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
