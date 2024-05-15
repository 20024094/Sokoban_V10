package sokoban_v10;

/**
 * Module: Object Oriented Programming 
 * 
 * Student: Daniel Barbu: 20024094
 * Application: Sokoban 
 * Version: 10.0 
 * Date: 15/05/2024 
 * File: Diamond.java
 *
 * @author Daniel Barbu
 * @version 10.0
 */

import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

// Diamond class extends MapElement, representing a diamond in the game
public class Diamond extends MapElement {

    // Private variable to store the image of the diamond
    private Image image;

    // Constructor to initialize the Diamond with coordinates and image map
    public Diamond(Coord coord, HashMap<String, Image> imageMap) {
        super(coord); // Calls the constructor of the superclass MapElement with the coordinate
        initDiamond(imageMap); // Initializes the diamond's image using the image map
    }

    // Retrieves the diamond image from the HashMap using the key Diamond
    private void initDiamond(HashMap<String, Image> imageMap) {
        image = imageMap.get("Diamond"); // Retrieve the Diamond image from the HashMap
        setImage(image); // setImage sets the image for this element
    }

    @Override
    public void interact() {
        System.out.println("Diamond is at coordinates: " + getX() + ", " + getY());
    }
}
