package chadbot;
import chadbot.bot.ChadBot;

import java.io.InputStream;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class Main {
    public static boolean DEBUG = false;
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
