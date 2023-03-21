public class RunnableExample2 implements Runnable {
  RunnableExample2(){
  	new Thread(this).start();
  }
  public void run() {
    System.out.println("In Run");
  }

  public static void main(String args[]) {
    RunnableExample2 re = new RunnableExample2();
  }
}
