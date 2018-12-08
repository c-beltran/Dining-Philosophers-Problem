import java.util.Date;
import java.util.Vector;

public class Utility {

	private static final int NAP_TIME = 5;

	  // Nap between zero and NAP_TIME seconds.
//	  public static void nap()
//	  {
//	    nap(NAP_TIME);
//	  }

	  // Nap between zero and duration seconds.
	  public static void nap(int philNum, int duration)
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
