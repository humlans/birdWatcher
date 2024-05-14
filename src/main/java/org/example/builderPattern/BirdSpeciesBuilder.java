package org.example.builderPattern;

import org.example.items.BirdSpecies;

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

    public BirdSpeciesBuilder scientificName(String scientificName) {
        this.scientificName = scientificName;
        return this;
    }
    public BirdSpeciesBuilder englishName(String englishName) {
        this.englishName = englishName;
        return this;
    }
    public BirdSpeciesBuilder family(String family) {
        this.family = family;
        return this;
    }
    public BirdSpeciesBuilder habitat(String habitat) {
        this.habitat = habitat;
        return this;
    }
    public BirdSpeciesBuilder diet(String diet) {
        this.diet = diet;
        return this;
    }
    public BirdSpeciesBuilder gradeOfEndangerment(String gradeOfEndangerment) {
        this.gradeOfEndangerment = gradeOfEndangerment;
        return this;
    }
    public BirdSpeciesBuilder appearance(String appearance) {
        this.appearance = appearance;
        return this;
    }
    public BirdSpeciesBuilder expectedLifetime(int expectedLifetime) {
        this.expectedLifetime = expectedLifetime;
        return this;
    }


    public BirdSpecies build() {
        return new BirdSpecies(id, scientificName, englishName, family, habitat, diet, gradeOfEndangerment, appearance, expectedLifetime);
    }

}
