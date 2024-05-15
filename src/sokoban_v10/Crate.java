package sokoban_v10;

/**
 * Module: Object Oriented Programming 
 * Student: Daniel Barbu: 20024094
 * Application: Sokoban 
 * Version: 10.0 
 * Date: 14/05/2024 
 * File: Crate.java
 *
 * @author Daniel Barbu
 * @version 10.0
 */

import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

// Crate class extends MapElement, representing a crate in the game
public class Crate extends MapElement {

    private Image image;

    // Constructor to initialize the Crate with coordinates and image map
    public Crate(Coord coord, HashMap<String, Image> imageMap) {
        super(coord);
        initCrate(imageMap); // Pass the HashMap to initWall
    }

    // Retrieves the crate image from the HashMap using the key Crate
    private void initCrate(HashMap<String, Image> imageMap) {
        image = imageMap.get("Crate"); // Retrieve the Wall image from the HashMap
        setImage(image); // setImage sets the image for this element
    }

    // Method to move the crate by a specified amount
    public void move(int x, int y) {
        int dx = getX() + x;
        int dy = getY() + y;

        setX(dx);
        setY(dy);
    }

    @Override
    public void interact() {
        System.out.println("Crate is at coordinates: " + getX() + ", " + getY());
    }
}
