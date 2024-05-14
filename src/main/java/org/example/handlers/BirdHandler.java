package org.example.handlers;

import org.example.items.BirdSpecies;

import java.util.ArrayList;

public class BirdHandler {
    ArrayList<BirdSpecies> birdSpeciesList;
    ArrayList<BirdSpecies> suggestedBirdSpecies;

    public BirdHandler(ArrayList<BirdSpecies> birdSpeciesList, ArrayList<BirdSpecies> suggestedBirdSpecies) {
        this.birdSpeciesList = birdSpeciesList;
        this.suggestedBirdSpecies = suggestedBirdSpecies;
    }

}
