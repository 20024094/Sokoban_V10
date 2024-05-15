package sokoban_v10;

/**
 * Module: Object Oriented Programming 
 * Student: Daniel Barbu: 20024094
 * Application: Sokoban 
 * Version: 10.0 
 * Date: 14/05/2024 
 * File: Coord.java
 *
 * @author Daniel Barbu
 * @version 10.0
 */

// Coord class represents a coordinate with x and y values
public class Coord {

    // Private attributes to store the x and y coordinates
    private int x;
    private int y;

    // Constructor to initialize the coordinates with given x and y values
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
     // Overloaded constructor with one parameter
    public Coord(int coordValue) {
        this.x = coordValue;
        this.y = coordValue;
    }

    // Getter for the x-coordinate
    public int getX() {
        return x;
    }

    // Setter for the x-coordinate
    public void setX(int x) {
        this.x = x;
    }

    // Getter for the y-coordinate
    public int getY() {
        return y;
    }

    // Setter for the y-coordinate
    public void setY(int y) {
        this.y = y;
    }
}
