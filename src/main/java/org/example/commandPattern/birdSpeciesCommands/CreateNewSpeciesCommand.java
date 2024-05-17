package org.example.commandPattern.birdSpeciesCommands;

import org.example.builderPattern.BirdSpeciesBuilder;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.DatabaseConnection;
import org.example.databaseConnections.NotificationSenderConnection;
import org.example.items.BirdSpecies;
import org.example.items.Menu;
import org.example.items.UserInput;

public class CreateNewSpeciesCommand implements ICommand {

    private Menu menu;

    public CreateNewSpeciesCommand(Menu menu) {
        this.menu = menu;
    }


    @Override
    public void execute() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        // I should add safety measures so that two birds with the same name cannot be added.
        UserInput userInput = new UserInput();

        // Takes in all the information needed to create a new bird species
        System.out.println("You have chosen to create a new bird species.");
        System.out.println("If you at any point want to return write \"Quit\"");
        System.out.println();
        System.out.println("What is the scientific name of the new species?");
        String scientificName = userInput.getCanNotBeBlankStringInput();
        if(scientificName.equals("Quit")) {
            return;
        }
        System.out.println("What is the english name of the new species?");
        String englishName = userInput.getCanBeBlankStringInput();
        if(englishName.equals("Quit")) {
            return;
        }
        System.out.println("Which family does it belong to?");
        String family = userInput.getCanNotBeBlankStringInput();
        if(family.equals("Quit")) {
            return;
        }
        System.out.println("What is the species habitat?");
        String habitat = userInput.getCanBeBlankStringInput();
        if(habitat.equals("Quit")) {
            return;
        }
        System.out.println("What does the species eat?");
        String diet = userInput.getCanBeBlankStringInput();
        if(diet.equals("Quit")) {
            return;
        }
        System.out.println("Is it endangered?");
        String gradeOfEndangerment = userInput.getCanBeBlankStringInput();
        if(gradeOfEndangerment.equals("Quit")) {
            return;
        }
        System.out.println("What is the species outward characteristics?");
        String appearance = userInput.getCanNotBeBlankStringInput();
        if(appearance.equals("Quit")) {
            return;
        }
        System.out.println("What is the expected lifetime of an individual?");
        int expectedLifetime = userInput.getIntInputOrQuit();
        if(expectedLifetime == Integer.MIN_VALUE) {
            return;
        }

        //Gets the last used id from database.
        int nextId = databaseConnection.getLastId("http://localhost:8080/bird-species/get-last-id") + 1;

        //Builds the new bird species
        BirdSpeciesBuilder builder = new BirdSpeciesBuilder();
        builder.id(nextId)
                .setScientificName(scientificName)
                .setEnglishName(englishName)
                .setFamily(family)
                .setHabitat(habitat)
                .setDiet(diet)
                .setGradeOfEndangerment(gradeOfEndangerment)
                .setAppearance(appearance)
                .setExpectedLifetime(expectedLifetime);
        BirdSpecies newBirdSpecies = builder.build();

        //Saves the new species to the database.
        databaseConnection.addNewBirdSpeciesJson(newBirdSpecies, "POST", "http://localhost:8080/bird-species/add-new");

        //Saves the affiliated notification sender to the database.
        NotificationSenderConnection notificationSenderConnection = new NotificationSenderConnection();
        notificationSenderConnection.addOrEditNotificationSender(newBirdSpecies.getNotificationSender(), "POST", "add-new");
    }
}
