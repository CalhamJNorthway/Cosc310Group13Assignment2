package chadbot;
import chadbot.bot.ChadBot;

import java.io.File;
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
        URL url = Main.class.getResource("ChatBotResponses.xml");
        File file = new File(url.getFile());
        chadBot = new ChadBot(file);
    }

    public void run() {
        System.out.println("How may I help you? To exit, simply type 'Quit'");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        while (!closeChat(input)) {
            input = scanner.nextLine().toLowerCase();

        }
        scanner.close();
    }

    protected boolean closeChat(String input) {
        return input.trim().toLowerCase().equals("quit");
    }
}
