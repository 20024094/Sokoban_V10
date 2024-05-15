package sokoban_v10;

/**
 * Module: Object Oriented Programming 
 * Student: Daniel Barbu: 20024094
 * Application: Sokoban 
 * Version: 10.0 
 * Date: 14/05/2024 
 * File: Wall.java
 *
 * @author Daniel Barbu
 * @version 10.0
 */

import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

// Wall class extends MapElement, representing a wall in the game
public class Wall extends MapElement {

    private Image image;

    // constructor setting up the HashMap
    public Wall(Coord coord, HashMap<String, Image> imageMap) {
        super(coord); // calls the constructor of the superclass MapElement
        initWall(imageMap);
    }

    // retrieves the wall image from the HashMap using the key Wall
    private void initWall(HashMap<String, Image> imageMap) {
        this.image = imageMap.get("Wall");
        setImage(image);
    }

    @Override
    public void interact() {
        System.out.println("Wall is at coordinates: " + getX() + ", " + getY());
    }
}
