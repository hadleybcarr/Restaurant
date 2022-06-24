import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * this class handles reservations, the hashmap allows you to look up by name in the reservation list.
 * included are methods for modifying the reservations, as well as standard getter/setter methods
 */

public class Reservation {
  private String name;
  private String phoneNum;
  private String date;
  private String time;
  private Integer numGuests;
  private Map<Reservation, String> Reservations = new HashMap<>();
  
  public Reservation(){
    name = null;
    phoneNum = null;
    date = null;
    time = null;
    numGuests = null;
    
  }
  
  public Reservation(String name, String phoneNum, String date, String time, Integer numGuests){
    this.name = name;
    this.phoneNum = phoneNum;
    this.date = date;
    this.time = time;
    this.numGuests = numGuests;
    
  }

  public String getName(){
    return name;
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  public String getPhoneNum(){
    return phoneNum;
  }
  
  public void setPhoneNum(String phoneNum){
    this.phoneNum = phoneNum;
  }
  
  public String getDate(){
    return date;
  }

  public void setDate(String date){
    this.date = date;
  }
  
  public String getTime(){
    return time;
  }

  public void setTime(String time){
    this.time = time;
  }
  
  public int getNumGuests(){
    return numGuests;
  }

  /**
   *

   * @param n indicates number of guests you want to add or subtract
   * this method and the removeGuests method are to aid in modifying reservations
   */
  public void addGuests(int n){
    numGuests += n;
  }
  
  public void removeGuests(int n){
    numGuests -= n;
  }
  
  
  public String toString(){
    return name+", you made a reservation for "+numGuests+" people at "+time+" on "+date+". You can be contacted @"+phoneNum;
  }
  
  /** uses hashmap but scanner for name doesn't work*/
  public static void main(String [] args){
    // public void newReservation(){
    Scanner scan = new Scanner(System.in);
    Map<String, Reservation> ReservationsbyNames = new HashMap<>();
    int numChoice = 4;
    while(numChoice != -1){
      System.out.println("Hello! Are you looking to create a reservation? Please click 1. If you're looking for a reservation, click 2. If you want to change your reservation click 3. Click -1 to stop");
      numChoice = scan.nextInt();
      if(numChoice == 1){
        System.out.println("What is your name?");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.println("What date would you like to make your reservation?");
        String date = scan.nextLine();
        System.out.println("What time?");
        String time = scan.nextLine();
        System.out.println("What is your phone number?");
        String phoneNum = scan.nextLine();
        System.out.println("How many guests?");
        int guests = scan.nextInt();
        Reservation res = new Reservation(name, phoneNum, time, date, guests);
        System.out.print("Just to confirm. This is your reservation:" +res+"y/n?");
        scan.nextLine();
        String confirm = scan.nextLine();
        if(confirm.equals("y")){
          ReservationsbyNames.put(name, res);
        }
      }else if(numChoice == 2){
        scan.nextLine();
        System.out.println("Who's name are you looking for?");
        String lookName = scan.nextLine();
        System.out.print(lookName + "'s reservation is "+ReservationsbyNames.get(lookName));
      }else if(numChoice == 3){
        scan.nextLine();
        System.out.println("What is your name?");
        String resName = scan.nextLine();
        System.out.print("Your reservation is "+ReservationsbyNames.get(resName));


      }
    }
  }
}
