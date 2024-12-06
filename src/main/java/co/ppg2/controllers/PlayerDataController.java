package co.ppg2.controllers;

import co.ppg2.model.Player;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerDataController {

    private static final Logger LOGGER = Logger.getLogger(PlayerDataController.class.getName());
    private static String FILE_NAME = "players.dat";

    public static void savePlayers(ArrayList<Player> players) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(players);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving players", e);
        }
    }

    public static ArrayList<Player> loadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (file.exists()) {
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
        FILE_NAME = filePath;
    }
}