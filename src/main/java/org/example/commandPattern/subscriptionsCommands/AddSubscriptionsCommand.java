package org.example.commandPattern.subscriptionsCommands;

import org.example.ProgramFacade;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.DatabaseConnection;
import org.example.databaseConnections.NotificationListenerConnection;
import org.example.databaseConnections.NotificationSenderConnection;
import org.example.items.BirdSpecies;
import org.example.items.User;
import org.example.items.UserInput;
import org.example.observerPattern.NotificationListener;
import org.example.observerPattern.NotificationSender;

import java.util.ArrayList;

public class AddSubscriptionsCommand implements ICommand {
    private User currentUser;

    private ProgramFacade program;
    public AddSubscriptionsCommand(ProgramFacade program) {
      this.program = program;
    }

    @Override
    public void execute() {
        currentUser = program.getMenu().getCurrentUser();
        System.out.println("You have chosen to add a subscription.");
        System.out.println("By subscribing to a bird species you will get a notification every time that species is sighted!");
        System.out.println("These are all the existing bird species:");

        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserInput userInput = new UserInput();
        ArrayList<BirdSpecies> birdSpecies = databaseConnection.getAllBirdSpecies();
        for (int i = 0; i < birdSpecies.size(); i++) {
            System.out.println(i + ". " + birdSpecies.get(i).getEnglishName());
        }

        //Lets the user chose if they want to subscribe to a specific bird or go back to the menu.
        System.out.println("Write an id or write \"Quit\" to return to menu");
        int choice = userInput.getIntInputOrQuit();
        if(choice < birdSpecies.size() && choice > -1) {
            //Checks if the logged-in user already has a listener, therefore is a subscriber,
            //of this bird species and adds them if they are not.
            BirdSpecies chosenBirdSpecies = birdSpecies.get(choice);
            checkAndAddIfNotAlreadySubscribing(chosenBirdSpecies);
        }
        else {
            System.out.println("A bird species with that id doesn't exist.");
        }

    }

    private void checkAndAddIfNotAlreadySubscribing(BirdSpecies chosenBirdSpecies) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        NotificationSenderConnection senderConnection = new NotificationSenderConnection();
        NotificationSender notificationSender = senderConnection.getSenderById(chosenBirdSpecies.getId());
        ArrayList<NotificationListener> notificationListeners = notificationSender.getListeners();

        //Checks if the current user already has a subscription to the chosen bird species.
        if (notificationListeners.size() > 0) {
            boolean alreadyHasSubscription = checkSubscriptionStatus(notificationListeners);
            if (alreadyHasSubscription) {
                return;
            }
        }

        //Gets the highest used id and created the new notificationListeners id by adding 1.
        int nextId = databaseConnection.getLastId("http://localhost:8080/notification-listener/get-last-id")
                +1;

        //Creates the new notificationListener
        NotificationListener notificationListener = new NotificationListener(nextId,
                                                    currentUser,
                                                    notificationSender);

        //Adds the listener to the sender and saves it to the database.
        notificationSender.subscribe(notificationListener);
        NotificationListenerConnection listenerConnection = new NotificationListenerConnection();
        listenerConnection.addNotificationListener(notificationListener);
        //Connects the right notificationSender to the new notificationListener.
        senderConnection.addOrEditNotificationSender(notificationSender, "PUT", "edit");
        System.out.println("You will now get notifications for " + chosenBirdSpecies.getEnglishName() + "!");
    }

    private boolean checkSubscriptionStatus(ArrayList<NotificationListener> notificationListeners) {
        for (NotificationListener listener : notificationListeners) {
            if (listener.getSubscriber().getUsername().equals(currentUser.getUsername())) {
                System.out.println("You already have a subscription to this bird species.");
                return true;
            }
        }
        return false;
    }
}
