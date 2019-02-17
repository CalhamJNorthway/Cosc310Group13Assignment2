package chadbot;
import java.util.Scanner;

@SuppressWarnings("WeakerAccess")
public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public Main() {
        System.out.println("Hidden test");
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
