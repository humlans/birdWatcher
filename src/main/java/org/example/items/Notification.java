package org.example.items;

public class Notification {
    private User subscriber;
    private BirdSpecies birdSpecies;
    private Sighting sightingAdded;

    public Notification(User subscriber, BirdSpecies birdSpeciesSubscription, Sighting sightingAdded) {
        this.subscriber = subscriber;
        this.birdSpecies = birdSpeciesSubscription;
        this.sightingAdded = sightingAdded;
    }

    public Notification() {
    }

    public User getSubscriber() {
        return subscriber;
    }

    public BirdSpecies getBirdSpecies() {
        return birdSpecies;
    }

    public Sighting getSightingAdded() {
        return sightingAdded;
    }
}
