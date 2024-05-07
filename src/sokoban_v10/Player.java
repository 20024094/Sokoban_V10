package sokoban_v10;

/**
 *
 * @author danie
 */

import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

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