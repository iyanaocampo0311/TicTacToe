package co.ppg2.controllers;

import co.ppg2.model.Player;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages the saving and loading of player data to and from a file.
 * Handles serialization and deserialization of player objects.
 */
public class PlayerDataController {

    /**
     * The name of the file where player data is stored.
     */
    private static final String FILE_NAME = "players.dat";

    /**
     * Saves the list of players to a file using serialization.
     *
     * @param players the list of {@link Player} objects to save
     */
    public static void savePlayers(ArrayList<Player> players) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace(); // TODO: (ag) Consider logging the exception for better debugging, not just printing the stack trace.
        }
    }

    /**
     * Loads the list of players from a file using deserialization.
     * If the file does not exist or an error occurs, an empty list is returned.
     *
     * @return a list of {@link Player} objects loaded from the file
     */
    public static ArrayList<Player> loadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            players = (ArrayList<Player>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // TODO: (ag) Consider logging the exception for better debugging.
        }
        return players;
    }
}
