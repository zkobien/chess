package main;

import board.*;
import java.awt.*;
import javax.swing.*;

public class ChessFrame extends JFrame {

    Board board;
    GameLoop gameLoop;

    public ChessFrame() {
        super("sackchess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.board = new Board();
        this.gameLoop = new GameLoop(board);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.tiles[row][col].addMouseListener(gameLoop);
            }
        }

        SquareWrapper wrapper = new SquareWrapper(board);
        wrapper.setBackground(Color.DARK_GRAY);

        this.add(wrapper);

        this.setSize(1000, 800);
        this.setVisible(true);
    }

    public Board getBoard() {
        return this.board;
    }
}
