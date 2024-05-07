package sokoban_v10;

/**
 *
 * @author danie
 */

import java.awt.Image;

public abstract class MapElement {

    private final int SPACE = 17;
    private Coord coord;
    private Image image;
    
    // An abstract method to be overridden
    public abstract void interact();

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

    public boolean isLeftCollision(MapElement elementLeftCollision) {
        
        return getX() - SPACE == elementLeftCollision.getX() && getY() == elementLeftCollision.getY();
    }

    public boolean isRightCollision(MapElement elementRightCollision) {
        
        return getX() + SPACE == elementRightCollision.getX() && getY() == elementRightCollision.getY();
    }

    public boolean isTopCollision(MapElement elementTopCollision) {
        
        return getY() - SPACE == elementTopCollision.getY() && getX() == elementTopCollision.getX();
    }

    public boolean isBottomCollision(MapElement elementBottomCollision) {
        
        return getY() + SPACE == elementBottomCollision.getY() && getX() == elementBottomCollision.getX();
    }
}