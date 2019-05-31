
// Event representation
class Event implements Comparable {

  public double time;
  private int _type;
 
  public Event(int a_type, double a_time) { 
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
};
