package Engine;

import javax.swing.*;
import java.awt.*;

public class ChessEngine {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Chess");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UserInterface UI = new UserInterface();
        mainFrame.add(UI);
        mainFrame.setSize(500, 500);
        mainFrame.setVisible(true);
    }
}
