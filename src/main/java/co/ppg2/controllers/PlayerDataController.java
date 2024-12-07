package co.ppg2.controllers;

import co.ppg2.model.Player;
import java.io.*;
import java.util.ArrayList;

public class PlayerDataController {

    private static final String FILE_NAME = "players.dat";

    public static void savePlayers(ArrayList<Player> players) {
        // TODO: Check if players is null or empty before attempting to save
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: Handle exception better (maybe log or show a user-friendly message)
        }
    }

    public static ArrayList<Player> loadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            players = (ArrayList<Player>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // TODO: Handle exception better (maybe return empty list or show a message)
        }
        return players;
    }
}
