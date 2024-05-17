package org.example.databaseConnections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.items.Notification;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NotificationConnection {

    public ArrayList<Notification> getNotificationsByUser(String username) {
        try {
            String urlString = "http://localhost:8080/notification/get-by-user";
            urlString += "?username=" + username;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();

            if (code >= 200 && code <= 299) {
                InputStream stream = connection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(stream);
                BufferedReader reader = new BufferedReader(streamReader);

                StringBuilder message = new StringBuilder();
                String readMessage = reader.readLine();
                while (readMessage != null) {
                    message.append(readMessage);
                    readMessage = reader.readLine();
                }

                Gson gson = new Gson();
                ArrayList<Notification> notifications;
                Type objectType = new TypeToken<ArrayList<Notification>>() {}.getType();
                notifications = gson.fromJson(String.valueOf(message), objectType);

                return notifications;
            }
            else {
                System.out.println("Error. Status code " + code + ".");
            }
        }
        catch (IOException e) {
            System.out.println("Error. " + e);
        }
        return null;
    }

    public void addNotification(Notification notification) {
        try{
            URL url = new URL("http://localhost:8080/notification/add-new");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            String jsonInputString = "{" +
                    "\"id\": " + notification.getId() + "," +
                    "\"subscriber\": {" +
                        "\"username\": \"" + notification.getSubscriber().getUsername() + "\"," +
                        "\"email\": \"" + notification.getSubscriber().getEmail() +"\"," +
                        "\"password\": " + "\"" + notification.getSubscriber().getPassword() + "\"" +
                        "}," +
                    "\"sighting\": {" +
                        "\"id\": " + notification.getSightingAdded().getId() + "," +
                        "\"title\": \"" + notification.getSightingAdded().getTitle() +"\"," +
                        "\"dateTime\": \"" + notification.getSightingAdded().getDate() + "\"," +
                        "\"user\": {" +
                            "\"username\": \"" + notification.getSightingAdded().getUser().getUsername() + "\"," +
                            "\"email\": \"" + notification.getSightingAdded().getUser().getEmail() +"\"," +
                            "\"password\": " + "\"" + notification.getSightingAdded().getUser().getPassword() + "\"" +
                        "}," +
                        "\"birdSpecies\": {" +
                        "\"id\": " + notification.getSightingAdded().getBirdSpecies().getId() + "," +
                        "\"scientificName\": \"" + notification.getSightingAdded().getBirdSpecies().getScientificName() +"\"," +
                        "\"englishName\": \"" + notification.getSightingAdded().getBirdSpecies().getEnglishName() + "\"," +
                        "\"family\": \"" + notification.getSightingAdded().getBirdSpecies().getFamily() + "\"," +
                        "\"habitat\": \"" + notification.getSightingAdded().getBirdSpecies().getHabitat() + "\"," +
                        "\"diet\": \"" + notification.getSightingAdded().getBirdSpecies().getDiet() + "\"," +
                        "\"gradeOfEndangerment\": \"" + notification.getSightingAdded().getBirdSpecies().getGradeOfEndangerment() + "\"," +
                        "\"appearance\": \"" + notification.getSightingAdded().getBirdSpecies().getAppearance() + "\"," +
                        "\"expectedLifetime\": " + notification.getSightingAdded().getBirdSpecies().getExpectedLifetime() +
                        "}," +
                        "\"comment\": \"" + notification.getSightingAdded().getComment() +"\"" +
                    "}," +
                    "\"message\": \"" + notification.getMessage() + "\" " +
                    "}";
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            connection.connect();


        }
        catch (Exception e){
            System.out.println("Notification could not connect to database");
            System.out.println(e.getMessage());
        }
    }
}
