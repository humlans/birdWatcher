package org.example.commandPattern.sightingCommands;

import org.example.ProgramFacade;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.DatabaseConnection;
import org.example.databaseConnections.SightingConnection;
import org.example.items.*;

import java.util.ArrayList;

public class EditSightingCommand implements ICommand {
    private ProgramFacade program;
    private UserInput userInput = new UserInput();

    public EditSightingCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        SightingConnection sightingConnection = new SightingConnection();
        User currentUser = program.getMenu().getCurrentUser();
        ArrayList<Sighting> sightingByCurrentUser = sightingConnection.getSightingsByUser(currentUser.getUsername());
        UserInput userInput = new UserInput();

        //Displays all the existing sightings by the current user and let them choose which one to edit.
        System.out.println("You have chosen to edit a sighting");
        System.out.println("To edit a sighting write one of the following id:s below.\n");
        for(int i = 0; i < sightingByCurrentUser.size(); i++) {
            System.out.println(i + ". " + sightingByCurrentUser.get(i).getTitle());
        }
        System.out.println("Write an id or write \"Quit\" to return to menu");
        int choice = userInput.getIntInputOrQuit();
        if(choice == Integer.MIN_VALUE) {
            return;
        }
        //If a valid choice is found proceeds to ask for the information that they want to edit.
        if(choice < sightingByCurrentUser.size() && choice > -1) {
            Sighting chosenSighting = sightingByCurrentUser.get(choice);
            System.out.println("You have chosen to edit " + chosenSighting.getTitle());
            System.out.println("Would you like to update the title? Write \"yes\" anything else is a no.");
            String input = userInput.getCanBeBlankStringInput();
            if(input.equals("yes")) {
                System.out.println("Write the new title: ");
                String title = userInput.getCanNotBeBlankStringInput();
                chosenSighting.setTitle(title);
            }

            System.out.println("Would you like to update the bird species? Write \"yes\" anything else is a no.");
            input = userInput.getCanBeBlankStringInput();
            if(input.equals("yes")) {
                System.out.println("Write the new bird species for the sighting");
                BirdSpecies birdSpecies = findExistingBirdSpecies();
                chosenSighting.setBirdSpecies(birdSpecies);
            }


            System.out.println("Would you like to update the comment? Write \"yes\" anything else is a no.");
            input = userInput.getCanBeBlankStringInput();
            if(input.equals("yes")) {
                System.out.println("What would you like the new comment to be?");
                String comment = userInput.getCanBeBlankStringInput();
                chosenSighting.setComment(comment);
            }

            //Saves the changes in the database.
            sightingConnection.addOrEditSightingJson(chosenSighting, "PUT", "http://localhost:8080/sighting/edit");
        }
    }

    public BirdSpecies findExistingBirdSpecies() {
        String birdSpeciesName = "";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        while (true) {
            birdSpeciesName = userInput.getCanNotBeBlankStringInput();
            BirdSpecies foundBirdSpecies = databaseConnection.getBirdSpeciesByEnglishName(birdSpeciesName);
            if(foundBirdSpecies != null) {
                return foundBirdSpecies;
            }
            System.out.println("Could not find that bird species in the database.");
            System.out.println("Try writing an existing bird species.");
        }
    }
}
