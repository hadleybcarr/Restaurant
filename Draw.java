import java.awt.*;

public class Draw {
    private int length;
    private int width;

    private Color[][] colorSeat;

    public static Color randomColor(){
        int red = (int)(Math.random() * 256);
        int green = (int)(Math.random() * 256);
        int blue = (int)(Math.random() * 256);
        Color random = new Color(red, green, blue);
        return random;
    }

    public void createTables(){
        StdDraw.setScale(0, length);

        for(int r = 0; r < length; r++){
            for(int c = 0; c < width; c++){
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
}
