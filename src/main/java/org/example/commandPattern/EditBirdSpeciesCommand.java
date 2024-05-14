package org.example.commandPattern;

import org.example.ProgramFacade;
import org.example.databaseConnections.DatabaseConnection;
import org.example.items.BirdSpecies;
import org.example.items.Menu;
import org.example.items.Sighting;
import org.example.items.UserInput;

import java.util.ArrayList;

public class EditBirdSpeciesCommand implements ICommand{
    private ProgramFacade program;

    public EditBirdSpeciesCommand(ProgramFacade program) {
        this.program = program;
    }
    @Override
    public void execute() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserInput userInput = new UserInput();

        ArrayList<BirdSpecies> birdSpecies = program.getMenu().getBirdSpecies();

        System.out.println("You have chosen to edit a bird species");
        System.out.println("To edit a bird species write one of the following id:s below.\n");
        for(int i = 0; i < birdSpecies.size(); i++) {
            System.out.println(i + ". " + birdSpecies.get(i).getEnglishName());
        }
        System.out.println("Write an id or write \"Quit\" to return to menu");
        int choice = userInput.getIntInputOrQuit();
        if(choice == Integer.MIN_VALUE) {
            return;
        }
        if(choice < birdSpecies.size() && choice > -1) {
            BirdSpecies chosenBirdSpecies = birdSpecies.get(choice);
            System.out.println("You have chosen to edit: ");
            chosenBirdSpecies.printDetailedBirdSpecies();
            System.out.println("Would you like to update the scientific name? Write \"yes\" anything else is a no.");
            String input = userInput.getCanBeBlankStringInput();
            if (input.equals("yes")) {
                System.out.println("Write the new scientific name: ");
                String scientificName = userInput.getCanNotBeBlankStringInput();
                chosenBirdSpecies.setScientificName(scientificName);
            }

            System.out.println("Would you like to update the english name? Write \"yes\" anything else is a no.");
            input = userInput.getCanBeBlankStringInput();
            if (input.equals("yes")) {
                System.out.println("Write the new english name: ");
                String englishName = userInput.getCanNotBeBlankStringInput();
                chosenBirdSpecies.setEnglishName(englishName);
            }

            System.out.println("Would you like to update the family? Write \"yes\" anything else is a no.");
            input = userInput.getCanBeBlankStringInput();
            if (input.equals("yes")) {
                System.out.println("Write the new family: ");
                String family = userInput.getCanNotBeBlankStringInput();
                chosenBirdSpecies.setFamily(family);
            }

            System.out.println("Would you like to update the habitat? Write \"yes\" anything else is a no.");
            input = userInput.getCanBeBlankStringInput();
            if (input.equals("yes")) {
                System.out.println("Write the new habitat: ");
                String habitat = userInput.getCanNotBeBlankStringInput();
                chosenBirdSpecies.setHabitat(habitat);
            }

            System.out.println("Would you like to update the diet? Write \"yes\" anything else is a no.");
            input = userInput.getCanBeBlankStringInput();
            if (input.equals("yes")) {
                System.out.println("Write the new diet: ");
                String diet = userInput.getCanNotBeBlankStringInput();
                chosenBirdSpecies.setDiet(diet);
            }

            System.out.println("Would you like to update the endangered status? Write \"yes\" anything else is a no.");
            input = userInput.getCanBeBlankStringInput();
            if (input.equals("yes")) {
                System.out.println("Write the new endangered status: ");
                String gradeOfEndangerment = userInput.getCanNotBeBlankStringInput();
                chosenBirdSpecies.setGradeOfEndangerment(gradeOfEndangerment);
            }

            System.out.println("Would you like to update the appearance? Write \"yes\" anything else is a no.");
            input = userInput.getCanBeBlankStringInput();
            if (input.equals("yes")) {
                System.out.println("Write the new appearance: ");
                String appearance = userInput.getCanNotBeBlankStringInput();
                chosenBirdSpecies.setAppearance(appearance);
            }

            System.out.println("Would you like to update the expected lifetime? Write \"yes\" anything else is a no.");
            input = userInput.getCanBeBlankStringInput();
            if (input.equals("yes")) {
                System.out.println("Write the new expected lifetime: ");
                int expectedLifetime = userInput.getIntInput();
                chosenBirdSpecies.setExpectedLifetime(expectedLifetime);
            }

            databaseConnection.addNewBirdSpeciesJson(chosenBirdSpecies, "PUT", "http://localhost:8080/bird-species/edit");
        }
    }
}
