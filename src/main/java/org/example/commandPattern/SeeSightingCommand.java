package org.example.commandPattern;

import org.example.ProgramFacade;
import org.example.items.Menu;
import org.example.items.Sighting;
import org.example.items.UserInput;

import java.util.ArrayList;

public class SeeSightingCommand implements ICommand{
    private ProgramFacade program;

    public SeeSightingCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        Menu menu = program.getMenu();
        UserInput userInput = new UserInput();
        System.out.println("You have chosen to see sightings");
        ArrayList<Sighting> sightings = menu.getAllSightings();
        // If i want to do this properly, this should be a pageable so as to not overload the system if the database is large...
        // But I'm going to create it under the assumption that it is a small dataset for now...
        for (int i = 0; i < sightings.size(); i++) {
            System.out.println(i + ". " + sightings.get(i).getTitle());
        }
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
