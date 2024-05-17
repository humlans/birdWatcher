package org.example.observerPattern;

import org.example.items.BirdSpecies;
import org.example.items.Sighting;

import java.util.ArrayList;

public class NotificationSender {
    private int id;
    private ArrayList<NotificationListener> listeners;
    private BirdSpecies birdSpecies;

    public NotificationSender(int id, BirdSpecies birdSpecies) {
        this.id = id;
        listeners = new ArrayList<NotificationListener>();
        this.birdSpecies = birdSpecies;
    }

    public NotificationSender() {
    }

    public NotificationSender(int id, ArrayList<NotificationListener> listeners) {
        this.id = id;
        this.listeners = listeners;
    }

    public void subscribe(NotificationListener subscriber) {
        listeners.add(subscriber);
    }

    public void unsubscribe(IListener subscriber) {
        listeners.remove(subscriber);
    }

    public void notifyUsers(Sighting sighting) {
        listeners.forEach(listener -> listener.update(sighting));
    }

    public int getId() {
        return id;
    }

    public ArrayList<NotificationListener> getListeners() {
        return listeners;
    }

    public void setListeners(ArrayList<NotificationListener> listeners) {
        this.listeners = listeners;
    }

    public BirdSpecies getBirdSpecies() {
        return birdSpecies;
    }
}
