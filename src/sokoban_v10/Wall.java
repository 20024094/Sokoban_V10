package sokoban_v10;

/**
 *
 * @author danie
 */

import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

public class Wall extends MapElement {
    private Image image;

    // constructor setting up the HashMap
    public Wall(Coord coord, HashMap<String, Image> imageMap) {
        super(coord);
        initWall(imageMap);
    }
    
    private void initWall(HashMap<String, Image> imageMap) {
        this.image = imageMap.get("Wall");
        setImage(image);
    }
    
    @Override
    public void interact() {
        System.out.println("Wall is at coordinates: " + getX() + ", " + getY());
    }
}
