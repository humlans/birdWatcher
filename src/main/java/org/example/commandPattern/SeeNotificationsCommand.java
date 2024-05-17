package org.example.commandPattern;

import org.example.ProgramFacade;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.NotificationConnection;
import org.example.items.Notification;

import java.util.ArrayList;

public class SeeNotificationsCommand implements ICommand {

    private ProgramFacade program;

    public SeeNotificationsCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        String currentUsername = program.getMenu().getCurrentUser().getUsername();
        NotificationConnection notificationConnection = new NotificationConnection();
        ArrayList<Notification> notifications = notificationConnection.getNotificationsByUser(currentUsername);

        //Prints all the users notifications.
        for (int i = notifications.size() -1; i > -1; i--) {
            notifications.get(i).printDetails();
        }
    }
}
