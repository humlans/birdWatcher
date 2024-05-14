package org.example.commandPattern;

import org.example.ProgramFacade;
import org.example.builderPattern.SightingBuilder;
import org.example.databaseConnections.DatabaseConnection;
import org.example.items.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateSightingCommand implements ICommand{
    private Menu menu;
    private ProgramFacade program;
    private UserInput userInput = new UserInput();
    public CreateSightingCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        menu = program.getMenu();
        System.out.println("This is in execute, user: " + menu.getCurrentUser().getUsername());
        System.out.println("You have chosen to create a new sighting");
        System.out.println("If at any moment you want to return to the menu write \"Quit\"");
        System.out.println("What do you want the title for this sighting to be?");
        String title = userInput.getCanNotBeBlankStringInput();
        if(title.equals("Quit")) {
            return;
        }

        System.out.println("What bird species did you see?");
        BirdSpecies foundBirdSpecies = findExistingBirdSpecies();
        if(foundBirdSpecies == null) {
            return;
        }

        System.out.println("Add a comment if you want.");
        String comment = userInput.getCanBeBlankStringInput();
        if(comment.equals("Quit")) {
            return;
        }

        String dateTime = LocalDateTime.now().toString();
        User user = menu.getCurrentUser();

        int nextId = databaseConnection.getLastId("http://localhost:8080/sighting/get-last-id") + 1;

        SightingBuilder builder = new SightingBuilder();


        Sighting newSighting = builder.setId(nextId)
                .setTitle(title)
                .setDateTime(dateTime)
                .setCurrentUser(user)
                .setBirdSpecies(foundBirdSpecies)
                .setComment(comment)
                .build();;
        menu.addSighting(newSighting);

        databaseConnection.addNewSightingJson(newSighting, "POST", "http://localhost:8080/sighting/add-new-sighting");

    }

    public BirdSpecies findExistingBirdSpecies() {
        String birdSpeciesName = "";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        // THIS DATABASE CONNECTION WORKS IF I SEND IN BLACKBIRD BUT NOT BLUE AND YELLOW MACAW
        while (!birdSpeciesName.equals("Quit")) {
            birdSpeciesName = userInput.getCanNotBeBlankStringInput();
            BirdSpecies foundBirdSpecies = databaseConnection.getBirdSpeciesByEnglishName(birdSpeciesName);
            if(foundBirdSpecies != null) {
                return foundBirdSpecies;
            }
        }
        return null;
    }
}
