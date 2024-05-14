package org.example.handlers;

import org.example.items.Notification;

import java.util.ArrayList;

public class NotificationsHandler {
    private ArrayList<Notification> notifications;

    public NotificationsHandler(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<Notification> getAllNotifications() {
        // Create a method for database connection to get all notifications
        ArrayList<Notification> notificationList = new ArrayList<>();
        return notificationList;
    }
    public ArrayList<Notification> getNotificationsByUsername(String username) {
        // Create a method for database connection to get notifications by user
        ArrayList<Notification> notificationList = new ArrayList<>();
        return notificationList;
    }

}
