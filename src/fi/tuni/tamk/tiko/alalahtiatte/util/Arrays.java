package fi.tuni.tamk.tiko.alalahtiatte.util;
/**
* The Arrays class contains various methods relating to arrays.
*
* @author Atte Ala-Lahti
 */
public class Arrays {
    /**
    * Returns an int array converted from a string array.
    *
    * Creates a new int array and adds all entries from the string array to it converted to int.
    *
    * @param array the string array to be converted
    * @return converted int array
    */
    public static int [] toIntArray(String [] array) {
        int [] intArray = new int[array.length];
        for (int i=0; i<intArray.length; i++) {
            intArray[i] = Integer.parseInt(array[i]);
        }
        return intArray;
    }
    /**
    * Returns true if int value is contained in int [] array.
    *
    * Goes trough all entries in array until one matches value. If one does, it changes the return to true and stops the loop.
    *
    * @param value integer to find in the array
    * @param array array to check for values
    * @return true or false based on if array contains value
    */
    public static boolean contains (int value, int [] array) {
        boolean found = false;
        for (int i=0; i<array.length && found == false; i++) {
            if (array[i] == value) {
                found = true;
            }
        }
        return found;
    }
    /**
    * Returns the amount of values the parameter arrays share.
    *
    * Goes through all entries of array1 and compares them to entries of array2. If they are the same, add 1 to the result and skip to the next entry in array1.
    *
    * @param array1 an int array full of unique values to compare
    * @param array2 an int array full of unique values to compare
    * @return amount of shared values between the arrays
    */
    public static int containsSameValues(int [] array1, int [] array2) {
        int result = 0;
        for (int i=0; i<array1.length; i++) {
            boolean notFound = true;
            for (int j=0; j<array2.length && notFound; j++) {
                if (array1[i] == array2[j]) {
                    result++;
                    notFound = false;
                }
            }
        }
        return result;
    }
    /**
    * Removes one entry from an array at a specified index.
    *
    * Makes a new array and adds all entries from the original array to it except at the specified index.
    *
    * @param array array to remove an entry from
    * @param index the index to remove the entry from
    * @return array with the entry removed
    */
    public static int [] removeIndex(int [] array, int index) {
        int [] newArray = new int[array.length-1];
        int j = 0;
        for (int i=0; i<array.length; i++) {
            if (i != index) {
                newArray[j] = array[i];
                j++;
            }
        }
        return newArray;
    }
    /**
    * Returns the given int array sorted smallest to largest using selection sort.
    *
    * Makes a copy of the given array and sorts it by going through it, finding the smallest number in it and swapping it with the number at the beginning.
    * Then starting again at the index after the previous starting number.
    *
    * @param array array to sort
    * @return sorted array
    */
    public static int [] sort(int [] array) {
        // Create a copy of the array
        int [] newArray = new int[array.length];
        for (int i=0; i<newArray.length; i++) {
            newArray[i] = array[i];
        }
        for (int j=0; j<newArray.length; j++) {
            // Find the smallest number and its position in the array
            int smallest = 0;
            int smallestPosition = 0;
            boolean firstTime = true;
            for (int i=j; i<newArray.length; i++) {
                if (newArray[i] < smallest || firstTime) {
                    smallest = newArray[i];
                    smallestPosition = i;
                    firstTime = false;
                }
            }
            // Swap it with the front number
            int frontNumber = newArray[j];
            newArray[j] = smallest;
            newArray[smallestPosition] = frontNumber;
        }
        return newArray;
    }
    /**
    * Returns an int array converted to a string array with 0s added to make all entries the same length.
    *
    * Finds the largest number in the array. Adds all entries from the int array to the string array and based on how many digits the largest number is adds 0s to the beginning of the entries shorter than that.
    *
    * @param array array to convert
    * @param minimumDigits the smallest length the string entries can be
    * @return an array with same length string entries converted from the parameters
    */
    public static String [] intToSameLengthString(int [] array, int minimumDigits) {
        int largest = 0;
        for (int i=0; i<array.length; i++) {
            if (array[i] > largest || largest == 0) {
                largest = array[i];
            }
        }
        largest = largest / 10;
        int maxDigitsToAdd = 0;
        while (largest > 0) {
            largest = largest / 10;
            maxDigitsToAdd++;
        }
        if (maxDigitsToAdd < minimumDigits - 1) {
            maxDigitsToAdd = minimumDigits - 1;
        }
        String [] newArray = new String[array.length];
        for (int i=0; i<newArray.length; i++) {
            newArray[i] = Integer.toString(array[i]);
            int currentNumber = array[i] / 10;
            int digitsToAdd = maxDigitsToAdd;
            while (currentNumber > 0) {
                currentNumber = currentNumber / 10;
                digitsToAdd--;
            }
            for (int j=0; j<digitsToAdd; j++) {
                newArray[i] = "0" + newArray[i];
            }
        }
        return newArray;
    }
}