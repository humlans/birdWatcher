package org.example.observerPattern;

import org.example.items.Sighting;
import org.example.items.User;

public interface IListener {
    public void update(Sighting sighting);
    public int getId();
    public User getSubscriber();
}
