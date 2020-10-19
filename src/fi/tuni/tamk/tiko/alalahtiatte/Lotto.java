package fi.tuni.tamk.tiko.alalahtiatte;

import fi.tuni.tamk.tiko.alalahtiatte.util.Math;
import fi.tuni.tamk.tiko.alalahtiatte.util.Arrays;
import fi.tuni.tamk.tiko.alalahtiatte.util.MyConsole;
import java.io.Console;
/**
* An application that asks you for lotto numbers and generates random lotto numbers until it generates yours within a lifetime.
*
* You have an option to show all winning number sequences and input your lotto numbers through arguments.
*
* @author Atte Ala-Lahti
*/
public class Lotto {
    final static String COMMA = ", ";
    public static void main(String [] args) {
        final int AMOUNT = 9;
        final int MIN = 1;
        final int MAX = 80;
        final int LIFETIME = 120;
        final int MAX_DIGITS = 2;
        Console c = System.console();
        int [] userNumbers = new int[AMOUNT];
        boolean showNumbers = MyConsole.readYN("Show lotto numbers? (y/n)");
        for (int i=0; i<userNumbers.length; i++) {
            int currentNumber = 0;
            try {
                currentNumber = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                currentNumber = askForNumber(MIN, MAX);
            } catch (ArrayIndexOutOfBoundsException e) {
                currentNumber = askForNumber(MIN, MAX);
            }
            while (Arrays.contains(currentNumber, userNumbers)) {
                System.out.println("Not a unique number!");
                currentNumber = askForNumber(MIN, MAX);
            }
            userNumbers[i] = currentNumber;
        }
        int years = LIFETIME;
        while (years >= LIFETIME) {
            int previouslyCorrect = 0;
            int amountCorrect = 0;
            int weeks = 0;
            while (amountCorrect < AMOUNT) {
                weeks++;
                int [] lotto = calculateLotto(MIN, MAX, AMOUNT);
                amountCorrect = Arrays.containsSameValues(userNumbers, lotto);
                if (amountCorrect > previouslyCorrect) {
                    if (showNumbers) {
                        String [] sortedUserNumbers = Arrays.intToSameLengthString(Arrays.sort(userNumbers), MAX_DIGITS);
                        String [] sortedLotto = Arrays.intToSameLengthString(Arrays.sort(lotto), MAX_DIGITS);
                        printLottoNumbers("User lotto", sortedUserNumbers);
                        printLottoNumbers("Random lotto", sortedLotto);
                    }
                    System.out.println("Got " + amountCorrect + " right! Took " + (weeks / 51) + " years.");
                    previouslyCorrect = amountCorrect;
                }
            }
            System.out.println("You won!");
            years = weeks / 51;
            if (years >= LIFETIME) {
                System.out.println("Took more than a lifetime, let's try again.");
            }
        }
    }
    /**
    * Returns a random sequence the of a desired length of unique integers between min and max.
    *
    * Creates an array of all possible integers and picks from it at random to add to the array that is to be returned. Integers are removed from the initial array as they are chosen.
    *
    * @param min minimum possible integer
    * @param max maximum possible integer
    * @param amount amount of integers the array will have
    * @return array of unique integers between min and max
    */
    public static int [] calculateLotto(int min, int max, int amount) {
        int [] allNumbers = new int[max];
        for (int i=0; i<allNumbers.length; i++) {
            allNumbers[i] = i + 1;
        }
        int [] lotto = new int[amount];
        for (int i=0; i<lotto.length; i++) {
            int index = Math.getRandom(min-1, max-1-i);
            lotto[i] = allNumbers[index];
            allNumbers = Arrays.removeIndex(allNumbers, index);
        }
        return lotto;
    }
    /**
    * Prints a name of a sequence and the contents of a given array between square brackets.
    *
    * @param lottoName the name to print before the array
    * @param array array which contents are to be printed
    */
    public static void printLottoNumbers(String lottoName, String [] array) {
        System.out.print(lottoName + ": [");
        for (int i=0; i<array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(COMMA);
            }
        }
        System.out.println("]");
    }
    /**
    * Asks for unique integers between min and max and returns them in an array.
    *
    * @param min minimum allowed integer
    * @param max maximum allowed integer
    * @return a user generated array of unique numbers between min and max 
    */
    public static int askForNumber(int min, int max) {
        System.out.println("Please give a unique number between [" + min + COMMA + max + "]");
        int number = MyConsole.readInt(min, max, "Please give a number.", "Your number has to be between [" + min + COMMA + max + "]");
        return number;
    }
}