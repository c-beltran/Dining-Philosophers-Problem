import java.util.Date;
import java.util.Vector;

/**
 * Utility clas from Factory.java
 * contains the philosofer naptime/eatingtime
 * @author Carlos
 *
 */
public class SleepUtilities {

	/**
	   * function which determines the 
	   * duration of when a philosopher eatings
	   * @param philNum
	   * @param duration
	   */
	  public static void eats(int philNum, int duration)
	  {
		 System.out.println("Philosofer "+philNum+" is eating");
	    int sleeptime = (int) (duration * Math.random());
	    
	    try
	    {
	      Thread.sleep(sleeptime * 1000);
	    }
	    catch (InterruptedException e) { }
	  }
	
}
