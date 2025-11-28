package pieces;

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
    

    //setters
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //getters
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
    
}
