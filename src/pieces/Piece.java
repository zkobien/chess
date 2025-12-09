package pieces;

import board.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Abstract base class representing a generic chess piece.
 * Handles common properties like position, color, image loading, and movement validation structure.
 */
public abstract class Piece {
    int x;
    int y;
    String type;
    String piecePng;
    Color color;
    Image image;
    boolean hasStepped;

    /**
     * Constructor for a generic Piece.
     * Loads the corresponding image resource based on the type and color.
     * @param x initial column position
     * @param y initial row position
     * @param type the type of piece 
     * @param color the color of the piece (Color.WHITE / Color.BLACK)
     */
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
        hasStepped = false;
    }
    
    //hasStepped getter and setter

    /**
     * Checks if the piece has moved at least once.
     * Important for Castling logic (King/Rook).
     * @return true if the piece has moved, false otherwise
     */
    public boolean hasStepped() {
        return this.hasStepped;
    }

    /**
     * Updates the moved status of the piece.
     * @param in true if the piece has just moved
     */
    public void setStepped(boolean in) {
        this.hasStepped = in;
    }


    /**
     * Abstract method to calculate all valid moves for each piece.
     * @param input the current state of the game board
     * @return a List of Tiles the piece can move to
     */
    public abstract java.util.List<Tile> validSteps(Board input);
        
    

    //setters
    
    /**
     * Updates the internal position coordinates of the piece.
     * @param x the new column index
     * @param y the new row index
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //getters
    
    /**
     * Gets the type of the piece as a String.
     * @return the type (e.g., "King", "Queen")
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the current column index (x-coordinate).
     * @return the x coordinate
     */
    public int getPosX() {
       return x;
    }

    /**
     * Gets the current row index (y-coordinate).
     * @return the y coordinate
     */
    public int getPosY() {
        return y;
    }

    /**
     * Gets the image associated with this piece.
     * @return the Image object
     */
    public Image getImage() {
        return image;
    }

    /**
     * Gets the color of the piece.
     * @return Color.WHITE or Color.BLACK
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Helper method to check if a coordinate is within the board boundaries (0-7).
     * @param x the x coordinate to check
     * @param y the y coordinate to check
     * @return true if valid, false if out of bounds
     */
    public boolean isOnBoard(int x, int y) {
        return x >= 0 && y >= 0 && x <= 7 && y <= 7;
    }
}