package org.example.commandPattern.birdSpeciesCommands;

import org.example.ProgramFacade;
import org.example.algorithms.QuickSort;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.DatabaseConnection;
import org.example.items.BirdSpecies;
import org.example.items.Menu;
import org.example.items.Sighting;
import org.example.items.UserInput;

import java.util.ArrayList;

public class SeeBirdSpeciesCommand implements ICommand {


    public SeeBirdSpeciesCommand() {

    }

    @Override
    public void execute() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserInput userInput = new UserInput();
        ArrayList<BirdSpecies> birdSpecies = databaseConnection.getAllBirdSpecies();

        //Sorts the array according to english name.
        QuickSort quickSort = new QuickSort();
        quickSort.sortBirdSpeciesAlphabetically(birdSpecies, 0, birdSpecies.size()-1);

        //Prints the name of all existing bird species.
        System.out.println("You have chosen to see bird species");
        for (int i = 0; i < birdSpecies.size(); i++) {
            System.out.println(i + ". " + birdSpecies.get(i).getEnglishName());
        }

        //Gives the user the choice to see more information or return to the menu.
        System.out.println("For all details on one specific species, write that id or write \"Quit\" to go back");
        int choice = userInput.getIntInputOrQuit();
        if (choice == Integer.MIN_VALUE) {
            return;
        }
        if (choice < birdSpecies.size() && choice > -1) {
            //Displays the information regarding the chosen bird species.
            BirdSpecies chosenBirdSpecies = birdSpecies.get(choice);
            chosenBirdSpecies.printDetailedBirdSpecies();
        }
    }
}