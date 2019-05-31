package a2;

public class Sender 
{
	private int senderNumber;
	private int pendingFrame = 0;
	private int frameSize    = 0;
	private int arrivalTime  = 0;
	private int listening    = 0;
	private int backOffTime  = 0;
	private double timeSent = -1;
	private Frame frame;
	
	
	public Sender(int senderNumber)
	{
		setSenderNumber(senderNumber);
		setPendingFrame(0);
		setFrameSize(0);
		setArrivalTime(0);
		setListening(0);
		setBackOffTime(0);
		frame = null;
	}

	public boolean canRecieveFrame()
	{
		if(pendingFrame == 1)
		{
			return false;
		}
		return true;
	}

	public int getPendingFrame() 
	{
		return pendingFrame;
	}


	public void setPendingFrame(int pendingFrame) 
	{
		this.pendingFrame = pendingFrame;
	}


	public int getFrameSize() 
	{
		return frameSize;
	}


	public void setFrameSize(int frameSize) 
	{
		this.frameSize = frameSize;
	}


	public int getArrivalTime() 
	{
		return arrivalTime;
	}


	public void setArrivalTime(int arrivalTime) 
	{
		this.arrivalTime = arrivalTime;
	}


	public int getListening() 
	{
		return listening;
	}


	public void setListening(int listening) 
	{
		this.listening = listening;
	}


	public int getBackOffTime() 
	{
		return backOffTime;
	}


	public void setBackOffTime(int backOffTime) 
	{
		this.backOffTime = backOffTime;
	}

	public int getSenderNumber() {
		return senderNumber;
	}

	public void setSenderNumber(int senderNumber) {
		this.senderNumber = senderNumber;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		frame.setOwner(this);
		this.frame = frame;
	}

	public double getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(double timeSent) {
		this.timeSent = timeSent;
	}
	
	
}
