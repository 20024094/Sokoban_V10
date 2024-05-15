package sokoban_v10;

/**
 * Module: Object Oriented Programming 
 * Student: Daniel Barbu: 20024094
 * Application: Sokoban 
 * Version: 10.0 
 * Date: 14/05/2024 
 * File: Map.java
 *
 * @author Daniel Barbu
 * @version 10.0
 */

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Map {

    private Game game;  // Reference to the Game class
    protected final int OFFSET = 16;
    private final int SPACE = 17;
    protected int currentLevelIndex = 0;
    private int width = 0;
    private int height = 0;

    // Constructor to initialize the Map with a reference to the Game
    public Map(Game game) {
        this.game = game;
    }

    // Method to load a specific level based on the level index
    protected void loadLevel(int levelIndex) {
        currentLevelIndex = levelIndex; // Set the current level index
        System.out.println("Current level index set to: " + currentLevelIndex);
        String level = "";
        // Load level data based on the level index
        switch (levelIndex) {
            case 0:
                level = readLevelFromFile("level1.txt");
                break;
            case 1:
                level = readLevelFromFile("level2.txt");
                break;
            case 2:
                level = readLevelFromFile("level3.txt");
                break;
            case 3:
                level = readLevelFromFile("level4.txt");
                break;
            case 4:
                level = readLevelFromFile("level5.txt");
                break;
            default:
                System.out.println("Invalid level index: " + levelIndex);
                return;
        }

        if (level.isEmpty()) {
            System.out.println("Level data is empty or could not be read for level index: " + levelIndex);
            return;
        }

        System.out.println("Level loaded: \n" + level);
        game.initMap(level); // Load the level
    }

    // Method to load the next level
    protected void loadNextLevel() {
        if (currentLevelIndex + 1 < 5) { // there are 5 levels indexed from 0 to 4
            System.out.println("Loading level: " + (currentLevelIndex + 1));
            // loadLevel(currentLevelIndex + 1);
            loadLevel(currentLevelIndex + 1); // Load the first level using Map
        } else {
            // Handle the completion of all levels (show message or restart the game)
            System.out.println("All levels completed. Congratulations!");
        }
    }

    // Private method to read level data from a file
    private String readLevelFromFile(String filename) {
        StringBuilder levelStringBuilder = new StringBuilder();
        // Load the level file as a resource
        InputStream in = getClass().getResourceAsStream("/maps/" + filename);

        if (in == null) {
            System.out.println("Level file not found: " + filename);
            return "";
        }

        // Read the file line by line and build the level string
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                levelStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return levelStringBuilder.toString();
    }

    // Getter for the board width
    public int getBoardWidth() {
        return this.width;
    }

    // Getter for the board height
    public int getBoardHeight() {
        return this.height;
    }

    // Method to get the maximum width of all levels
    public int getMaxLevelWidth() {
        int maxWidth = 0;
        for (int i = 1; i <= 5; i++) { // there are 5 levels
            String level = readLevelFromFile("level" + i + ".txt");
            String[] lines = level.split("\n");
            for (String line : lines) {
                maxWidth = Math.max(maxWidth, line.length());
            }
        }
        return maxWidth * SPACE;
    }

    // Method to get the maximum height of all levels
    public int getMaxLevelHeight() {
        int maxHeight = 0;
        for (int i = 1; i <= 5; i++) { // there are 5 levels
            String level = readLevelFromFile("level" + i + ".txt");
            String[] lines = level.split("\n");
            maxHeight = Math.max(maxHeight, lines.length);
        }
        int extraSpace = 30;
        return maxHeight * SPACE + extraSpace;
    }
}
