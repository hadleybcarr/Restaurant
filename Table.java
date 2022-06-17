import java.util.ArrayList;
import java.awt.Color;

public class Table{
  private int numSeats;
  private String type;
  
  public Table(){
    numSeats =2;
    type = "freeStand";
  }
  
  public Table(int numSeats, String type){
    this.numSeats = numSeats;
    this.type = type;
    
  }
  
  public int getNumSeats(){
    return numSeats;
  }
  
  public String getType(){
    return type;
  }
  
  
  public String toString(){
    return type+"w/ "+numSeats+"people";
  }

  
}