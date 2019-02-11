import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class UserInputTest {


    //TWO attempts at testing input.....im not so good at testing :(
    @Test
    void test() {

        UserInput userIn = new UserInput();
        userIn.writeIn();
        //Return parser when ready?
        assertEquals("hello", userIn.getUserAnswer());

    }
    // Second attempt ....didnt know how to return the correct urser input? tried my best my dudes.
    //also dont know how to run the test using IntelliJ
    @Test
    void test2(){
        UserInput user = new UserInput();
        String input = "hello";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertEquals("hello", user.getUserAnswer());
    }

}
