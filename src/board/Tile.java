package board;

import java.awt.*;
import javax.swing.*;
import pieces.*;

/**
 * Represents a single square (tile) on the chess board. Each tile is a JPanel
 * that can hold a piece and handle its own drawing logic, including
 * highlighting legal moves.
 */
public class Tile extends JPanel {

    int x;
    int y;
    int row;
    int col;
    String name;
    Piece piece;
    boolean highlighted;
    Color originalColor;

    /**
     * Constructs a new Tile with the specified LayoutManager.
     * @param l the LayoutManager to use for this panel
     */
    public Tile(LayoutManager l) {
        super(l);

    }

    /**
     * Custom painting component for the tile. Draws the background, the chess
     * piece (if present), and any highlight effects.
     * @param g the Graphics object used for drawing
     */
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
    /**
     * Sets the position of the tile on the board. Calculates logical
     * coordinates (x, y) based on standard chess board orientation (0,0 at
     * bottom-left).
     *
     * @param col the column index (0-7)
     * @param row the row index (0-7)
     */
    public void setPos(int col, int row) {
        //x and y represent the x and y  on the board
        this.x = col;
        this.y = 7 - row;
        //row and col represent the position relative to the top left of the board
        this.row = row;
        this.col = col;
    }

    /**
     * Sets the alphanumeric name of the tile.
     * @param in the name string
     */
    public void setTileName(String in) {
        this.name = in;
    }

    /**
     * Places a piece on this tile and triggers a repaint to update the visual.
     * @param p the Piece object to place, or null to clear the tile
     */
    public void setPiece(Piece p) {
        this.piece = p;
        this.repaint();
    }

    /**
     * Sets the highlight state of the tile. Highlighted tiles usually indicate
     * valid move destinations or the currently selected piece.
     * @param in true to highlight the tile, false to remove highlight
     */
    public void setHiglighted(boolean in) {
        highlighted = in;
        this.repaint();
    }

    /**
     * Stores the original background color of the tile (light or dark). Used to
     * restore the color when highlights are removed.
     * @param in the Color object
     */
    public void setOriginalColor(Color in) {
        this.originalColor = in;
    }

    //getters

    /**
     * Gets the original background color of the tile.
     * Used for Piece initilization
     * @return the original Color
     */
    public Color getoriginalColor() {
        return this.originalColor;
    }

    /**
     * Checks if the tile is currently highlighted.
     * @return true if highlighted, false otherwise
     */
    public boolean getHighlighted() {
        return highlighted;
    }

    /**
     * Gets the row index of the tile 
     * @return the row index
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the column index of the tile 
     *
     * @return the column index
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Gets the name of the tile (e4).
     *
     * @return the tile name
     */
    public String getTileName() {
        return this.name;
    }

    /**
     * Gets X coordinate of the Tile
     *
     * @return the x coordinate (0-7)
     */
    public int getPosX() {
        return this.x;
    }

    /**
     * Gets Y coordinate of the Tile
     * @return the y coordinate (0-7)
     */
    public int getPosY() {
        return this.y;
    }

    /**
     * Returns the piece currently occupying this tile.
     *
     * @return the Piece object, or null if empty
     */
    public Piece getPiece() {
        return this.piece;
    }

}
