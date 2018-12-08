
public class DiningPhil {

		public static void main(String[] args){
			int numOfPhil = 5, eatingTime = 5;
			
			Philosopher p[] = new Philosopher[numOfPhil];
			Monitor mon = new Monitor(numOfPhil);
			
			System.out.println("Dinner is starting! \n");
			
			// Start philosopher threads.
			for(int i = 0; i < numOfPhil; i++){
				p[i] = new Philosopher(i, eatingTime, mon);
			}
			// Suspend the current thread until the philosophers have not done.
			for(int i = 0; i < numOfPhil; i++){
				try {
					p[i].thread.join();
				} catch (InterruptedException e) { }
			}
//			System.out.println("");
//			System.out.println("Dinner is over!");
		}
}

