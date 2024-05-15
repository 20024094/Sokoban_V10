package sokoban_v10;

/**
 * Module: Object Oriented Programming 
 * Student: Daniel Barbu: 20024094
 * Application: Sokoban 
 * Version: 10.0 
 * Date: 15/05/2024 
 * File: MapElement.java
 *
 * @author Daniel Barbu
 * @version 10.0
 */

import java.awt.Image;

public abstract class MapElement {

    // Constant space value used for collision detection
    private final int SPACE = 17;
    private Coord coord;
    private Image image;

    // An abstract method to be overridden
    public abstract void interact();

    // Constructor to initialize the coordinate of the element
    public MapElement(Coord coord) {
        this.coord = coord;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public int getX() {
        return coord.getX();
    }

    public int getY() {
        return coord.getY();
    }

    public void setX(int x) {
        coord.setX(x);
    }

    public void setY(int y) {
        coord.setY(y);
    }

    // Method to check for collision on the left side
    public boolean isLeftCollision(MapElement elementLeftCollision) {

        return getX() - SPACE == elementLeftCollision.getX() && getY() == elementLeftCollision.getY();
    }

    // Method to check for collision on the right side
    public boolean isRightCollision(MapElement elementRightCollision) {

        return getX() + SPACE == elementRightCollision.getX() && getY() == elementRightCollision.getY();
    }

    // Method to check for collision on the top side
    public boolean isTopCollision(MapElement elementTopCollision) {

        return getY() - SPACE == elementTopCollision.getY() && getX() == elementTopCollision.getX();
    }

    // Method to check for collision on the bottom side
    public boolean isBottomCollision(MapElement elementBottomCollision) {

        return getY() + SPACE == elementBottomCollision.getY() && getX() == elementBottomCollision.getX();
    }
}
