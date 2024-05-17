package org.example.builderPattern;

import org.example.items.BirdSpecies;
import org.example.observerPattern.NotificationSender;

public class BirdSpeciesBuilder {
    private int id;
    private String scientificName;
    private String englishName;
    private String family;
    private String habitat;
    private String diet;
    private String gradeOfEndangerment;
    private String appearance;
    private int expectedLifetime;
    public BirdSpeciesBuilder id(int id) {
        this.id = id;
        return this;
    }

    public BirdSpeciesBuilder setScientificName(String scientificName) {
        this.scientificName = scientificName;
        return this;
    }
    public BirdSpeciesBuilder setEnglishName(String englishName) {
        this.englishName = englishName;
        return this;
    }
    public BirdSpeciesBuilder setFamily(String family) {
        this.family = family;
        return this;
    }
    public BirdSpeciesBuilder setHabitat(String habitat) {
        this.habitat = habitat;
        return this;
    }
    public BirdSpeciesBuilder setDiet(String diet) {
        this.diet = diet;
        return this;
    }
    public BirdSpeciesBuilder setGradeOfEndangerment(String gradeOfEndangerment) {
        this.gradeOfEndangerment = gradeOfEndangerment;
        return this;
    }
    public BirdSpeciesBuilder setAppearance(String appearance) {
        this.appearance = appearance;
        return this;
    }
    public BirdSpeciesBuilder setExpectedLifetime(int expectedLifetime) {
        this.expectedLifetime = expectedLifetime;
        return this;
    }


    public BirdSpecies build() {
        BirdSpecies newBirdSpecies = new BirdSpecies(id, scientificName, englishName, family, habitat, diet, gradeOfEndangerment, appearance, expectedLifetime);
        newBirdSpecies.setNotificationSender(new NotificationSender(id, newBirdSpecies));
        return newBirdSpecies;
    }

}
