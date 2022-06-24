import java.util.ArrayList;
import java.awt.Color;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * Restaurant is the class used to "build" a new restaurant. The length and width tell the program how long and wide the grid should be
 */
public class Restaurant {
  private int capacity;
  private int length;
  private int width;

  private Color[][] colorSeat;

  private String name;

  //constructors
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

  /**
   * createTables sets every table in the restaurant to white using a 2D array
   * the program later goes through and asseses whether or not a space is white to see if a table can be placed there
   */
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

  /**
   * the groupTable method is used to plot the reservations on the standard draw grid
   * it goes through and assesses whether a square is white (available) or another color (unavailable)
   * and then plots the tables accordingly
   * @param res is needed for the program to know which reservation it's plotting
   */
    /*public void groupTable(Reservation res){
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
    }*/
  public void groupTable(Reservation res){
    if(res.getNumGuests()%2 != 0){
      res.addGuests(1);
    }

    int side = res.getNumGuests()/2;
    int total = res.getNumGuests();
    Color current = randomColor();
    int r = 0;
    int c = 0;

    while(r < width){
      while(c < length){
        if(colorSeat[r][c] == StdDraw.WHITE){
          StdDraw.setPenColor(current);
          colorSeat[r][c] = current;
          StdDraw.filledSquare(r + 0.5, c + 0.5, 0.4);
          total--;
        }else if (total != side || total != 0){
          c++;
        }
        if(total == side || total == 0){
          //r++;
          //c++;
          break;
        }
      }
      if(total == 0){
        r = 0;
        c = 0;
        break;
      }
    }
  }


  public static void main(String[] args){
    Restaurant First = new Restaurant(10, 10, "First");
    Scanner scan = new Scanner(System.in);
    Map<String, Reservation> ReservationsByNames = new HashMap<>();
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
          ReservationsByNames.put(name, res);
          //First.createTables();
          First.groupTable(res);
        }
      }else if(numChoice == 2){
        scan.nextLine();
        System.out.println("Who's name are you looking for?");
        String lookName = scan.nextLine();
        System.out.print(lookName + "'s reservation is "+ReservationsByNames.get(lookName));
      }else if(numChoice == 3){
        scan.nextLine();
        System.out.println("What is your name?");
        String resName = scan.nextLine();
        Reservation resChange = ReservationsByNames.get(resName);
        System.out.print("Your reservation is "+ReservationsByNames.get(resName));
        System.out.print("Would you like to change [N]ame, [D]ate, [T]ime, Number of [G]uests?");
          switch(scan.nextLine().toUpperCase()){
            case "N":
              String initName = resChange.getName();
              System.out.print("Your name is "+initName+". What would you like to change it to?");
              resChange.setName(scan.nextLine());
            case "D":
              String initDate = resChange.getDate();
              System.out.print("Your name is "+initDate+". What would you like to change it to?");
              resChange.setDate(scan.nextLine());
            case "T":
              String initTime = resChange.getTime();
              System.out.print("Your name is "+initTime+". What would you like to change it to?");
              resChange.setTime(scan.nextLine());
            case "G1":
              String initNum = resChange.getTime();
              System.out.print("Your name is "+initNum+". How many guests would you like to add? If you would like to remove guests, write a negative number.");
              resChange.addGuests(scan.nextInt());
          }

        
      }
    }
    
    System.out.print(First);
    StdDraw.show();
    
    
  }
  
}
