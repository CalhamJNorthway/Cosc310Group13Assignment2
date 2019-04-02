package chadbot;

import chadbot.bot.ChadBot;

import javax.swing.*;
import javax.swing.plaf.metal.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class Main extends Frame {
    public static boolean DEBUG = true;
    private ChadBot chadBot;
    private BotGUI gui;

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public Main() {
        InputStream url = Main.class.getResourceAsStream("ChatBotResponses.xml");
        chadBot = new ChadBot(url);
        gui = new BotGUI();
        gui.initGUI();
        // Set size of panel

        // jframe.getcontentpane(.setSize());
    }

    public void run() {
        gui.responseArea.append("How may I help you? To exit, simply type 'Quit' \n");
        // putting users input through UI and scanner
        String newLine = "/n";

        gui.textInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userInput = gui.textInput.getText();
                gui.scrollBoi.setValue(gui.scrollBoi.getMaximum());

                if (closeChat(userInput) == true) {
                    gui.closeGUI();
                    System.exit(0);
                } else {
                    String response = chadBot.getResponse(userInput);
                    gui.responseArea.append("You: " + userInput + "\n" + "ChadBot: " + response + "\n");
                    gui.textInput.setText("");
                }

            }

        });
    }

    protected boolean closeChat(String input) {
        return input.trim().toLowerCase().equals("quit");
    }
}
