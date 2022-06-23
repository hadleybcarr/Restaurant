import java.util.ArrayList;
import java.awt.Color;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Restaurant {
  private int capacity;
  private int length;
  private int width;
  
  private Color[][] colorSeat;
  
  private String name;
  
  public Restaurant(){
    length = 20;
    width = 20;
    capacity = (length * width);
    name = null;
    colorSeat = new Color[width][length];
    createTables();
  }
  
  public Restaurant(int length, int width, String name){
    this.length = length;
    this.width = length;
    this.capacity = (length * width);
    this.name = name;
    colorSeat = new Color[width][length];
    createTables();
  }
  
  /**
   * gets number of seats available at restaraunt
   * ask Mr. Jacoby about class design and inheritance because we don't need to inherit the functions --> should they be public?
   */
  public int getCapacity(){
    return capacity;
  }
  
  public void addCapacity(int num){
    capacity += num;
  }
  
  public int getLength(){
    return length;
  }
  
  public int getWidth(){
    return width;
  }
  
  public String toString(){
    String res = "Patron's name: +" + name + "\n Number of seats: " + capacity;
    return res;
  }
  
  public static Color randomColor(){
    int red = (int)(Math.random() * 256);
    int green = (int)(Math.random() * 256);
    int blue = (int)(Math.random() * 256);
    Color random = new Color(red, green, blue);
    return random;
  }  
  
  public void createTables(){
    StdDraw.setScale(0, length);
    
    for(int r = 0; r < width; r++){
      for(int c = 0; c < length; c++){
        StdDraw.setPenColor(StdDraw.WHITE);
        colorSeat[r][c] = StdDraw.WHITE;
        StdDraw.filledSquare(r + 0.5, c + 0.5, 0.4);
        StdDraw.setPenColor(StdDraw.WHITE);
      }
    }
    
  }
  
  
  public void groupTable(Reservation res){
    if(res.getNumGuests()%2 != 0){
      res.addGuests(1);
    }
    
    int side = res.getNumGuests()/2; 
    int total = res.getNumGuests();
    Color current = randomColor();
    
    for(int r = 0; r < width; r++){
      for(int c = 0; c < length; c++){
        if(colorSeat[r][c] == StdDraw.WHITE){
          StdDraw.setPenColor(current);
          colorSeat[r][c] = current;
          StdDraw.filledSquare(r + 0.5, c + 0.5, 0.4);
          total--;
        }
        if(total == side || total == 0){
          break;
        }
      }
      if(total == 0){
        break;
      }
    }
  }
  
  
  public static void main(String[] args){
    Restaurant First = new Restaurant(10, 10, "First");
    Scanner scan = new Scanner(System.in);
    Map<String, Reservation> ReservationsbyNames = new HashMap<>();
    int numChoice = 4;
    while(numChoice != -1){
      //newReservation();
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
          //First.createTables();
          First.groupTable(res);
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
    
    System.out.print(First);
    StdDraw.show();
    
    
  }
  
}
