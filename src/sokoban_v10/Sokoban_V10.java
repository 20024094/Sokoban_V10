package sokoban_v10;

/**
 * Module: Object Oriented Programming 
 * Student: Daniel Barbu: 20024094
 * Application: Sokoban 
 * Version: 10.0 
 * Date: 15/05/2024 
 * File: Sokoban_V10.java
 *
 * @author Daniel Barbu
 * @version 10.0
 */

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Sokoban_V10 {

    public static void main(String[] args) {
        // schedule a job for the event-dispatching thread creating and showing the GUI.
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sokoban");
            // a new instance of Game class
            Game sokobanGame = new Game();

            // Polymorphic state
            Game game = new Game();
            game.printAllElementStatuses(); // print the positions of all game elements

            Dimension size = sokobanGame.calculateFrameSize();

            frame.setPreferredSize(size);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack(); // Adjust the frame size to fit the preferred size and layouts of its subcomponents
            frame.setLocationRelativeTo(null); // Center the frame
            frame.add(sokobanGame);
            frame.setVisible(true);
        });
    }
}
