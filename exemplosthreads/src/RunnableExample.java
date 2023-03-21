public class RunnableExample implements Runnable {
  public void run() {
    System.out.println("In Run");
  }

  public static void main(String args[]) {
    RunnableExample re = new RunnableExample();
    Thread t1 = new Thread(re);
    Thread t2 = new Thread(re);
    t1.start(); t2.start();
  }
}

