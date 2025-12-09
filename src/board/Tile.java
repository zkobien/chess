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
    boolean highlighted;
    Color originalColor;
    
    public Tile(LayoutManager l) {
        super(l);

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Draw the square background color

        if (piece != null && piece.getImage() != null) {
            Graphics2D g2d = (Graphics2D) g;

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            double scale = 0.75;

            int tileWidth = getWidth();
            int tileHeight = getHeight();

            int pieceWidth = (int) (tileWidth * scale);
            int pieceHeight = (int) (tileHeight * scale);

            int xPos = (tileWidth - pieceWidth) / 2;
            int yPos = (tileHeight - pieceHeight) / 2;

            g2d.drawImage(piece.getImage(), xPos, yPos, pieceWidth, pieceHeight, null);
        }

        if (highlighted) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setColor(new Color(255, 153, 0, 128));

            int dotSize = getWidth() / 3;
            int x = (getWidth() - dotSize) / 2;
            int y = (getHeight() - dotSize) / 2;
            g2d.fillOval(x, y, dotSize, dotSize);

            this.setBackground(new Color(255, 204, 0));

        }

        if (!highlighted) {
            this.setBackground(originalColor);
        }
        
    }

    //setters
    public void setPos(int col, int row) {
        //x and y represent the x and y  on the board
        this.x = col;
        this.y = 7-row;
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

    public void setHiglighted(boolean in) {
        highlighted = in;
        this.repaint();
    }

    public void setOriginalColor(Color in) {
        this.originalColor = in;
    }

    //getters

    public Color getoriginalColor() {
        return this.originalColor;
    }
    public boolean getHighlighted() {
        return highlighted;
    }

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

    public Piece getPiece() {
        return this.piece;
    }
    

}
