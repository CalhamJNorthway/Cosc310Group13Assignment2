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


}
