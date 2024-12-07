package co.ppg2.controllers;

import co.ppg2.model.Player;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerDataController {

    private static final Logger LOGGER = Logger.getLogger(PlayerDataController.class.getName());
    private static String FILE_NAME = "players.dat";

    // TODO: Consider using a configuration file or environment variable for FILE_NAME
    // TODO: Make FILE_NAME final and private

    public static void savePlayers(ArrayList<Player> players) {
        // TODO: Add null check for players parameter
        // TODO: Consider using try-with-resources for better resource management
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(players);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving players", e);
            // TODO: Consider throwing a custom exception instead of just logging
        }
    }

    public static ArrayList<Player> loadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (file.exists()) {
            // TODO: Consider using try-with-resources for better resource management
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = ois.readObject();
                if (obj instanceof ArrayList<?>) {
                    players = (ArrayList<Player>) obj;
                }
            } catch (IOException | ClassNotFoundException e) {
                LOGGER.log(Level.SEVERE, "Error loading players", e);
            }
        }
        return players;
    }

    public static void setFilePath(String filePath) {

        // TODO: Add null check for filePath parameter
        // TODO: Consider validating the file path (e.g., check if it's writable)
        FILE_NAME = filePath;
    }
}