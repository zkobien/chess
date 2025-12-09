package main;

import java.awt.*;
import javax.swing.*;

/**
 * A utility container that forces its child component to remain square. Used to
 * ensure the chessboard maintains a 1:1 aspect ratio when the window is
 * resized.
 */
class SquareWrapper extends JPanel {

    /**
     * Constructs a SquareWrapper for a given component.
     *
     * @param child the component to keep square
     */
    public SquareWrapper(Component child) {
        setLayout(null);
        add(child);
    }

    /**
     * Performs layout logic to center the child component and force a square
     * size. The size is calculated based on the smaller dimension (width or
     * height) of this container.
     */
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
