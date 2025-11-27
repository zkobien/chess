package main;

import java.awt.*;
import javax.swing.*;

public class ChessFrame extends JFrame {
    public ChessFrame() {
        super("Chess Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel boardPanel = createBoardPanel();
        SquareWrapper wrapper = new SquareWrapper(boardPanel);
        wrapper.setBackground(Color.DARK_GRAY);

        this.add(wrapper);

        this.setSize(1000, 800);
        this.setVisible(true);
    }
    private JPanel createBoardPanel() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(8, 8));            
        for (int row = 0; row < 8; row++) {
            Color lightColor = new Color(249, 245, 209);
            Color darkColor = new Color(200, 66, 66);
            for (int col = 0; col < 8; col++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setPreferredSize(new Dimension(100, 100));
                if ((row + col) % 2 == 0) 
                    square.setBackground(lightColor);//light color
                else 
                    square.setBackground(darkColor);//dark color        
                boardPanel.add(square);

                if (col == 0) {
                    JLabel t = new JLabel();
                    t.setText(Integer.toString(8-row));
                    t.setFont(new Font("Monospaced", Font.BOLD, 20));
                    if (square.getBackground() == lightColor)
                        t.setForeground(darkColor);
                    else t.setForeground(lightColor);
                    
                    square.add(t,BorderLayout.NORTH);
                }
                if (row == 7) {
                    JLabel t = new JLabel();
                    t.setHorizontalAlignment(JLabel.RIGHT);
                    char c;
                    c = 97;
                    c += col;
                    t.setText(c+ new String(" "));
                    t.setFont(new Font("Monospaced", Font.BOLD, 20));
                    if (square.getBackground() == lightColor) {
                        t.setForeground(darkColor); 
                    } else {
                        t.setForeground(lightColor);
                    }
                    square.add(t,BorderLayout.SOUTH);  
                }



            }
        }
        return boardPanel;
    }
}
