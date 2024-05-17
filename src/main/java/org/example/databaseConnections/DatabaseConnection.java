package org.example.databaseConnections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.items.BirdSpecies;
import org.example.items.JSonFormatter;
import org.example.items.Sighting;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

// Should I split the different objects into different database connections
// I may be able to create an interface because they are quite closely related, the only difference should be the return type...
public class DatabaseConnection {
    public ArrayList<BirdSpecies> getAllBirdSpecies() {
        try {
            URL url = new URL("http://localhost:8080/bird-species/get-all");
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
                ArrayList<BirdSpecies> birdSpecies = new ArrayList<>();
                Type listType = new TypeToken<ArrayList<BirdSpecies>>() {}.getType();
                birdSpecies = gson.fromJson(String.valueOf(message), listType);

                return birdSpecies;

            }
            else {
                System.out.println("Error. Status code " + code + ".");
            }
        }
        catch (IOException e) {
            System.out.println("Error. " + e);
        }
        return new ArrayList<>();
    }

    public BirdSpecies getBirdSpeciesByEnglishName(String englishName) {
        try {
            String encodedEnglishName = URLEncoder.encode(englishName, StandardCharsets.UTF_8);
            String urlString = "http://localhost:8080/bird-species/get-by-english-name";
            urlString += "?englishName=" + encodedEnglishName;

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
                BirdSpecies birdSpecies = new BirdSpecies();
                Type objectType = new TypeToken<BirdSpecies>() {}.getType();
                birdSpecies = gson.fromJson(String.valueOf(message), objectType);

                return birdSpecies;

            }
            else {
                System.out.println("Error. Could not find bird species");
            }
        }
        catch (IOException e) {
            System.out.println("Error. " + e);
        }
        return null;
    }

    public int getLastId(String urlString) {
        try {
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
                String json = message.toString();
                Gson gson = new Gson();
                Type type = new TypeToken<Integer>() {}.getType();
                int lastId = gson.fromJson(json, type);

                return lastId;

            }
            else {
                System.out.println("Error. Status code " + code + ".");
            }
        }
        catch (IOException e) {
            System.out.println("Error. " + e);
        }
        return Integer.MIN_VALUE;
    }

    public void addNewBirdSpeciesJson(BirdSpecies birdSpecies, String putOrPost, String urlString) {
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(putOrPost);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            //Turns the birdSpecies into json format
            JSonFormatter jSonFormatter = new JSonFormatter();
            String jsonInputString = jSonFormatter.getBirdspeciesAsJson(birdSpecies);

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
            System.out.println("Something went wrong.");
            System.out.println(e.getMessage());
            System.out.println("The scientific name has to be unique.");
        }
    }

    public void deleteBirdSpeciesOrSighting(int chosenId, String mapping) {
        try{
            URL url = new URL("http://localhost:8080/" + mapping + "/delete?id=" + chosenId);
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
            System.out.println("Nothing to connect to, everything crashed.");
            System.out.println(e.getMessage());
        }
    }
}
