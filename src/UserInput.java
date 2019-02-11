import java.util.Scanner;

public class UserInput {

public UserInput(){

}
//User inputs data into console and scanner reads input , when the parser is made for our project we replace
//return statement with parser and system will make a reply based on sentance structure.
//built in end chat with 'quit', system will terminate.
    public String writeInput() {
        System.out.println(" how may I help you? To exit, simply type 'Quit'");
        Scanner in = new Scanner(System.in);
        String userAnswer = in.nextLine().toLowerCase();
        while (in.hasNextLine()) {

            // TODO: return to parser instead when it is built
            if (userAnswer.equals("quit") || userAnswer.equals("Quit"))
                break;
            else {
                return ("Users answer: " + userAnswer);
            }

        }
        in.close();
        return ("quit");
    }

}