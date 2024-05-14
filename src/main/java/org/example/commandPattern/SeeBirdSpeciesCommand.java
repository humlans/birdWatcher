package org.example.commandPattern;

import org.example.ProgramFacade;
import org.example.items.BirdSpecies;
import org.example.items.Menu;
import org.example.items.Sighting;
import org.example.items.UserInput;

import java.util.ArrayList;

public class SeeBirdSpeciesCommand implements ICommand{
    private ProgramFacade program;

    public SeeBirdSpeciesCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        Menu menu = program.getMenu();
        UserInput userInput = new UserInput();
        System.out.println("You have chosen to see bird species");
        ArrayList<BirdSpecies> birdSpecies = menu.getBirdSpecies();
        // If i want to do this properly, this should be a pageable so as to not overload the system if the database is large...
        // But I'm going to create it under the assumption that it is a small dataset for now...
        // THIS ONE AND THE SEE SIGHTINGS ARE ALMOST THE SAME, POLYMORPHISM??
        // OR JUST AHVE THEM IN THE SAME ONE AND HAVE AN IF STATEMENT AS DELETE HAS RIGHT NOW
        for (int i = 0; i < birdSpecies.size(); i++) {
            System.out.println(i + ". " + birdSpecies.get(i).getEnglishName());
        }
        System.out.println("For all details on one specific species, write that id or write \"Quit\" to go back");
        int choice = userInput.getIntInputOrQuit();
        if (choice == Integer.MIN_VALUE) {
            return;
        }
        if (choice < birdSpecies.size() && choice > -1) {
            BirdSpecies chosenBirdSpecies = birdSpecies.get(choice);
            chosenBirdSpecies.printDetailedBirdSpecies();
        }
    }
}