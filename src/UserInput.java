import java.util.Scanner;;

public class UserInput {
public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    
    System.out.print("input your name:");
    String userName = in.nextLine();
    System.out.println("Hello " + userName +"!");
    System.out.println("you cutie ;)");

    in.close();
}

}