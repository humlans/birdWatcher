package org.example.items;

import org.example.observerPattern.NotificationListener;
import org.example.observerPattern.NotificationSender;

import java.util.ArrayList;

public class JSonFormatter {

    public String getBirdspeciesAsJson(BirdSpecies birdSpecies) {
        String jsonInputString =  "{\"id\": " + birdSpecies.getId() + "," +
                "\"scientificName\": \"" + birdSpecies.getScientificName() +"\"," +
                "\"englishName\": " + "\"" + birdSpecies.getEnglishName() + "\"," +
                "\"family\": " + "\"" + birdSpecies.getFamily() + "\"," +
                "\"habitat\": " + "\"" + birdSpecies.getHabitat() + "\"," +
                "\"diet\": " + "\"" + birdSpecies.getDiet() + "\"," +
                "\"gradeOfEndangerment\": " + "\"" + birdSpecies.getGradeOfEndangerment() + "\"," +
                "\"appearance\": " + "\"" + birdSpecies.getAppearance() + "\"," +
                "\"expectedLifetime\": " + birdSpecies.getExpectedLifetime() +
                "}";
        return jsonInputString;
    }

    public String getSightingAsJson(Sighting sighting) {
        String jsonInputString = "{" +
                "\"id\": " + sighting.getId() + "," +
                "\"title\": \"" + sighting.getTitle() +"\"," +
                "\"date\": \"" + sighting.getDate() + "\"," +
                "\"user\": {" +
                "\"username\": \"" + sighting.getUser().getUsername() + "\"," +
                "\"email\": \"" + sighting.getUser().getEmail() +"\"," +
                "\"password\": \"" + sighting.getUser().getPassword() + "\"" +
                "}," +
                "\"birdSpecies\": "+ getBirdspeciesAsJson(sighting.getBirdSpecies()) + "," +
                "\"comment\": \"" + sighting.getComment() +"\"" +
                "}";
        return jsonInputString;
    }

    public String getNotificationSenderAsJson(NotificationSender notificationSender) {
        String jsonInputString = "{\"id\": " + notificationSender.getId() + "," +
                "\"notificationListeners\": [";

        ArrayList<NotificationListener> listeners = notificationSender.getListeners();
        for (int i = 0; i < listeners.size(); i++) {
            jsonInputString += "{ \"id\":" + listeners.get(i).getId() + ","
                    + "\"subscriber\": " + getUserAsJson(listeners.get(i).getSubscriber()) +
                    "}";
            if (i != listeners.size()-1) {
                jsonInputString += ",";
            }
        }
        jsonInputString += "], \"birdSpecies\": " + getBirdspeciesAsJson(notificationSender.getBirdSpecies()) +
                "}";
        return jsonInputString;
    }

    public String getUserAsJson(User user) {
        String jsonInputString = "{" +
                "\"username\": \"" + user.getUsername() + "\"," +
                        "\"email\": \"" + user.getEmail() + "\"," +
                        "\"password\":\"" + user.getPassword() + "\"}";
        return jsonInputString;
    }


    public String getListenerAsJson(NotificationListener listener) {
        String jsonInputString = "{\"id\": " + listener.getId() + "," +
                                "\"subscriber\": " + getUserAsJson(listener.getSubscriber()) + "," +
                                "\"notificationSender\": " +
                                getNotificationSenderAsJson(listener.getNotificationSender()) + "}";
        return jsonInputString;
    }
}
