package org.example.commandPattern.subscriptionsCommands;

import org.example.ProgramFacade;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.NotificationListenerConnection;
import org.example.items.User;
import org.example.observerPattern.NotificationListener;

import java.util.ArrayList;

public class SeeSubscriptionsCommand implements ICommand {

    private User currentUser;

    private ProgramFacade program;
    public SeeSubscriptionsCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        currentUser = program.getMenu().getCurrentUser();
        NotificationListenerConnection listenerConnection = new NotificationListenerConnection();
        ArrayList<NotificationListener> notificationListeners =
                                        listenerConnection.getListenersByUsername(currentUser.getUsername());

        System.out.println("You have a subscription to following bird species.");
        for (NotificationListener listener: notificationListeners) {
            //Writes the bird species english name
            System.out.println(listener.getNotificationSender().getBirdSpecies().getEnglishName());
        }

    }
}
