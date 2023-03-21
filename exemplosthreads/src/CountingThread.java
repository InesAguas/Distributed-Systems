public class CountingThread implements Runnable
{
	Counter myCounter;
	int     countAmount;

	public CountingThread (Counter counter, int amount)
	{
		myCounter = counter;
		countAmount = amount;
	}

	public void run()
	{
		for (int i = 1; i <= countAmount; i++)
		{
			myCounter.increaseCount();
		}
	}

	public static void main(String args[]) throws Exception
	{
		Counter c = new Counter();

		Runnable runner = new CountingThread( c, 10 );

		System.out.println ("Starting counting threads");

		Thread t1 = new Thread(runner);
		Thread t2 = new Thread(runner);
		Thread t3 = new Thread(runner);
		t1.start(); t2.start(); t3.start();

		t1.join(); t2.join(); t3.join();

		System.out.println ("main:Counter value is " + c.getCount() );
	}
}

