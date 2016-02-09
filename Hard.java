import java.util.HashMap;
import java.util.Random;

public class Hard {
    /**
     * 17.1
     * Add Without Plus: Write a function that adds two numbers. You should not use + or any
     * arithmetic operators.
     */
    public static int addWithoutPlus(int a, int b) {
        while(b != 0) {
            int sum = a ^ b; // add without carrying
            int carry = (a & b) << 1; // carry, but don't add
            a = sum;
            b = carry;
        }
        return a;
    }

    /**
     * 17.2
     * Shuffle: Write a method to shuffle a deck of cards. It must be a perfect shuffle - in other
     * words, each of the 52! permutations of the deck has to be equally likely. Assume that you are
     * given a random number generator which is perfect.
     */
    public static void shuffle(int[] a) {
        Random rand = new Random();
        for(int i=0; i<a.length; i++) {
            int r = rand.nextInt(i);
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }

    /**
     * 17.3
     * Random Set: Write a method to randomly generate a set of m integers fron an array of size n.
     * Each element must have equal probability of being chosen.
     */
    public static int[] randomSet(int[] set, int m) {
        int[] subset = new int[m];

        Random rand = new Random();

        for(int i=0; i<m; i++) {
            subset[i] = set[i];
        }

        for(int i=m; i<set.length; i++) {
            int r = rand.nextInt(i);
            if(r < m) {
                subset[r] = set[i];
            }
        }

        return subset;
    }

    /**
     * 17.4
     * Missing Number: An array A contains all the integers from 0 to n, except from one Number
     * which is missing. In the problem, we cannot access an entire integer in A with a single
     * operation. The elements of A are represented in binary, and the only operation we ca use to
     * access them is "fetch the jth bit of A[i]", which takes constant time. Write code to find them
     * missing integer. Can you do it in O(n) time?
     */
    // public static int findMissing(ArrayList<BitInteger> input, int column) {
    //     if(column >= BitInteger.INTEGER_SIZE) {
    //         return 0;
    //     }
    //     ArrayList<BitInteger> oneBits = new ArrayList<BitInteger>(input.size()/2);
    //     ArrayList<BitInteger> zeroBits = new ArrayList<BitInteger>(input.size()/2);
    //
    //     for(BitInteger t:input) {
    //         if(t.fetch(column) == 0) {
    //             zeroBits.add(t);
    //         } else {
    //             oneBits.add(t);
    //         }
    //     }
    //     if(zeroBits.size() <= oneBits.size()) {
    //         int v = findMissing(zeroBits, column+1);
    //         return (v<<1) | 0;
    //     } else {
    //         int v = findMissing(oneBits, column+1);
    //         return (v<<1) | 1;
    //     }
    // }

    /**
     * 17.5
     * Letters and Numbers: Give an array filled with letters and numbers, find the longest subarray
     * with an equal number of letters and numbers.
     */
    public static void lettersAndNumbers(char[] array) {
        int[] letters = new int[array.length];
        int[] digits = new int[array.length];

        if(Character.isDigit(array[0])) {
            digits[0] = 1;
        } else {
            letters[0] = 1;
        }

        for(int i=1; i<array.length; i++) {
            if(Character.isDigit(array[i])) {
                digits[i] = digits[i-1] + 1;
                letters[i] = letters[i-1];
            } else {
                digits[i] = digits[i-1];
                letters[i] = letters[i-1] + 1;
            }
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;

        for(int i=0; i<letters.length; i++) {
            if(map.containsKey(Math.abs(letters[i] - digits[i]))) {
                int distance = i - map.get(Math.abs(letters[i] - digits[i]));
                if(distance > max) {
                    max = distance;
                }
            } else {
                map.put(Math.abs(letters[i] - digits[i]), i);
            }
        }

        System.out.println(max);
    }

    /**
     * 17.6
     * Count of 2s: Write a method to count the number of 2s that appear in all the numbers between
     * 0 and n (inclusive).
     * EXAMPLE
     * Input: 25
     * Output: 9 (2,12,20,21,22,23,24 and 25. Note that 22 counts for two 2s);
     */

    /**
     * 17.7
     * Baby Names: Each year, the government releases a list of the 10000 most common baby Names
     * and their frequencies (the number of babies with that name). The only problem with this is
     * that some names have multiple spellings. For example, "John" and "Jon" are esentially the same
     * name but would be listed separately in the list. Given two lists, one of names/frequencies
     * and the other of pairs of equivalent names, write an algorithm to print a new listof the
     * true frequency of each name. Note that if John and Jon are synonyms, and Jon and Johnny are
     * synonyms, then John and Johnny are synonyms. (It is both transitive and symmetric.) In then
     * final list, any name can be used as the "real" name.
     * EXAMPLE:
     * Input
     *      Names John(15), Jon(12), Chris(13), Kris(4), Christopher(19)
     *      Synonyms: (Jon, John), (John, Johnny), (Chris, Kris), (Chris, Christopher)
     * Output John(27), Kris(36)
     */


    public static void main(String[] args) {

    }
}
