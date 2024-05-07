package sokoban_v10;

/**
 *
 * @author danie
 */

import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;

public class Diamond extends MapElement {
    private Image image;

    // constructor setting up the HashMap
    public Diamond(Coord coord, HashMap<String, Image> imageMap) {
        super(coord);
        initDiamond(imageMap);
    }
    
    private void initDiamond(HashMap<String, Image> imageMap) {
        image = imageMap.get("Diamond"); // Retrieve the Diamond image from the HashMap
        setImage(image); // setImage sets the image for this element
    }
    
    @Override
    public void interact() {
        System.out.println("Diamond is at coordinates: " + getX() + ", " + getY());
    }
}
