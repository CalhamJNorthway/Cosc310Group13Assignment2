package chadbot;
import chadbot.bot.ChadBot;
import sun.security.util.Resources;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class Main {
    private ChadBot chadBot;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public Main() {
        InputStream url = Main.class.getResourceAsStream("ChatBotResponses.xml");
        chadBot = new ChadBot(url);
    }

    public void run() {
        System.out.println("How may I help you? To exit, simply type 'Quit'");
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine().toLowerCase();
            String response = chadBot.getResponse(input);
            System.out.println(response);
        }while (!closeChat(input));
        scanner.close();
    }

    protected boolean closeChat(String input) {
        return input.trim().toLowerCase().equals("quit");
    }
}
