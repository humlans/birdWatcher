package org.example.commandPattern;

import org.example.ProgramFacade;
import org.example.databaseConnections.DatabaseConnection;
import org.example.databaseConnections.SightingConnection;
import org.example.items.BirdSpecies;
import org.example.items.Menu;
import org.example.items.Sighting;
import org.example.items.UserInput;

import java.util.ArrayList;
import java.util.Objects;

public class DeleteCommand implements ICommand{
    private ProgramFacade program;
    private ArrayList<BirdSpecies> birdSpecies;
    private ArrayList<Sighting> sightings;
    UserInput userInput;
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private SightingConnection sightingConnection = new SightingConnection();

    public DeleteCommand(ProgramFacade program) {
        this.program = program;

    }
    @Override
    public void execute() {
        this.birdSpecies = databaseConnection.getAllBirdSpecies();
        String username = program.getMenu().getCurrentUser().getUsername();
        this.sightings = sightingConnection.getSightingsByUser(username);
        userInput = new UserInput();

        //Lets the user choose whether to erase a sighting or a bird.
        System.out.println("Do you want to delete a sighting or a bird species?");
        System.out.println("Write \"s\" for sighting and \"b\" for bird species");
        String input = userInput.getCanBeBlankStringInput();
        String chosenMapping = "";
        int maxId;
        //Lets the user choose an appropriate id or go back to the menu.

        //!!!!!!!!!!!!!!!!!!!
        //Is there a better way to do this, can I extract the method somehow??
        if(input.equals("b")) {
            chosenMapping = "bird-species";
            maxId = birdSpecies.size();
            for (int i = 0; i < birdSpecies.size(); i++) {
                System.out.println(i + ". " + birdSpecies.get(i).getEnglishName());
            }
            System.out.println("Write the id of the birdspecies you want to delete.");
            int chosenId = userInput.getIntInputInInterval(maxId);
            deleteBirdSpecies(chosenId, chosenMapping);
        }
        else if(input.equals("s")) {
            chosenMapping = "sighting";
            maxId = sightings.size();
            for (int i = 0; i < sightings.size(); i++) {
                System.out.println(i + ". " + sightings.get(i).getTitle());
            }
            System.out.println("Write the id of the sighting you want to delete.");
            int chosenId = userInput.getIntInputInInterval(maxId);
            deleteSighting(chosenId, chosenMapping);
        }
    }

    //Erases the chosen bird species
    private void deleteBirdSpecies(int chosenId, String mapping) {
        BirdSpecies chosenBirdSpecies = birdSpecies.get(chosenId);
        chosenBirdSpecies.printDetailedBirdSpecies();
        System.out.println("Are you sure you want to delete this bird species? Write \"y\" for yes");
        String newInput = userInput.getCanBeBlankStringInput();
        if (newInput.equals("y")) {
            databaseConnection.deleteBirdSpeciesOrSighting(chosenBirdSpecies.getId(), mapping);
        }
    }

    //Erases the chosen sighting
    private void deleteSighting(int chosenId, String mapping) {
        Sighting sighting = sightings.get(chosenId);
        sighting.printDetailedSighting();
        System.out.println("Are you sure you want to delete this bird species? Write \"y\" for yes");
        String newInput = userInput.getCanBeBlankStringInput();
        if (newInput.equals("y")) {
            databaseConnection.deleteBirdSpeciesOrSighting(sighting.getId(), mapping);
        }
    }
}
