package chadbot;
import chadbot.bot.data.Tokenizer;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Hello World");

        String output = "";

        while(output.equals("quit") != true){
            output = UserInput.writeInput();
            System.out.println(output);
        }

    }

    public Main() {

    }

    public void run() {

    }

    public void handleChadBotResponse(String response) {
        // TODO: Change next line to handle default chad response once ready
        if (response == null || response.equals("") || response.equals(" ")) {
            System.out.println("some default response");
        } else {
            System.out.println(response);
            UserInput.writeInput();
        }
    }

}
