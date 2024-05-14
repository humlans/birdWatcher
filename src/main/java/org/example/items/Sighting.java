package org.example.items;

public class Sighting {
    private int id;
    private String title;
    private String dateTime;
    private User user;
    private BirdSpecies birdSpecies;
    private String comment;

    public Sighting(int id, String title, String dateTime, User user, BirdSpecies birdSpecies, String comment) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.user = user;
        this.birdSpecies = birdSpecies;
        this.comment = comment;
    }

    public Sighting(String title, String dateTime, User user, BirdSpecies birdSpecies, String comment) {
        this.title = title;
        this.dateTime = dateTime;
        this.user = user;
        this.birdSpecies = birdSpecies;
        this.comment = comment;
    }


    public Sighting() {
    }

    public void printDetailedSighting() {
        System.out.println(title);
        System.out.println("Date and time of sighting: " + dateTime);
        System.out.println("Bird species: " + getBirdSpecies().getEnglishName());
        System.out.println("Comment from finder: " + comment);
        System.out.println("Username of finder: " + user.getUsername());

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BirdSpecies getBirdSpecies() {
        return birdSpecies;
    }

    public void setBirdSpecies(BirdSpecies birdSpecies) {
        this.birdSpecies = birdSpecies;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
