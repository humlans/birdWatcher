package org.example.databaseConnections;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.items.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserConnection {
    public User login(String username, String password) {
        try {
            String urlString = "http://localhost:8080/user/login";
            urlString += "?username=" + username;
            urlString += "&password=" + password;

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
                User user = new User();
                Type objectType = new TypeToken<User>() {}.getType();
                user = gson.fromJson(String.valueOf(message), objectType);

                return user;
            }
            else {
                System.out.println("Could not log in.");
            }
        }
        catch (IOException e) {
            System.out.println("Error. " + e);
        }
        return null;
    }
}
