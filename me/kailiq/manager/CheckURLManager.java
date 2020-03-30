package me.kailiq.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class CheckURLManager {
    public static boolean check(String code) {
        try {
            URL url = new URL(code);
            url.openStream();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
