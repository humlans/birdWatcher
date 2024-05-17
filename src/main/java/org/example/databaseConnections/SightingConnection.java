package org.example.databaseConnections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.items.JSonFormatter;
import org.example.items.Sighting;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SightingConnection {
    public ArrayList<Sighting> getAllSightings() {
        try {
            ArrayList<Sighting> sightings = new ArrayList<>();
            URL url = new URL("http://localhost:8080/sighting/get-all");
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
                sightings = new ArrayList<>();
                Type listType = new TypeToken<ArrayList<Sighting>>() {}.getType();
                sightings = gson.fromJson(String.valueOf(message), listType);

                return sightings;

            }
            else {
                System.out.println("Could not get sightings");
            }
        }
        catch (IOException e) {
            System.out.println("Sightings: Error. " + e);
        }
        return new ArrayList<>();
    }

    public ArrayList<Sighting> getSightingsByUser(String username) {
        try {
            String urlString = "http://localhost:8080/sighting/get-by-user?username=";
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
                ArrayList<Sighting> sightings = new ArrayList<>();
                Type listType = new TypeToken<ArrayList<Sighting>>() {}.getType();
                sightings = gson.fromJson(String.valueOf(message), listType);

                return sightings;

            }
            else {
                System.out.println("Could not get your sightings");
            }
        }
        catch (IOException e) {
            System.out.println("Sighting: Error. " + e);
        }
        return new ArrayList<>();
    }

    public void addOrEditSightingJson(Sighting sighting, String putOrPost, String urlString) {
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(putOrPost);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            JSonFormatter jSonFormatter = new JSonFormatter();
            String jsonInputString = jSonFormatter.getSightingAsJson(sighting);

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
            if(putOrPost.equals("PUT")) {
                System.out.println("Sighting edited successfully");
            }
            else {
                System.out.println("Sighting added successfully");
            }

        }
        catch (Exception e){
            System.out.println("Could not connect the sighting to database.");
            System.out.println(e.getMessage());
        }
    }

}
