package sokoban_v10;

/**
 * Module: Object Oriented Programming 
 * Student: Daniel Barbu: 20024094
 * Application: Sokoban 
 * Version: 10.0 
 * Date: 15/05/2024 
 * File: Game.java
 *
 * @author Daniel Barbu
 * @version 10.0
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.HashMap;

public class Game extends JPanel {

    // Constants for layout and collision type constants
    protected final int OFFSET = 16;
    private final int SPACE = 17;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    private Image backgroundImage;

    // Instance of the Map class to manage game levels
    private Map game;
    protected HashMap<String, Image> imageMap;

    // List of all map elements in the game
    private ArrayList<MapElement> coordElements;

    // Lists for specific types of game elements
    private ArrayList<Wall> wall = new ArrayList<>();
    private ArrayList<Crate> crate = new ArrayList<>();
    private ArrayList<Diamond> diamond = new ArrayList<>();

    // The player object
    private Player player;
    // Dimensions of the game board
    private int width = 0;
    private int height = 0;

    private int currentLevelIndex = 0;
    // Flag to indicate if the current level is completed
    private boolean complete = false;

    // Constructor to initialize the game
    public Game() {
        loadImages(); // Load images for the game elements
        game = new Map(this); // Create a new Map object
        game.loadLevel(currentLevelIndex);
        initBoard();

        coordElements = new ArrayList<>();
        // Initialize elements in polymorphic way
        coordElements.add(new Player(new Coord(1, 1), imageMap));
        coordElements.add(new Wall(new Coord(2, 2), imageMap));
        coordElements.add(new Crate(new Coord(3, 3), imageMap));
        coordElements.add(new Diamond(new Coord(4, 4), imageMap));
    }

    // Method to calculate the size of the game frame
    public Dimension calculateFrameSize() {
        int frameWidth = game.getMaxLevelWidth() + OFFSET * 2;
        int frameHeight = game.getMaxLevelHeight() + OFFSET * 2;
        return new Dimension(frameWidth, frameHeight);
    }

    // Method to print the statuses of all elements
    public void printAllElementStatuses() {
        for (MapElement element : coordElements) {
            element.interact();  // Polymorphic method call
        }
    }

    // Method to load images from files
    private void loadImages() {
        imageMap = new HashMap<>();
        try {
            File pathToFile = new File("src/graphics/Floor.png");
            imageMap.put("Floor", ImageIO.read(pathToFile));

            pathToFile = new File("src/graphics/Wall.png");
            imageMap.put("Wall", ImageIO.read(pathToFile));

            pathToFile = new File("src/graphics/Diamond.png");
            imageMap.put("Diamond", ImageIO.read(pathToFile));

            pathToFile = new File("src/graphics/Crate.png");
            imageMap.put("Crate", ImageIO.read(pathToFile));

            pathToFile = new File("src/graphics/WarehouseKeeper.png");
            imageMap.put("Player", ImageIO.read(pathToFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Method to initialize the game board
    private void initBoard() {
        addKeyListener(new TAdapter()); // Add key listener for player control
        setFocusable(true);
        game.loadLevel(currentLevelIndex); // Load the current level
    }

    // Method to initialize the map with level data
    public void initMap(String level) {
        System.out.println("Initializing map with level data...");
        // Clear previous game elements
        wall.clear();
        crate.clear();
        diamond.clear();
        player = null;

        int x = OFFSET;
        int y = OFFSET;

        for (int i = 0; i < level.length(); i++) {
            char item = level.charAt(i);
            Coord coord = new Coord(x, y); // Create Coord object here

            switch (item) {
                case '\n':
                    y += SPACE;
                    x = OFFSET;
                    if (this.width < x) {
                        this.width = x;
                    }
                    break;
                case 'X':
                    wall.add(new Wall(coord, imageMap));
                    x += SPACE;
                    break;
                case '*':
                    crate.add(new Crate(coord, imageMap));
                    x += SPACE;
                    break;
                case '.':
                    diamond.add(new Diamond(coord, imageMap));
                    x += SPACE;
                    break;
                case '@':
                    player = new Player(coord, imageMap);
                    x += SPACE;
                    break;
                case ' ':
                    x += SPACE;
                    break;
                default:
                    // Handle unexpected characters or add logging
                    break;
            }

            height = y; // Update the height to the current y position
        }
    }

    // Method to build the map and render it
    private void buildMap(Graphics g) {
        super.paintComponent(g);

        Image floor = imageMap.get("Floor");
        if (floor == null) {
            System.out.println("Floor image not found in the map.");
        } else {
            for (int x = 0; x < getWidth(); x += OFFSET) {
                for (int y = 0; y < getHeight(); y += OFFSET) {
                    g.drawImage(floor, x, y, this);
                }
            }
        }

        ArrayList<MapElement> mapArray = new ArrayList<>();

        mapArray.addAll(wall);
        mapArray.addAll(diamond);
        mapArray.addAll(crate);
        mapArray.add(player);

        for (int i = 0; i < mapArray.size(); i++) {

            MapElement item = mapArray.get(i);

            if (item instanceof Player || item instanceof Crate) {

                g.drawImage(item.getImage(), item.getX() + 2, item.getY() + 2, this);
            } else {

                g.drawImage(item.getImage(), item.getX(), item.getY(), this);
            }

            if (complete) {

                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        buildMap(g);
    }

    // Inner class for handling keyboard input
    protected class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (complete) {
                return; // If the level is complete, ignore further input
            }

            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    if (wallCollision(player, LEFT_COLLISION)) {
                        return;
                    }

                    if (crateCollision(LEFT_COLLISION)) {
                        return;
                    }
                    player.move(-SPACE, 0);

                    break;

                case KeyEvent.VK_RIGHT:
                    if (wallCollision(player, RIGHT_COLLISION)) {
                        return;
                    }

                    if (crateCollision(RIGHT_COLLISION)) {
                        return;
                    }
                    player.move(SPACE, 0);

                    break;

                case KeyEvent.VK_UP:
                    if (wallCollision(player, TOP_COLLISION)) {
                        return;
                    }

                    if (crateCollision(TOP_COLLISION)) {
                        return;
                    }
                    player.move(0, -SPACE);

                    break;

                case KeyEvent.VK_DOWN:
                    if (wallCollision(player, BOTTOM_COLLISION)) {
                        return;
                    }

                    if (crateCollision(BOTTOM_COLLISION)) {
                        return;
                    }
                    player.move(0, SPACE);

                    break;

                case KeyEvent.VK_R:
                    restartLevel(); // Restart the current level

                    break;

                default:
                    break;
            }

            repaint();
        }
    }

    // Method to check for collisions with walls
    private boolean wallCollision(MapElement elementCollision, int type) {
        switch (type) {
            case LEFT_COLLISION:
                for (int i = 0; i < wall.size(); i++) {
                    Wall wall = this.wall.get(i);
                    if (elementCollision.isLeftCollision(wall)) {
                        return true;
                    }
                }

                return false;

            case RIGHT_COLLISION:
                for (int i = 0; i < wall.size(); i++) {
                    Wall wall = this.wall.get(i);
                    if (elementCollision.isRightCollision(wall)) {
                        return true;
                    }
                }

                return false;

            case TOP_COLLISION:
                for (int i = 0; i < wall.size(); i++) {
                    Wall wall = this.wall.get(i);
                    if (elementCollision.isTopCollision(wall)) {
                        return true;
                    }
                }

                return false;

            case BOTTOM_COLLISION:
                for (int i = 0; i < wall.size(); i++) {
                    Wall wall = this.wall.get(i);
                    if (elementCollision.isBottomCollision(wall)) {
                        return true;
                    }
                }

                return false;

            default:
                break;
        }

        return false;
    }

    // Method to check for collisions with crates
    private boolean crateCollision(int type) {
        switch (type) {

            case LEFT_COLLISION:

                for (int i = 0; i < crate.size(); i++) {

                    Crate crateLeftCollision = crate.get(i);

                    if (player.isLeftCollision(crateLeftCollision)) {

                        for (int j = 0; j < crate.size(); j++) {

                            Crate item = crate.get(j);

                            if (!crateLeftCollision.equals(item)) {

                                if (crateLeftCollision.isLeftCollision(item)) {
                                    return true;
                                }
                            }

                            if (wallCollision(crateLeftCollision, LEFT_COLLISION)) {
                                return true;
                            }
                        }

                        crateLeftCollision.move(-SPACE, 0);
                        isCompleted();
                    }
                }

                return false;

            case RIGHT_COLLISION:

                for (int i = 0; i < crate.size(); i++) {

                    Crate crateRightCollision = crate.get(i);

                    if (player.isRightCollision(crateRightCollision)) {

                        for (int j = 0; j < crate.size(); j++) {

                            Crate item = crate.get(j);

                            if (!crateRightCollision.equals(item)) {

                                if (crateRightCollision.isRightCollision(item)) {
                                    return true;
                                }
                            }

                            if (wallCollision(crateRightCollision, RIGHT_COLLISION)) {
                                return true;
                            }
                        }

                        crateRightCollision.move(SPACE, 0);
                        isCompleted();
                    }
                }
                return false;

            case TOP_COLLISION:

                for (int i = 0; i < crate.size(); i++) {

                    Crate crateTopCollision = crate.get(i);

                    if (player.isTopCollision(crateTopCollision)) {

                        for (int j = 0; j < crate.size(); j++) {

                            Crate item = crate.get(j);

                            if (!crateTopCollision.equals(item)) {

                                if (crateTopCollision.isTopCollision(item)) {
                                    return true;
                                }
                            }

                            if (wallCollision(crateTopCollision, TOP_COLLISION)) {
                                return true;
                            }
                        }

                        crateTopCollision.move(0, -SPACE);
                        isCompleted();
                    }
                }

                return false;

            case BOTTOM_COLLISION:

                for (int i = 0; i < crate.size(); i++) {

                    Crate crateBottomCollision = crate.get(i);

                    if (player.isBottomCollision(crateBottomCollision)) {

                        for (int j = 0; j < crate.size(); j++) {

                            Crate item = crate.get(j);

                            if (!crateBottomCollision.equals(item)) {

                                if (crateBottomCollision.isBottomCollision(item)) {
                                    return true;
                                }
                            }

                            if (wallCollision(crateBottomCollision, BOTTOM_COLLISION)) {

                                return true;
                            }
                        }

                        crateBottomCollision.move(0, SPACE);
                        isCompleted();
                    }
                }

                break;

            default:
                break;
        }

        return false;
    }

    // Method to check if the level is completed
    public void isCompleted() {
        int numCratesOnDiamonds = 0;

        for (Crate crate : crate) {
            for (Diamond diamond : diamond) {
                if (crate.getX() == diamond.getX() && crate.getY() == diamond.getY()) {
                    numCratesOnDiamonds++;
                }
            }
        }

        if (numCratesOnDiamonds == diamond.size()) {
            complete = true;
            repaint();
            System.out.println("Level completed. Loading next level...");
            // Delay loading the next level to allow the user to see the completed message
            SwingUtilities.invokeLater(() -> {
                complete = false; // Reset the complete flag
                game.loadNextLevel();
                repaint();
            });
        }
    }

    private void restartLevel() {
        game.loadLevel(currentLevelIndex); // Restart the current level
    }
}
