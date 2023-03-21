public class SynchBlock implements Runnable
{
	StringBuffer buffer;
	int counter;

	public SynchBlock()
	{
		buffer = new StringBuffer();
		counter= 1;
	}

	public void run()
	{
		
		synchronized (buffer)
		{
			System.out.print ("Starting synchronized block ");
			int tempVariable = counter++;

			String message = "Count value is : ";
			
			buffer.append (message);
			
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException ie) {}
			
			message = tempVariable + System.getProperty("line.separator");

			buffer.append (message);
			
			System.out.println ("... ending synchronized block");
		}
	}

	public static void main(String args[]) throws Exception
	{
		SynchBlock block = new SynchBlock();

		Thread t1 = new Thread (block);
		Thread t2 = new Thread (block);
		Thread t3 = new Thread (block);
		Thread t4 = new Thread (block);
		t1.start(); t2.start(); t3.start(); t4.start();

		t1.join(); t2.join(); t3.join(); t4.join();

		System.out.println (block.buffer);
	}
}