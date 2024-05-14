package org.example.items;

import org.example.observerPattern.NotificationService;

public class BirdSpecies {
    private int id;
    private String scientificName;
    private String englishName;
    private String family;
    private String habitat;
    private String diet;
    private String gradeOfEndangerment;
    private String appearance;
    private int expectedLifetime;

    //private NotificationService notificationService;

    public BirdSpecies(int id, String scientificName, String englishName, String family, String habitat, String diet, String gradeOfEndangerment, String appearance, int expectedLifetime) {
        this.id = id;
        this.scientificName = scientificName;
        this.englishName = englishName;
        this.family = family;
        this.habitat = habitat;
        this.diet = diet;
        this.gradeOfEndangerment = gradeOfEndangerment;
        this.appearance = appearance;
        this.expectedLifetime = expectedLifetime;
    }

    public BirdSpecies() {
    }
    public void printDetailedBirdSpecies() {
        System.out.println("Scientific name: " + scientificName);
        System.out.println("English name: " + englishName);
        System.out.println("Family: " + family);
        System.out.println("Habitat: " + habitat);
        System.out.println("Diet: " + diet);
        System.out.println("Grade of endangerment: " + gradeOfEndangerment);
        System.out.println("Appearance: " + appearance);
        System.out.println("Expected lifetime: " + expectedLifetime);
    }

    public int getId() {
        return id;
    }
    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getGradeOfEndangerment() {
        return gradeOfEndangerment;
    }

    public void setGradeOfEndangerment(String gradeOfEndangerment) {
        this.gradeOfEndangerment = gradeOfEndangerment;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public int getExpectedLifetime() {
        return expectedLifetime;
    }

    public void setExpectedLifetime(int expectedLifetime) {
        this.expectedLifetime = expectedLifetime;
    }
}
