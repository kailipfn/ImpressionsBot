package me.kailiq;

import me.kailiq.listener.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {
    private static String token = "";
    public static void main(String[] args) {
        try {
            JDA jda = new JDABuilder(token).setActivity(Activity.playing(".help でヘルプを表示")).build();
            jda.addEventListener(new MessageListener());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
