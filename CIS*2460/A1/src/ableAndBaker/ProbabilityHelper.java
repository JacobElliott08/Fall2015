package ableAndBaker;

import java.text.DecimalFormat;
import java.util.Random;

public class ProbabilityHelper 
{
	private long seed = 0L;
	private Random r;
	private double[] interArrivalTime =  {0.25,0.40, 0.20, 0.15};
	private double[] ableServiceTime = {0.30,0.28,0.25,0.17};
	private double[] bakerServiceTime = {0.35,0.25,0.20,0.20};
	
	public ProbabilityHelper(long seed)
	{
		this.seed = seed;
		r = new Random(seed);
	}

	
	
	/*InterarrivalTime(Minutes) 
	Probability
	1     0.25
	2     0.40
	3     0.20
	4     0.15
	*/
	public int getInterarrivalTime()
	{
		double rand = r.nextDouble();
		if(rand < 0.25)
		{
			return 1;
		}
		if(rand < 0.65)
		{
			return 2;
		}
		if(rand < 0.85)
		{
			return 3;
		}
		else
		{
			return 4;
		}
	}
	
	public void displayInterarrivalTime()
	{
		double total = 0.00;
		System.out.println("Interarrival Time distribution:");
		System.out.println("Time (minutes) [1,2,3,4].");
		System.out.print("Probability [");
		for(int i = 0; i < interArrivalTime.length; i ++)
		{
			if((interArrivalTime.length - i) != 1)
			{
				System.out.print(interArrivalTime[i]+",");
			}
			else
			{
				System.out.print(interArrivalTime[i]);
			}
			
		}
		System.out.println("]");
		
		
		System.out.print("Cumulative pb [");
		for(int i = 0; i < interArrivalTime.length; i ++)
		{
			total += interArrivalTime[i];
			
			if((interArrivalTime.length - i) != 1)
			{
				System.out.print(total+",");
			}
			else
			{
				System.out.print(total);
			}
			
		}
		System.out.println("]");
		
	}
	
	/*
	Able’s ServiceTime(Minutes) 
	Probability
	2     0.30
	3     0.28
	4     0.25
	5     0.17
	*/
	public int getAbleServiceTime()
	{
		double rand = r.nextDouble();
		if(rand < 0.30)
		{
			return 1;
		}
		if(rand < 0.58)
		{
			return 2;
		}
		if(rand < 0.83)
		{
			return 3;
		}
		else
		{
			return 4;
		}
	}
	
	
	public void displayAbleServiceTime()
	{
		double total = 0.00;
		System.out.println("Able service distribution:");
		System.out.println("Time (minutes) [2,3,4,5].");
		System.out.print("Probability [");
		for(int i = 0; i < ableServiceTime.length; i ++)
		{
			if((ableServiceTime.length - i) != 1)
			{
				System.out.print(ableServiceTime[i]+",");
			}
			else
			{
				System.out.print(ableServiceTime[i]);
			}
			
		}
		System.out.println("]");
		
		
		System.out.print("Cumulative pb [");
		for(int i = 0; i < ableServiceTime.length; i ++)
		{
			total += ableServiceTime[i];
			
			if((ableServiceTime.length - i) != 1)
			{
				System.out.print(total+",");
			}
			else
			{
				System.out.print(total);
			}
			
		}
		System.out.println("]");
		
	}
	
	/*
	Baker’sServiceTime(Minutes)
	Probability
	3     0.35
	4     0.25
	5     0.20
	6     0.20
	*/
	
	public int getBakerServiceTime()
	{
		double rand = r.nextDouble();
		if(rand < 0.35)
		{
			return 1;
		}
		if(rand < 0.60)
		{
			return 2;
		}
		if(rand < 0.80)
		{
			return 3;
		}
		else
		{
			return 4;
		}
	}
	
	
	public void displayBakerServiceTime()
	{
		double total = 0.00;
		System.out.println("Baker service distribution:");
		System.out.println("Time (minutes) [3,4,5,6].");
		System.out.print("Probability [");
		for(int i = 0; i < bakerServiceTime.length; i ++)
		{
			if((bakerServiceTime.length - i) != 1)
			{
				System.out.print(bakerServiceTime[i]+",");
			}
			else
			{
				System.out.print(bakerServiceTime[i]);
			}
			
		}
		System.out.println("]");
		
		
		System.out.print("Cumulative pb [");
		for(int i = 0; i < bakerServiceTime.length; i ++)
		{
			total += bakerServiceTime[i];
			
			if((bakerServiceTime.length - i) != 1)
			{
				System.out.print(total+",");
			}
			else
			{
				System.out.print(total);
			}
			
		}
		System.out.println("]");
		
	}
	
}

