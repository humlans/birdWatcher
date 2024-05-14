package org.example.commandPattern;

import org.example.builderPattern.BirdSpeciesBuilder;
import org.example.builderPattern.MenuBuilder;
import org.example.databaseConnections.DatabaseConnection;
import org.example.items.BirdSpecies;
import org.example.items.Menu;
import org.example.items.User;
import org.example.items.UserInput;

public class CreateNewSpeciesCommand implements ICommand{

    private Menu menu;

    public CreateNewSpeciesCommand(Menu menu) {
        this.menu = menu;
    }


    @Override
    public void execute() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        /*

        String input = "";
        String previousKey = "";
        while (!input.equals("Quit")){
            for (String inputForSighting: questionsAndAnswers.keySet()) {
                if(!previousKey.isBlank()) {
                    questionsAndAnswers.put(previousKey, input);
                }
                System.out.println("What is the " + inputForSighting + "of the new sightings?");
                input = userInput.getCanNotBeBlankStringInput();
                previousKey = inputForSighting;
            }

        }
         */
        // I should add safety measures so that two birds with the same name cannot be added.
        UserInput userInput = new UserInput();
        System.out.println("What is the scientific name of the new species?");
        String scientificName = userInput.getCanNotBeBlankStringInput();
        if(scientificName.equals("Quit")) {
            return;
        }
        System.out.println("What is the english name of the new species?");
        String englishName = userInput.getCanBeBlankStringInput();
        System.out.println("Which family does it belong to?");
        String family = userInput.getCanNotBeBlankStringInput();
        System.out.println("What is the species habitat?");
        String habitat = userInput.getCanBeBlankStringInput();
        System.out.println("What does the species eat?");
        String diet = userInput.getCanBeBlankStringInput();
        System.out.println("Is it endangered?");
        String gradeOfEndangerment = userInput.getCanBeBlankStringInput();
        System.out.println("What is the species outward characteristics?");
        String appearance = userInput.getCanNotBeBlankStringInput();
        System.out.println("What is the expected lifetime of an individual?");
        int expectedLifetime = userInput.getIntInput();
        int nextId = databaseConnection.getLastId("http://localhost:8080/bird-species/get-last-id") + 1;

        BirdSpeciesBuilder builder = new BirdSpeciesBuilder();
        builder.id(nextId)
                .scientificName(scientificName)
                .englishName(englishName)
                .family(family)
                .habitat(habitat)
                .diet(diet)
                .gradeOfEndangerment(gradeOfEndangerment)
                .appearance(appearance)
                .expectedLifetime(expectedLifetime);
        BirdSpecies newBirdSpecies = builder.build();

        menu.addBirdSpecies(newBirdSpecies);

        // Do a database connection here, put request!
        // Then either save it in an arraylist or do database request at other places to get an updated arraylist for the program


        databaseConnection.addNewBirdSpeciesJson(newBirdSpecies, "POST", "http://localhost:8080/bird-species/add-new");

    }
}
