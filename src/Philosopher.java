
public class Philosopher implements Runnable{
	
	//initializing variables
	public int phil_Id;
//	private int eatingTime;
	public static Thread thread;	
	private Monitor mon;
	private int sleepLength;	
	
	// Constructor.
	Philosopher(int id, Monitor m){
		this.phil_Id = id;
//		this.eatingTime = numToEat;
		this.mon = m;
		sleepLength = 10;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		while(count <= 5){
			mon.takeChopsticks(phil_Id);
			SleepUtilities.nap(phil_Id, sleepLength);
			mon.returnChopsticks(phil_Id);
			++count;
		}
	}	
}
