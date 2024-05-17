package org.example.items;

public class Notification {
    private int id;
    private User subscriber;
    private Sighting sighting;
    private String message;

    public Notification(int id, User subscriber, Sighting sighting, String message) {
        this.id = id;
        this.subscriber = subscriber;
        this.sighting = sighting;
        this.message = message;
    }

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public Sighting getSightingAdded() {
        return sighting;
    }

    public String getMessage() {
        return message;
    }

    public void printDetails() {
        System.out.println(id + ". " + message);
        System.out.println("Below is all the information of this new sighting!");
        sighting.printDetailedSighting();
        System.out.println();
    }
}
