package org.example.commandPattern.sightingCommands;

import org.example.ProgramFacade;
import org.example.algorithms.MergeSort;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.SightingConnection;
import org.example.items.Menu;
import org.example.items.Sighting;
import org.example.items.UserInput;

import java.util.ArrayList;

public class SeeSightingCommand implements ICommand {


    public SeeSightingCommand() {

    }

    @Override
    public void execute() {
        SightingConnection sightingConnection = new SightingConnection();
        UserInput userInput = new UserInput();
        ArrayList<Sighting> sightings = sightingConnection.getAllSightings();

        //Sorts the sightings first by title and then by date to get the most newly added sighting
        // at the bottom so that they are closest to when the user gets to choose.
        MergeSort mergeSort = new MergeSort();
        mergeSort.sortAlphabetically(sightings, "title");
        mergeSort.sortAlphabetically(sightings, "dateTime");

        //Prints all the existing sightings titles and dates.
        System.out.println("You have chosen to see sightings");
        for (int i = 0; i < sightings.size(); i++) {
            System.out.println(i + ". " + sightings.get(i).getTitle() + ", date: " + sightings.get(i).getDate());
        }

        //Lets the user choose whether they want to see more information on a specific sighting
        System.out.println("For all details on one specific sighting, write that id or write \"Quit\" to go back");
        int choice = userInput.getIntInputOrQuit();
        if(choice == Integer.MIN_VALUE) {
            return;
        }
        if(choice < sightings.size() && choice > -1) {
            Sighting chosenSighting = sightings.get(choice);
            chosenSighting.printDetailedSighting();
        }
    }
}
