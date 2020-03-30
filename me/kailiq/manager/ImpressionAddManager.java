package me.kailiq.manager;

import me.kailiq.ImpBoolean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImpressionAddManager {

    public static void add(String link,int amount) {
        ImpBoolean.set(true);
        for(int i = 0; i < amount; i++) {
            try {
                URL url = new URL(link);
                url.openStream();
            } catch (MalformedURLException e) {
                System.out.println("Malformed URL: " + e.getMessage());
            } catch (IOException e) {
                return;
            }
        }
        ImpBoolean.set(false);
    }
}
