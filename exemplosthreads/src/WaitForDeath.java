public class WaitForDeath extends Thread
{
	public void run()
	{
		System.out.println ("This thread will sleep....");
		try
		{ 
			Thread.sleep(5000);
		}
		catch (InterruptedException ie) {}
	}


	public static void main(String args[]) throws InterruptedException
	{
		Thread dying = new WaitForDeath();
		dying.start();
		System.out.println ("Waiting for thread death");
		dying.join();
		System.out.println ("Thread has died");
	}
}