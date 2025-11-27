package board;

import java.awt.*;
import javax.swing.*;
 import pieces.*; 

public class Tile extends JPanel {

    int x;
    int y;
    int row;
    int col;
    String name;
    Piece piece;
    
    public Tile(LayoutManager l) {
        super(l);
        // piece = null;
    }

    //setters
    public void setPos(int col, int row) {
        //x and y represent the x and y  on the board
        this.x = 7 - row;
        this.y = col;
        //row and col represent the position relative to the top left of the board
        this.row = row;
        this.col = col;
    }

    public void setTileName(String in) {
        this.name = in;
    }

    //getters
    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public String getTileName() {
        return this.name;
    }

    public int getPosX() {
        return this.x;
    }

    public int getPosY() {
        return this.y;
    }
}
