package org.example.commandPattern;

import org.example.ProgramFacade;
import org.example.databaseConnections.DatabaseConnection;
import org.example.items.*;

import java.util.ArrayList;

public class EditSightingCommand implements ICommand{
    private ProgramFacade program;
    private UserInput userInput = new UserInput();

    public EditSightingCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        User currentUser = program.getMenu().getCurrentUser();
        ArrayList<Sighting> sightingByCurrentUser = databaseConnection.getSightingsByUser(currentUser.getUsername());
        UserInput userInput = new UserInput();

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
            databaseConnection.addNewSightingJson(chosenSighting, "PUT", "http://localhost:8080/sighting/edit");
        }
    }

    //Is there any way for me to share this one between the two commands or will I just have to accept that
    // I have duplicate code here and in CreateSightingCommand
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
