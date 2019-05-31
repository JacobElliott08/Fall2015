package a2;

// Run: java Sim seed
// Ex: java Sim 123

import java.util.*;

class Sim 
{

	// Class Sim variables
	public static double Clock;
	public static double MeanInterArrivalTime;
	public static double MeanServiceTime;
	public static double SIGMA;
	public static double LastEventTime;
	
	public static double SaveNormal;
	public static int  NumNormals = 0;
	public static final double  PI = 3.1415927 ;
	
	public static double TotalBusy;
	public static double MaxQueueLength;
	public static double SumResponseTime;
	public static long QueueLength, NumberInService,
	TotalFrames, NumberOfDepartures, LongService;

	public static int NumberOfFrames;
	public static int NumberOfFramesScheduled = 0;
	
	public final static int arrival = 1;
	public final static int departure = 2;
	public final static int listen = 3;
	public final static int first72 = 4;

	public static EventList FutureEventList;
	public static Queue frameList;
	public static Random stream;
	
	public static int numOfSenders = 2;
	private static ArrayList<Sender> senders = new ArrayList<Sender>(numOfSenders);

	public static void main(String argv[]) {
		MeanInterArrivalTime = 4.5; 
		MeanServiceTime      = 3.2;
		SIGMA                = 0.6; 
		TotalFrames          = 2;
		NumberOfFrames       = 1;
		long seed            = 123456;
		

		stream = new Random();           // initialize rng stream
		FutureEventList = new EventList();
		frameList = new Queue();

		Initialization();

		// Loop until first "TotalFrames" have departed
		doSim();
		
	}
	
	public static void doSim()
	{
		while(NumberOfFrames <= TotalFrames) 
		{
			Event evt = (Event)FutureEventList.getMin();  // get imminent event
			FutureEventList.dequeue();                    // be rid of it
			Clock = evt.get_time();                       // advance simulation time
			
			System.out.println("");
			if (evt.get_type() == arrival ) 
			{
				ProcessFrameArrival(evt);
			}
			else if (evt.get_type() == listen)
			{
				ProcessListen(evt);
			}
			else if (evt.get_type() == first72)
			{
				ProcessFirst72(evt);
			}
		}
	}
	
	/*
	Process Listening by Sender of a Pending Frame
	
	Pre:Listening by sender S is the imminent event. Idea
	determine cable view for sender S of frame F; if cable appears quiet to S,
	schedule completion of delivery of 72th byte of F; else // cable appears busy
	count senders that started sending before current time;
	get the latest time r for S to receive a transmitted 72th byte; if count = 1 and the frame sent is F’,
	schedule S to listen 1 usec after sending of F’ is complete; else schedule S to listen at time r;
	*/
	
	private static void ProcessFirst72(Event evt) 
	{
		System.out.println("In process First 72 : "+evt.get_time());
		NumberOfFrames ++;	
	}

	public static void ProcessListen(Event evt) 
	{
		int view = determineCableView(evt.getSender(), evt);
		System.out.println("In process listen for "+evt.get_time());
		
		if(view == 0)
		{
			/*schedule completion of delivery of 72th byte of F;*/
			//TODO change this later
			evt.getSender().setTimeSent(evt.get_time());
			Event  first72Event = new Event(first72,(evt.get_time() + 10),evt.getFrame());
			FutureEventList.enqueue(first72Event);
			
		}
		//TODO 
		else
		{
			
			System.out.println("Cable is busy");
		}
	
	}

	private static int determineCableView(Sender owner, Event evt) 
	{
		
		for(Sender s : senders)
		{
			if(!s.canRecieveFrame())
			{
				if(s.getTimeSent() != -1)
				{
					if(s.getTimeSent() < evt.get_time()) 
					{
						int max = Math.max(s.getSenderNumber(), evt.getSender().getSenderNumber());
						int min = Math.min(s.getSenderNumber(), evt.getSender().getSenderNumber());
						double div = ((max - min)/(double)numOfSenders);
						if((div * 25.6) < evt.get_time())
						{
							return 1;
						}
					}
				}
			}
		}
		return 0;
	}

	// seed the event list with TotalFrames arrivals
	public static void Initialization()   
	{ 
		Clock = 0.0;
		QueueLength = 0;
		NumberInService = 0;
		LastEventTime = 0.0;
		TotalBusy = 0 ;
		MaxQueueLength = 0;
		SumResponseTime = 0;
		NumberOfDepartures = 0;
		LongService = 0;
		
		for(int i = 0; i < numOfSenders; i ++)
		{
			senders.add(new Sender(i+1));
		}
		
		// create first arrival event
		scheduleNewFrame(NumberOfFramesScheduled);

	}

	public static void scheduleNewFrame(int i) 
	{
		NumberOfFramesScheduled ++;
		
		System.out.println("Scheduling new frame with ID : "+i);
		Frame f = new Frame(i);
		double time = exponential(stream, MeanInterArrivalTime);
		Event evt = new Event(arrival, time, f);
		if(i == 0)
		{
			evt.time = 0;
		}
		evt.time += Clock;
		System.out.println("Arrival scheduled at time : "+evt.time);
		FutureEventList.enqueue( evt );
	}

	
	
	
	public static void ProcessFrameArrival(Event evt) 
	{
		frameList.enqueue(evt.getFrame()); 
		QueueLength++;
		Sender toUse = null;
		// if the server is idle, fetch the event, do statistics
		// and put into service
		
		
		/*Give it to a sender if sender is avail*/
		for(Sender s : senders)
		{
			if(s.canRecieveFrame())
			{
				toUse = s;
				break;
			}
		}
		
		if(toUse != null)
		{
			Frame f = (Frame) frameList.dequeue();
			toUse.setFrame(f);
			System.out.println("We can use Sender : "+toUse.getSenderNumber());
			toUse.setPendingFrame(1);
			System.out.println("frame "+f.getId()+" was dequeued.");
			
			/*Schedule Sender to listen in 1 usec*/	
			Event listenEvent = new Event(listen, (evt.time + 1), toUse);
			FutureEventList.enqueue(listenEvent);
		}
		else
		{
			TotalBusy += (Clock - LastEventTime);
		}

		


		// adjust max queue length statistics
		if (MaxQueueLength < QueueLength)
		{
			MaxQueueLength = QueueLength;
		}

		// schedule the next arrival 
		//TODO adjust this condition
		if(NumberOfFramesScheduled < TotalFrames)
		{
			scheduleNewFrame(NumberOfFramesScheduled);	
		}
		
		LastEventTime = Clock;
	}

	
	
	

	
	

	public static double exponential(Random rng, double mean) 
	{
		
		return -mean*Math.log( rng.nextDouble() );
	}

	public static double normal(Random rng, double mean, double sigma)
	{
		double ReturnNormal;
		// should we generate two normals?
		if (NumNormals == 0 ) {
			double r1 = rng.nextDouble();
			double r2 = rng.nextDouble();
			ReturnNormal = Math.sqrt(-2*Math.log(r1))*Math.cos(2*PI*r2);
			SaveNormal   = Math.sqrt(-2*Math.log(r1))*Math.sin(2*PI*r2);
			NumNormals = 1;
		} else {
			NumNormals = 0;
			ReturnNormal = SaveNormal;
		}
		return ReturnNormal*sigma + mean ;
	}
}

