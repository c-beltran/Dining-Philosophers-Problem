import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor implements DiningServer {
	
	private int numOfPhil;
	enum State { THINKING, HUNGRY, EATING };
	final Lock lock;
	State[] states;
	Condition[] self;

	public Monitor(int philNum){
		this.numOfPhil = philNum;
		 states = new State[philNum];
		 self = new Condition[philNum];
		 lock = new ReentrantLock();
		 
		 //initializing state and cond for philosophers
		for (int i = 0; i < states.length; i++){
			states[i] = State.THINKING;
			self[i] = lock.newCondition();
//			System.out.println("starting.. " + states[i]);
		}
	 }
	
	//pickup chopsticks
	public void takeChopsticks(int philNumber) {
		lock.lock();
		try {
			//signal that philosopher is hungry
			states[philNumber] = State.HUNGRY;
			
			//this checks if my left and right neighbors are not eating
			checkForAvailability(philNumber);
			
			if (states[philNumber] != State.EATING){
				
					//if im unable to eat, i wait for signal
					System.out.println("Philosopher "+philNumber+" is thinking...");
					self[philNumber].await();
					//eat after waiting for some time
					checkForAvailability(philNumber);
			}
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			System.out.println("getting stuck here take-chop "+philNumber);
			e.printStackTrace();
		} 
		finally {
			lock.unlock();
			
		}
	}

	//put down chopsticks
	public void returnChopsticks(int philNumber) {
		lock.lock();
		try{
			System.out.println("Philosopher "+philNumber+" released its left and right chopsticks.");
			
			states[philNumber] = State.THINKING;
			 // test left and right philosophers
			
			 checkForAvailability((philNumber + 4) % 5);
	
			 checkForAvailability((philNumber + 1) % 5);
		}
		finally{
//			System.out.println("getting stuck here return-chop "+philNumber);
			lock.unlock();
		}
		
	}
	
	private void checkForAvailability(int philNumber){
		if ((states[(philNumber + 4) % 5] != State.EATING) && 
				(states[philNumber] == State.HUNGRY) && 
				(states[(philNumber + 1) % 5] != State.EATING)){
			
			// indicate that philosopher is eating
			System.out.println("Philosopher "+philNumber+" acquired its left and right chopsticks.");
			states[philNumber] = State.EATING;
			
            // signal() has no effect during takeChopsticks(), 
            // but is important to wake up waiting 
            // hungry philosophers during returnChopsticks() 
			self[philNumber].signal();
		}
	 }

}
