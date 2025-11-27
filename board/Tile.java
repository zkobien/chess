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

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Draw the square background color

        if (piece != null && piece.getImage() != null) {
            Graphics2D g2d = (Graphics2D) g;

            // 1. Enable smooth rendering (essential when resizing images!)
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            // 2. Define how much of the tile the piece should fill (0.85 = 85%)
            double scale = 0.75;

            // 3. Calculate the size of the piece based on the tile size
            int tileWidth = getWidth();
            int tileHeight = getHeight();

            int pieceWidth = (int) (tileWidth * scale);
            int pieceHeight = (int) (tileHeight * scale);

            // 4. Calculate the position to center the piece
            int xPos = (tileWidth - pieceWidth) / 2;
            int yPos = (tileHeight - pieceHeight) / 2;

            // 5. Draw the image with the new size and position
            g2d.drawImage(piece.getImage(), xPos, yPos, pieceWidth, pieceHeight, null);
        }
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

    public void setPiece(Piece p) {
        this.piece = p;
        this.repaint();
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
