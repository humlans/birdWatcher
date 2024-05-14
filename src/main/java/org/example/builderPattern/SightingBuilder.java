package org.example.builderPattern;

import org.example.items.BirdSpecies;
import org.example.items.Sighting;
import org.example.items.User;

public class SightingBuilder {
    private int id;
    private String title;
    private String dateTime;
    private User user;
    private BirdSpecies birdSpecies;
    private String comment;

    public SightingBuilder setId(int id) {
        this.id = id;
        return this;
    }
    public SightingBuilder setTitle(String title) {
        this.title = title;
        return this;
    }
    public SightingBuilder setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }
    public SightingBuilder setCurrentUser(User user) {
        this.user = user;
        return this;
    }
    public SightingBuilder setBirdSpecies(BirdSpecies birdSpecies) {
        this.birdSpecies = birdSpecies;
        return this;
    }
    public SightingBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Sighting build() {
        return new Sighting(id, title, dateTime, user, birdSpecies, comment);
    }


}
