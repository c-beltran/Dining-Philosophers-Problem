/**
 * Main driver class
 * contains main function
 * and initializes the number 
 * of philosophers
 * @author Carlos
 *
 */
public class Assingment2 {

	public static void main(String[] args)
	{
		int numOfPhil = 5;
		
		Philosopher phil[] = new Philosopher[numOfPhil];
		Monitor monitor = new Monitor(numOfPhil);
		
		System.out.println("Food is ready! \n");
		
		// initialize a thread for each philosopher
		for(int i = 0; i < numOfPhil; i++)
		{
			phil[i] = new Philosopher(i, monitor);
		}
		// Suspend the current thread until philosopher is done
		for(int i = 0; i < numOfPhil; i++)
		{
			try {
			//join() causes the current thread to pause execution until philosopher thread terminates. 
				phil[i].thread.join();
			} catch (InterruptedException e) { }
		}
	}
}