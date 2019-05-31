package ableAndBaker;

public class AbleBaker 
{

	
	public static void main(String [] args)
	{
		ProbabilityHelper ph = new ProbabilityHelper(1234);
		int numTimes = 10;
	
		
	
		ph.displayInterarrivalTime();
		System.out.println("");
		ph.displayAbleServiceTime();
		System.out.println("");
		ph.displayBakerServiceTime();
		System.out.println("");
		
		
		Activity oldActivity = new Activity(ph,null);
		oldActivity.printOutput();
		System.out.println("");
		
		for(int i = 1; i < 10; i ++)
		{
			Activity newAcc = new Activity(ph,oldActivity);
			System.out.println("");
			newAcc.printOutput();
			System.out.println("");
			
			
			oldActivity = newAcc;
			
		}
	
	}
	
}
