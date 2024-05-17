package org.example.commandPattern.sightingCommands;

import org.example.ProgramFacade;
import org.example.builderPattern.SightingBuilder;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.DatabaseConnection;
import org.example.databaseConnections.NotificationSenderConnection;
import org.example.databaseConnections.SightingConnection;
import org.example.items.*;
import org.example.observerPattern.NotificationSender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateSightingCommand implements ICommand {
    private Menu menu;
    private ProgramFacade program;
    private UserInput userInput = new UserInput();
    public CreateSightingCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        SightingConnection sightingConnection = new SightingConnection();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        menu = program.getMenu();

        //Takes all the necessary information to create a sighting.
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

        //Automatically sets the date to now,
        // the user to the currently logged-in user
        // and the id to one number above the highest existing id.
        String dateTime = LocalDate.now().toString();
        User user = menu.getCurrentUser();
        int nextId = databaseConnection.getLastId("http://localhost:8080/sighting/get-last-id") + 1;

        //Builds the new sighting.
        SightingBuilder builder = new SightingBuilder();
        Sighting newSighting = builder.setId(nextId)
                .setTitle(title)
                .setDateTime(dateTime)
                .setCurrentUser(user)
                .setBirdSpecies(foundBirdSpecies)
                .setComment(comment)
                .build();

        //Saves the new sighting to the database.
        sightingConnection.addOrEditSightingJson(newSighting,
                                        "POST",
                                        "http://localhost:8080/sighting/add-new-sighting");

        //Creates the new notification to alert subscribers to the
        // newly created sighting with their favourite bird species.
        NotificationSenderConnection senderConnection = new NotificationSenderConnection();
        NotificationSender notificationSender = senderConnection.getSenderById(foundBirdSpecies.getId());
        if(!notificationSender.getListeners().isEmpty()) {
            notificationSender.notifyUsers(newSighting);
        }


    }

    private BirdSpecies findExistingBirdSpecies() {
        String birdSpeciesName = "";
        DatabaseConnection databaseConnection = new DatabaseConnection();

        //Lets the user choose an existing bird species for the new sighting.
        while (!birdSpeciesName.equals("Quit")) {
            birdSpeciesName = userInput.getCanNotBeBlankStringInput();
            BirdSpecies foundBirdSpecies =
                    databaseConnection.getBirdSpeciesByEnglishName(birdSpeciesName);
            if(foundBirdSpecies != null) {
                return foundBirdSpecies;
            }
            System.out.println("If you don't know what bird or if the bird species don't exist, go back to the menu");
            System.out.println("Try a new bird species.");
        }
        return null;
    }
}
