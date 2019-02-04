
public class Main {
    public static void main(String[] args) {
        
        UserInput user = new UserInput();
        String output = "";

        while(output.equals("quit") != true){
            output = user.writeInput();
            System.out.println(output);
        }

    }
}