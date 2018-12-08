
public class Philosopher implements Runnable{
	
	public int myId;
	private int timesToEat;		// Times to eat.
	private Monitor mon;
	public static Thread thread;	
	private int sleepLength;		// How long to sleep during eating.
	// Constructor.
	Philosopher(int id, int numToEat, Monitor m){
		this.myId = id;
		this.timesToEat = numToEat;
		this.mon = m;
		sleepLength = 10;			// Make a pause of 10 ms while eating.
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int count = 0;
		while(count <= timesToEat){
			mon.takeChopsticks(myId);
			Utility.nap(myId, sleepLength);
			mon.returnChopsticks(myId);
			++count;
		}
	}

	
}
