package pieces;

import board.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Piece {
    int x;
    int y;
    String type;
    String piecePng;
    Color color;
    Image image;

    Piece(int x, int y, String type, Color color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.type = type;
        String colorPrefix = (color.equals(Color.WHITE)) ? "white" : "black";
        String path = "/pieces/source/" + colorPrefix + "_" + type + ".png";
        //System.out.println(path);
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException | NullPointerException e) {
            System.out.println("Error loading image: " + path);
            e.printStackTrace();
        }
    }


    public abstract java.util.List<Tile> validSteps(Board input);
        
    

    //setters
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //getters
    public String getType() {
        return this.type;
    }

    public int getPosX() {
       return x;
    }

    public int getPosY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isOnBoard(int x, int y) {
        return x >= 0 && y >= 0 && x <= 7 && y <= 7;
    }
}
