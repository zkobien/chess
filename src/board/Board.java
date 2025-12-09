package board;

import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import pieces.*;

/**
 * Stores an 8x8 chess grid, and creates it as a jpanel window
 * the tiles are stored in a 2d Tile object array. Also
 * stores the active color, used for importing matches
 */
public class Board extends JPanel {

    
    public Tile[][] tiles = new Tile[8][8];
    Color activeColor;

    /**
     * 0 parameter constructor
     * initializes the supers constructor, 
     * and then itself with a member function
     * @return returns with an initialized chessboard
     */
    public Board() {
        super(new GridLayout(8, 8));
        this.setLayout(new GridLayout(8, 8));   
        initializeBoard();
        activeColor = Color.WHITE;
    }
    public Board(String path) {
        super(new GridLayout(8, 8)); // Ensure layout is set
        this.setLayout(new GridLayout(8, 8));

        initializeEmptyGrid();

        importGame(path);

        activeColor = Color.WHITE; 
    }
    /**
     * 
     * @param filename
     */
    public void exportGame(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    Tile t = tiles[row][col];
                    Piece p = t.getPiece();
                    if (p == null) {
                        writer.write("x");
                    } else {
                        String colorPrefix = (p.getColor() == Color.WHITE) ? "w" : "b";
                        String typeCode = getPieceCode(p);
                        writer.write(colorPrefix + typeCode);
                    }
                    if (col < 7) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }
            System.out.println("Game saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importGame(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            for (int row = 0; row < 8; row++) {
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] tokens = line.trim().split("\\s+");
                    for (int col = 0; col < 8; col++) {
                        if (col < tokens.length) {
                            String token = tokens[col];
                            Tile t = tiles[row][col];
                            // Clear existing piece
                            t.setPiece(null);

                            if (!token.equals("x")) {
                                Piece p = createPieceFromCode(token, col, 7 - row);
                                t.setPiece(p);
                            }
                        }
                    }
                }
            }
            this.repaint();
            System.out.println("Game loaded from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            e.printStackTrace();
        }
    }

    private void initializeEmptyGrid() {
        Color lightColor = new Color(249, 245, 209);
        Color darkColor = new Color(200, 66, 66);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile square = new Tile(new BorderLayout());
                square.setPreferredSize(new Dimension(100, 100));

                if ((row + col) % 2 == 0) {
                    square.setBackground(lightColor);
                    square.setOriginalColor(lightColor);
                } else {
                    square.setBackground(darkColor);
                    square.setOriginalColor(darkColor);
                }

                square.setTileName(getNumber(row) + getLetter(col));
                square.setPos(col, row);

                if (col == 0) {
                    JLabel l = new JLabel(getNumber(row));
                    l.setFont(new Font("Monospaced", Font.BOLD, 20));
                    l.setForeground(square.getBackground() == lightColor ? darkColor : lightColor);
                    square.add(l, BorderLayout.NORTH);
                }
                if (row == 7) {
                    JLabel t = new JLabel(getLetter(col) + " ");
                    t.setHorizontalAlignment(JLabel.RIGHT);
                    t.setFont(new Font("Monospaced", Font.BOLD, 20));
                    t.setForeground(square.getBackground() == lightColor ? darkColor : lightColor);
                    square.add(t, BorderLayout.SOUTH);
                }

                tiles[row][col] = square;
                this.add(square);
            }
        }
    }

    private String getPieceCode(Piece p) {
        if (p instanceof Pawn) {
            return "p";
        }
        if (p instanceof Rook) {
            return "r";
        }
        if (p instanceof Knight) {
            return "kn";
        }
        if (p instanceof Bishop) {
            return "b";
        }
        if (p instanceof Queen) {
            return "q";
        }
        if (p instanceof King) {
            return "ki";
        }
        return "?";
    }

    private Piece createPieceFromCode(String code, int x, int y) {
        char colorChar = code.charAt(0);
        Color color = (colorChar == 'w') ? Color.WHITE : Color.BLACK;
        String type = code.substring(1);

        switch (type) {
            case "p":
                return new Pawn(x, y, "Pawn", color);
            case "r":
                return new Rook(x, y, "Rook", color);
            case "kn":
                return new Knight(x, y, "Knight", color);
            case "b":
                return new Bishop(x, y, "Bishop", color);
            case "q":
                return new Queen(x, y, "Queen", color);
            case "ki":
                return new King(x, y, "King", color);
            default:
                return null;
        }
    }
    /**
     * initializer function
     * creates the Tiles individually, sets Pieces on the correct Tiles
     * @return initializes the 2d Tile array of the board
    */
    private void initializeBoard() {
        Color lightColor = new Color(249, 245, 209);
        Color darkColor = new Color(200, 66, 66);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Create the tile with BorderLayout
                Tile square = new Tile(new BorderLayout());
                square.setPreferredSize(new Dimension(100, 100));

                // Color initialization
                if ((row + col) % 2 == 0) {
                    square.setBackground(lightColor);
                    square.setOriginalColor(lightColor);
                } else {
                    square.setBackground(darkColor);
                    square.setOriginalColor(darkColor);
                }

                // Setting tile name and position
                square.setTileName(getNumber(row) + getLetter(col));
                square.setPos(col, row);

                JLabel l = new JLabel(getNumber(row));
                l.setFont(new Font("Monospaced", Font.BOLD, 20));

                JLabel t = new JLabel(getLetter(col) + " ");
                t.setHorizontalAlignment(JLabel.RIGHT);
                t.setFont(new Font("Monospaced", Font.BOLD, 20));

                //adding numbers and letters
                if (col == 0) {
                    square.add(l, BorderLayout.NORTH);
                    l.setForeground(square.getBackground() == lightColor ? darkColor : lightColor);
                    
                }
                if (row == 7) {
                    square.add(t, BorderLayout.SOUTH);
                    t.setForeground(square.getBackground() == lightColor ? darkColor : lightColor);
                }

                //adding pieces
                if (row == 1)
                    square.setPiece(new Pawn(square.getPosX(), square.getPosY(), "Pawn", Color.BLACK));
                if (row == 6)
                    square.setPiece(new Pawn(square.getPosX(), square.getPosY(), "Pawn", Color.WHITE));
                if (row == 0) {
                    if (col == 0 || col == 7)
                        square.setPiece(new Rook(square.getPosX(), square.getPosY(), "Rook", Color.BLACK));
                    if (col == 1 || col == 6)
                        square.setPiece(new Knight(square.getPosX(), square.getPosY(), "Knight", Color.BLACK));
                    if (col == 2 || col == 5)
                        square.setPiece(new Bishop(square.getPosX(), square.getPosY(), "Bishop", Color.BLACK));
                    if (col == 3)
                        square.setPiece(new Queen(square.getPosX(), square.getPosY(), "Queen", Color.BLACK));
                    if (col == 4)
                        square.setPiece(new King(square.getPosX(), square.getPosY(), "King", Color.BLACK));
                }
                if (row == 7) {
                    if (col == 0 || col == 7)
                        square.setPiece(new Rook(square.getPosX(), square.getPosY(), "Rook", Color.WHITE));
                    if (col == 1 || col == 6)
                        square.setPiece(new Knight(square.getPosX(), square.getPosY(), "Knight", Color.WHITE));
                    if (col == 2 || col == 5)
                        square.setPiece(new Bishop(square.getPosX(), square.getPosY(), "Bishop", Color.WHITE));
                    if (col == 3)
                        square.setPiece(new Queen(square.getPosX(), square.getPosY(), "Queen", Color.WHITE));
                    if (col == 4)
                        square.setPiece(new King(square.getPosX(), square.getPosY(), "King", Color.WHITE));
                }
                tiles[row][col] = square;
                this.add(square);
            }
        }
    }
    /**
     * highlights the input tiles, setting them active
     * @param relevantTiles a list of tiles to be highlighted
     */
    public void activateTiles(java.util.List<Tile> relevantTiles) {
        for (Tile tile : relevantTiles) {
            if (!tile.highlighted)
                tile.setHiglighted(true);
        }
    }

    /**
     * removes highlighting from every tile on the board
     */
    public void unHighlightAll() {
        for (int i = 0; i < 8; i++) {
            for (int y = 0; y < 8; y++) {
                tiles[i][y].setHiglighted(false);
            }
        }
    }
    
    //getters
    public Tile[][] getTileArray() {
        return this.tiles;
    }

    public Color getActiveColor() {
        return activeColor;
    }

    //helper methods
    private String getLetter(int col) {
        return String.valueOf((char) ('a' + col));
    }

    private String getNumber(int row) {
        return Integer.toString(8 - row);
    }

    
}
