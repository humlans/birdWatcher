package org.example.handlers;

import org.example.databaseConnections.DatabaseConnection;
import org.example.items.Sighting;

import java.util.ArrayList;

public class SightingHandler {
    private ArrayList<Sighting> sightings;
    DatabaseConnection databaseConnection = new DatabaseConnection();

    public SightingHandler(ArrayList<Sighting> sightings) {
        this.sightings = sightings;
    }

    public SightingHandler() {
    }

    public void setSightingsToDatabaseList(){

        this.sightings = databaseConnection.getAllSightings();
    }

    public ArrayList<Sighting> getSightingsByUserFromDatabase(String username){
        return databaseConnection.getSightingsByUser(username);
    }

    public ArrayList<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(ArrayList<Sighting> sightings) {
        this.sightings = sightings;
    }

    public Sighting getSightingById(int id) {
        return databaseConnection.getSightingById(id);
    }


}
