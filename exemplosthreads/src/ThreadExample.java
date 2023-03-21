import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadExample extends Thread {
    String nome;

    public ThreadExample(String nome) {
        this.nome = nome;
    }
    
    public void run() {
        int i = 10;
        do {
            System.out.println("In Run: " + nome + " Contador: " + i);
            i--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Erro");
            }
        }while(i>0); 
    }

    public static void main(String args[]) {
        ThreadExample t1 = new ThreadExample("teste");
        ThreadExample t2 = new ThreadExample("sim");
        t1.start(); t2.start();
        
        try { 
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            System.out.println("Erro");
        }
        
        System.out.println("Main ended...");
        //a ordem que as threads sao executadas é definida pelo sistema...
    }
}
