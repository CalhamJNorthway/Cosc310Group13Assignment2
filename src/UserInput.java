import java.util.Scanner;

public class UserInput {
    public UserInput() {

    }

    public void writeInput() {
        System.out.println("Type a user name!");
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String userAnswer = in.nextLine().toLowerCase();
            if (!userAnswer.equals("quit"))
                System.out.println(userAnswer + ", how may I help you? To exit, simply type 'Quit'");

            if (userAnswer.equals("quit") || userAnswer.equals("Quit"))
                break;
            else {
                System.out.println("Users answer: " + userAnswer);
            }
        }
        in.close();
    }

    public static void main(String[] args) {
        UserInput user = new UserInput();
        user.writeInput();

    }

}