package fi.tuni.tamk.tiko.alalahtiatte.util;
/**
* The Math class contains methods for performing various calculations.
*
* @author Atte Ala-Lahti
 */
public class Math {
    /**
    * Returns a random int value between int min and int max.
    *
    * Chooses a random double [0-1[ and multiplies it by the amount of possible values, which is (max-min)+1.
    * Then the minimum value is added.
    *
    * @param min minimun possible value that can be returned
    * @param max maximum possible value that can be returned
    * @return a random value between min and max
     */
    public static int getRandom(int min, int max) {
        return min + (int) (java.lang.Math.random() * ((max - min) + 1));
    }
}