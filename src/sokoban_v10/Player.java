package sokoban_v10;

/**
 * Module: Object Oriented Programming 
 * Student: Daniel Barbu: 20024094
 * Application: Sokoban 
 * Version: 10.0 
 * Date: 14/05/2024 
 * File: Player.java
 *
 * @author Daniel Barbu
 * @version 10.0
 */

import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

// class Player representing Player in Sokoban game
public class Player extends MapElement {

    private Image image;

    // Modify constructor to accept HashMap
    public Player(Coord coord, HashMap<String, Image> imageMap) {
        super(coord);
        initPlayer(imageMap);
    }

    private void initPlayer(HashMap<String, Image> imageMap) {
        image = imageMap.get("Player"); // Retrieve the Wall image from the HashMap
        setImage(image); // setImage sets the image for this element
    }

    // Method to move the player by a specified amount
    public void move(int x, int y) {
        int dx = getX() + x;
        int dy = getY() + y;

        setX(dx);
        setY(dy);
    }

    @Override
    public void interact() {
        System.out.println("Player is at coordinates: " + getX() + ", " + getY());
    }
}
