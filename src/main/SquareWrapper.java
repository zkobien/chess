package main;
import java.awt.*;
import javax.swing.*;

class SquareWrapper extends JPanel {

    public SquareWrapper(Component child) {
        setLayout(null);
        add(child);
    }
    @Override
    public void doLayout() {
        if (getComponentCount() > 0) {
            Component child = getComponent(0);

            int w = getWidth();
            int h = getHeight();
            int size = Math.min(w, h);

            int x = (w - size) / 2;
            int y = (h - size) / 2;

            child.setBounds(x, y, size, size);
            child.validate();
        }
    }
}
