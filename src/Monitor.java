import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class monitors the threads between philosophers
 * Implements DiningServer interface
 * Contains important functions such as when
 * a philosopher picks up and returns chop sticks
 * @author Carlos
 *
 */
public class Monitor implements DiningServer {
	
	private int numOfPhil;
	enum State { THINKING, HUNGRY, EATING };
	//lock is used to achieve synchronization
	final Lock lock;
	State[] states;
	Condition[] self;

	//constructor
	public Monitor(int philNum){
		this.numOfPhil = philNum;
		 states = new State[philNum];
		 self = new Condition[philNum];
		 
		 //ReentrantLock class implements the Lock interface and 
		 //provides synchronization to methods while accessing shared resources
		 lock = new ReentrantLock();
		 
		 //initializing state and cond for philosophers
		for (int i = 0; i < states.length; i++){
			states[i] = State.THINKING;
			self[i] = lock.newCondition();
		}
	 }
	
	/*
	 * pickup chopsticks
	 * @see DiningServer#takeChopsticks(int)
	 */
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
			e.printStackTrace();
		} 
		finally {
			lock.unlock();
		}
	}

	/**
	 * philosopher returning chopsticks
	 */
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
			lock.unlock();
		}
	}
	
	/**
	 * checks if the other philosophers are eating
	 * If not the 'philNumber' eats
	 * @param philNumber
	 */
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
