package main;

import board.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * The main window frame for the Chess app. Initializes the GUI
 * components, sets up the Board, GameLoop, and input listeners.
 */
public class ChessFrame extends JFrame {

    Board board;
    GameLoop gameLoop;

    /**
     * Constructor for the main game window. Sets up the window properties,
     * initializes the board (empty / from import), attaches the game loop for
     * mouse handling, and sets up keyboard shortcuts.
     *
     * * @param args command-line arguments. if args[0] is "import", loads game
     * from args[1].
     */
    public ChessFrame(String args[]) {
        super("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (args.length > 0 && args[0].equals("import")) {

            this.board = new Board(args[1]);
        } else {
            this.board = new Board();
        }

        this.gameLoop = new GameLoop(board);

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.tiles[row][col].addMouseListener(gameLoop);
            }
        }

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    System.out.println("Exporting game...");
                    board.exportGame("saved_game.txt");
                }
            }
        });

        this.setFocusable(true);
        this.requestFocusInWindow();

        SquareWrapper wrapper = new SquareWrapper(board);
        wrapper.setBackground(Color.DARK_GRAY);

        this.add(wrapper);

        this.setSize(1000, 800);
        this.setVisible(true);
    }

    /**
     * Gets the current Board 
     *
     * @return the Board object of this Frame
     */
    public Board getBoard() {
        return this.board;
    }

}
