package org.example.databaseConnections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.items.JSonFormatter;
import org.example.items.Sighting;
import org.example.observerPattern.IListener;
import org.example.observerPattern.NotificationListener;
import org.example.observerPattern.NotificationSender;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NotificationSenderConnection {

    public void addOrEditNotificationSender(NotificationSender notificationSender, String putOrPost, String urlEnding) {
        try{
            URL url = new URL("http://localhost:8080/notification-sender/" + urlEnding);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(putOrPost);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            //String jsonInputString = getJsonInputString(notificationSender);

            JSonFormatter jSonFormatter = new JSonFormatter();
            String jsonInputString = jSonFormatter.getNotificationSenderAsJson(notificationSender);

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
            connection.connect(); //This will make the connection
        }
        catch (Exception e){
            System.out.println("Notification sender: Error " + e.getMessage());
        }
    }

    private String getJsonInputString(NotificationSender notificationSender) {
        String jsonInputString = "{\"id\": " + notificationSender.getId() + "," +
                "\"notificationListeners\": [";

        ArrayList<NotificationListener> listeners = notificationSender.getListeners();
        for (int i = 0; i < listeners.size(); i++) {
            jsonInputString += "{ \"id\":" + listeners.get(i).getId() + ","
                            + "\"subscriber\": {" +
                                        "\"username\": \"" + listeners.get(i).getSubscriber().getUsername() + "\"," +
                                        "\"email\": \"" + listeners.get(i).getSubscriber().getEmail() + "\"," +
                                        "\"password\":\"" + listeners.get(i).getSubscriber().getPassword() + "\"}" +
                            "}";
            if (i != listeners.size()-1) {
                jsonInputString += ",";
            }
        }
        jsonInputString += "]}";
        return jsonInputString;
    }

    public NotificationSender getSenderById(int id) {
        try {
            String urlString = "http://localhost:8080/notification-sender/get-by-id?id=";
            urlString += id;
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

                //Can this be done in a better way? I just want the arraylist to be filled automatically...
                ArrayList<NotificationListener> notificationListeners;
                Type listType = new TypeToken<ArrayList<NotificationListener>>() {}.getType();
                String[] jsonArray = String.valueOf(message).split("\\[");
                String[] temporaryjsonArray = jsonArray[1].split("\\]");
                String jsonOfNotificationListeners = "[" + temporaryjsonArray[0] + "]";
                notificationListeners = gson.fromJson(jsonOfNotificationListeners, listType);

                NotificationSender notificationSender;
                Type objectType = new TypeToken<NotificationSender>() {}.getType();
                notificationSender = gson.fromJson(String.valueOf(message), objectType);

                notificationSender.setListeners(notificationListeners);

                return notificationSender;

            }
            else {
                System.out.println("Could not get sender from database");
            }
        }
        catch (IOException e) {
            System.out.println("Sender: Error. " + e);
        }
        return null;
    }


}
