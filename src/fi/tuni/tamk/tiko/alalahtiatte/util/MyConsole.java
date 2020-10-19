package fi.tuni.tamk.tiko.alalahtiatte.util;

import java.io.Console;
/**
* The MyConsole class contains methods relating to the console.
*
* @author Atte Ala-Lahti
 */

public class MyConsole {
    /**
    * Reads from the user until an integer between min and max is inputted, otherwise displays an error message.
    *
    * Keeps asking for user input in a loop until the input can be parsed as an int and the catch doesn't trigger.
    * Then checks if the input is between min and max and marks it as incorrect if it isn't.
    *
    * @param min minimum integer allowed as input
    * @param max maximum integer allowed as input
    * @param errorMessageNonNumeric error message displayed when the user input is not an integer
    * @param errorMessageNonMinAndMax error message displayed when the user inputs is smaller than min or larger than max
    * @return user input between min and max converted to int
    */
    public static int readInt(int min, int max, String errorMessageNonNumeric, String errorMessageNonMinAndMax) {
        Console c = System.console();
        boolean incorrectInput = true;
        int input = 0;
        while (incorrectInput) {
            incorrectInput = false;
            try {
                input = Integer.parseInt(c.readLine());
            } catch(NumberFormatException e) {
                System.out.println(errorMessageNonNumeric);
                incorrectInput = true;
            }
            if (incorrectInput == false && (input < min || input > max)) {
                System.out.println(errorMessageNonMinAndMax);
                incorrectInput = true;
            }
        }
        return input;
    }
    /**
    * Asks the user a given question and returns a boolean value based on if the user inputs y or n.
    *
    * Keeps asking the user the question until y or n is inputted. Then return true if the input is y or return false if the input is n.
    *
    * @param question yes or no question to be asked
    * @return true if y inputted, false if n inputted
    */
    public static boolean readYN(String question) {
        Console c = System.console();
        String userInput = "";
        while (!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n")) {
            System.out.println(question);
            userInput = c.readLine();
        }
        boolean result = false;
        if (userInput.equalsIgnoreCase("y")) {
            result = true;
        }
        return result;
    }
}