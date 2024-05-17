package org.example.observerPattern;

import org.example.databaseConnections.DatabaseConnection;
import org.example.databaseConnections.NotificationConnection;
import org.example.items.BirdSpecies;
import org.example.items.Notification;
import org.example.items.Sighting;
import org.example.items.User;

public class NotificationListener implements IListener {
    private int id;
    private User subscriber;
    private NotificationSender notificationSender;


    public NotificationListener(int id, User subscriber, NotificationSender notificationSender) {
        this.id = id;
        this.subscriber = subscriber;
        this.notificationSender = notificationSender;
    }

    @Override
    public void update(Sighting newSighting) {
        NotificationConnection notificationConnection = new NotificationConnection();
        DatabaseConnection databaseConnection = new DatabaseConnection();

        //Creates a personalized message for the notification.
        String message = subscriber.getUsername() + ", a " +
                        newSighting.getBirdSpecies().getEnglishName() + " has been sighted!";

        //Gets the highest used id.
        int notificationId = databaseConnection.getLastId("http://localhost:8080/notification/get-last-id");

        //Creates a new notification and saves it to the database.
        Notification newNotification = new Notification (notificationId, subscriber, newSighting, message);
        notificationConnection.addNotification(newNotification);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public User getSubscriber() {
        return subscriber;
    }

    public NotificationSender getNotificationSender() {
        return notificationSender;
    }

}
