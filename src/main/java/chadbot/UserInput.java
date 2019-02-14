package chadbot;
import java.util.Scanner;


public class UserInput {
    public UserInput() {

    }

    public static String writeInput() {
        System.out.println("Type a user name!");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String userAnswer = in.nextLine().toLowerCase();
            System.out.println(userAnswer + ", how may I help you? To exit, simply type 'Quit'");
            // TODO: return to parser instead when it is built
            if (userAnswer.equals("quit") || userAnswer.equals("Quit"))
                break;
            else {
                return("Users answer: " + userAnswer);
            }
        }
        in.close();
        return("quit");
    }

    public void defaultResponse(String response) {
        if (response == null || response.equals("") || response.equals(" ")) {
            System.out.println("Sorry, I don't understand this. Can you try again? ");
        } else {
            System.out.println(response);
            UserInput.writeInput();
        }
    }

}