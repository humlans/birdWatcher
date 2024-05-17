package org.example.databaseConnections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.items.JSonFormatter;
import org.example.items.Sighting;
import org.example.observerPattern.NotificationListener;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NotificationListenerConnection {

    public void addNotificationListener(NotificationListener notificationListener) {
        try{
            URL url = new URL("http://localhost:8080/notification-listener/add-new");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            JSonFormatter jSonFormatter = new JSonFormatter();
            String jsonInputString = jSonFormatter.getListenerAsJson(notificationListener);


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
            System.out.println("Listener could not connect to database");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<NotificationListener> getListenersByUsername(String username) {
        try {
            String urlString = "http://localhost:8080/notification-listener/get-by-user?username=";
            urlString += username;
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
                ArrayList<NotificationListener> listeners = new ArrayList<>();
                Type listType = new TypeToken<ArrayList<NotificationListener>>() {}.getType();
                listeners = gson.fromJson(String.valueOf(message), listType);

                return listeners;

            }
            else {
                System.out.println("Could not get your listeners");
            }
        }
        catch (IOException e) {
            System.out.println("Listener: Error. " + e);
        }
        return new ArrayList<>();
    }

    public void deleteNotificationListenerById(int chosenId) {
        try{
            URL url = new URL("http://localhost:8080/notification-listener/delete?id=" + chosenId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.connect(); //This will make the connection

            int code = connection.getResponseCode();
            if(code >= 200 && code <= 299){ //response is successful
                System.out.println("Delete success! Status code: " + code);
            }
            else{
                System.out.println("Error status code: " + code);
            }
        }
        catch (Exception e){
            System.out.println("Failed to connect.");
        }

    }
}
