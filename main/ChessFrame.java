package main;

import board.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 
import javax.swing.*;

public class ChessFrame extends JFrame {

    Board board;
    GameLoop gameLoop;

    
    
    public ChessFrame(String args[]) {
        super("sackchess");
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
    public Board getBoard() {
        return this.board;
    }

    public void endGame() {
        
    }
}
