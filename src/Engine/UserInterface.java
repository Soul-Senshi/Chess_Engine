package Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class UserInterface extends JPanel implements MouseListener, MouseMotionListener {

    static int x,y = 0;
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(Color.gray);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        graphics.setColor(new Color(240,240,240));
        graphics.fillRect(x-20,y-20,40,40);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
