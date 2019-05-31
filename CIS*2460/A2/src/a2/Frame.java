package a2;

public class Frame 
{
	private int id = 0;
	private int size = 0;
	private double arrival = 0;
	
	private Sender owner = null;
	private int numberOfColisions = 0;
	
	public Frame(int id)
	{
		this.setId(id);
		setSize(0);
		setArrival(0);
		setNumberOfColisions(0);
		setOwner(null);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getArrival() {
		return arrival;
	}

	public void setArrival(double arrival) {
		this.arrival = arrival;
	}

	public Sender getOwner() {
		return owner;
	}

	public void setOwner(Sender owner) {
		this.owner = owner;
	}

	public int getNumberOfColisions() {
		return numberOfColisions;
	}

	public void setNumberOfColisions(int numberOfColisions) {
		this.numberOfColisions = numberOfColisions;
	}
}
