package org.example.commandPattern.subscriptionsCommands;

import org.example.ProgramFacade;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.NotificationListenerConnection;
import org.example.items.Sighting;
import org.example.items.User;
import org.example.items.UserInput;
import org.example.observerPattern.NotificationListener;

import java.util.ArrayList;

public class DeleteSubscriptionCommand implements ICommand {
    private User currentUser;
    private ProgramFacade program;

    public DeleteSubscriptionCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        currentUser = program.getMenu().getCurrentUser();
        NotificationListenerConnection listenerConnection = new NotificationListenerConnection();
        ArrayList<NotificationListener> notificationListeners =
                listenerConnection.getListenersByUsername(currentUser.getUsername());
        UserInput userInput = new UserInput();

        System.out.println("You have a subscription to following bird species.");
        for (int i = 0; i < notificationListeners.size(); i++) {
            NotificationListener listener = notificationListeners.get(i);
            //Writes the listeners array index and the bird species english name
            System.out.println(i + ". " + listener.getNotificationSender().getBirdSpecies().getEnglishName());
        }

        int maxId = notificationListeners.size();

        System.out.println("Write the id of the subscription you want to delete.");
        int chosenId = userInput.getIntInputInInterval(maxId);

        NotificationListener listener = notificationListeners.get(chosenId);

        String englishName = listener.getNotificationSender().getBirdSpecies().getEnglishName();
        System.out.println("You have chosen " + englishName + ".");
        System.out.println("Are you sure you want to unsubscribe? Write \"y\" for yes");
        String newInput = userInput.getCanBeBlankStringInput();
        if (newInput.equals("y")) {
            listenerConnection.deleteNotificationListenerById(listener.getId());
        }

    }
}
