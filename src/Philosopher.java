/**
 * implements Runnable
 * philosophers get defined and threads are created
 * also contains the run function
 * @author Carlos
 *
 */
public class Philosopher implements Runnable{
	
	//initializing variables
	public int phil_Id;
	public static Thread thread;	
	private Monitor monitor;
	private int sleepLength;	
	
	// Constructor.
	Philosopher(int id, Monitor mon){
		this.phil_Id = id;
		this.monitor = mon;
		sleepLength = 5;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * run function acquired from
	 * Runnable interface
	 */
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		while(count <= 5){
			monitor.takeChopsticks(phil_Id);
			SleepUtilities.eats(phil_Id, sleepLength);
			monitor.returnChopsticks(phil_Id);
			++count;
		}
	}	
}
