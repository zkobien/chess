package main;

import board.*;
import java.awt.*;
import javax.swing.*;

public class ChessFrame extends JFrame {

    Board board;

    public ChessFrame() {
        super("sackchess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.board = new Board();

        SquareWrapper wrapper = new SquareWrapper(board);
        wrapper.setBackground(Color.DARK_GRAY);

        this.add(wrapper);

        this.setSize(1000, 800);
        this.setVisible(true);
    }
}
