package a2;


// Event representation
class Event implements Comparable<Object> {

  public double time;
  private int _type;
  private Frame f = null;
  private Sender s = null;
 
  public Event(int a_type, double a_time, Frame f) {
	this.setFrame(f);
	setSender(getFrame().getOwner());
    _type = a_type; 
    time = a_time; 
  }
  
  
  public Event(int a_type, double a_time, Sender s) {
	this.setSender(s);
	this.setFrame(s.getFrame());
    _type = a_type; 
    time = a_time; 
  }
  
  
  
  public int get_type() { 
    return _type; 
  }

  public double get_time() { 
    return time; 
  }

  // Used by EventList class
  public Event leftlink, rightlink, uplink;

  public int compareTo(Object _cmpEvent ) {
    double _cmp_time = ((Event) _cmpEvent).get_time() ;
    if( this.time < _cmp_time) return -1;
    if( this.time == _cmp_time) return 0;
    return 1;
  }

	public Frame getFrame() {
		return f;
	}
	
	public void setFrame(Frame f) {
		this.f = f;
	}


	public Sender getSender() {
		return s;
	}


	public void setSender(Sender s) {
		this.s = s;
	}
};
