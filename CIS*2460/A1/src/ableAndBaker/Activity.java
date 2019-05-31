package ableAndBaker;

public class Activity 
{
	private int arrTime;    //arrival time of the current caller
	private int intArrTime; //current caller’s interarrival time
	private int tAvaiAble;  //earliest time when Able is available
	private int tAvaiBaker; //earliest time when Baker is available
	private int tCompAble;  //service completion time of Able for received call
	private int tCompBaker; //service completion time of Baker for received call
	private char server;    //chosen server 'A' or 'B' for current call
	private int servTime;   //service time of current call
	private int tServBegin; //service start time of current call
	private Activity old;
	
	public Activity(ProbabilityHelper ph, Activity old)
	{
		
		//interArrival time
		//serviceTime
		
		if(old == null)
		{
			setArrivalTime(0);
			setTimeAvailableAble(0);
			setTimeAvailableBaker(0);
			setTimeCompleteAble(0);
			setTimeCompleteBaker(0);
			setServerTime(0);
			setTimeServerBegin(0);
			setInterArrivalTime(0);
			setTimeServerBegin(0);
		}
		if(old != null)
		{
			this.old = old;
			
			setInterArrivalTime(ph.getInterarrivalTime());	
			setArrivalTime(old.getArrivalTime() + getInterArrivalTime());
			
			if(old.server == 'A')
			{	
				setTimeAvailableAble(old.getTimeServerBegin() + old.getServerTime());
				setTimeAvailableBaker(old.getTimeAvailableBaker());
			}
			else
			{
				setTimeAvailableAble(old.getTimeAvailableAble());
				setTimeAvailableBaker(old.getTimeServerBegin() + old.getServerTime());
			}
			
		
		}
		
		
		
		if(getTimeAvailableAble() == 0 & getTimeAvailableBaker() == 0)
		{
			setServer('A');
			setServerTime(ph.getAbleServiceTime());
		}
		else if (getTimeAvailableAble() <= getTimeAvailableBaker())
		{
			setServer('A');
			setServerTime(ph.getAbleServiceTime());
		}
		else if (getArrivalTime() >= getTimeAvailableAble())
		{
			setServer('A');
			setServerTime(ph.getAbleServiceTime());
		}
		else
		{
			setServer('B');
			setServerTime(ph.getBakerServiceTime());
		}
		
		
		if(getServer() == 'A')
		{
			setTimeServerBegin(Math.max(getTimeAvailableAble(), getArrivalTime()));
		}
		else
		{
			setTimeServerBegin(Math.max(getTimeAvailableBaker(), getArrivalTime()));
		}

	}
	
	public void printOutput()
	{
		System.out.println("Inter Arrival Time : "+getInterArrivalTime());
		System.out.println("Arrival Time : "+getArrivalTime());
		System.out.println("Server : "+(server=='A'?"Able":"Baker"));
		System.out.println("Able available : "+getTimeAvailableAble());
		System.out.println("Baker available : "+getTimeAvailableBaker());
		System.out.println("Server Time  : "+getServerTime());
		//System.out.println("Service Time : ["+(old == null? 0 : (old.getServerTime() + 1))+ "-"+(old == null? getServerTime() : (old.getServerTime() + getServerTime() + 1))+"]");
		if(old == null)
		{
			System.out.println("Service Period : [0-"+getServerTime()+"]");
		}
		else
		{
			if(getServer() == 'A')
			{
			
				System.out.println("Service Period : ["+getTimeServerBegin()+"-"+(getTimeServerBegin()+getServerTime())+"]");
			}
			else
			{

				System.out.println("Service Period : ["+getTimeServerBegin()+"-"+(getTimeServerBegin()+getServerTime())+"]");
			}
		}
		System.out.println("Delay of service : "+(getTimeServerBegin() - getArrivalTime() ));
		System.out.println("Time in system : "+( (getTimeServerBegin()+getServerTime()) - getArrivalTime()));
		System.out.println();
	}
	
	public int getArrivalTime() 
	{
		return arrTime;
	}
	public void setArrivalTime(int arrTime) 
	{
		this.arrTime = arrTime;
	}
	
	public int getInterArrivalTime() 
	{
		return intArrTime;
	}
	public void setInterArrivalTime(int intArrTime)
	{
		this.intArrTime = intArrTime;
	}
	
	public int getTimeAvailableAble() 
	{
		return tAvaiAble;
	}
	public void setTimeAvailableAble(int tAvaiAble) 
	{
		this.tAvaiAble = tAvaiAble;
	}
	
	
	public int getTimeAvailableBaker() 
	{
		return tAvaiBaker;
	}
	public void setTimeAvailableBaker(int tAvaiBaker) 
	{
		this.tAvaiBaker = tAvaiBaker;
	}
	
	/*service completion time of Able for received call*/
	public int getTimeCompleteAble() 
	{
		return tCompAble;
	}
	public void setTimeCompleteAble(int tCompAble) 
	{
		this.tCompAble = tCompAble;
	}
	
	/*service completion time of Baker for received call*/
	public int getTimeCompleteBaker()
	{
		return tCompBaker;
	}
	public void setTimeCompleteBaker(int tCompBaker) 
	{
		this.tCompBaker = tCompBaker;
	}
	
	/*Chosen server 'A' or 'B' for current call*/
	public char getServer()
	{
		return server;
	}
	public void setServer(char server)
	{
		this.server = server;
	}
	
	/*Service time of current call*/
	public int getServerTime() 
	{
		return servTime;
	}
	public void setServerTime(int servTime)
	{
		this.servTime = servTime;
	}
	
	/*Service start time of current call*/
	public int getTimeServerBegin() 
	{
		return tServBegin;
	}
	public void setTimeServerBegin(int tServBegin) 
	{
		this.tServBegin = tServBegin;
	}

	
	
}
