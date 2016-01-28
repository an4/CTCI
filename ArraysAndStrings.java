import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class ArraysAndStrings {
    /**
     * 1.1
     * Is Unique: Implement an algorithm to determine if a string has all unique characters.
     * What if you cannot use additional data structures?
     */
    private static boolean isUnique(String input) {
        HashSet<Character> set = new HashSet<Character>();

        for(int i=0; i<input.length(); i++) {
            if(set.contains(input.charAt(i))) {
                return false;
            }
            set.add(input.charAt(i));
        }
        return true;
    }

    // private static boolean isUnique2(String input) {
    //     for(int i=0; i<input.length()-1; i++) {
    //         for(int j=i+1; j<input.length(); j++) {
    //             if(input.charAt(i) == input.charAt(j)) {
    //                 return false;
    //             }
    //         }
    //     }
    //     return true;
    // }

    /**
     * 1.2
     * Check Permutation: Given two strings, write a method to decide if one is a permutation
     * of the other.
     */
    private static boolean checkPermutation(String a, String b) {
        if(a.length() != b.length()) {
            return false;
        }
        char[] arr_a = a.toCharArray();
        Arrays.sort(arr_a);
        String sorted_a = new String(arr_a);

        char[] arr_b = b.toCharArray();
        Arrays.sort(arr_b);
        String sorted_b = new String(arr_b);

        return sorted_a.equals(sorted_b);
    }

    /**
     * 1.3
     * URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the
     * string has sufficient space at the end to hold the additional characters, and that you are
     * given the "true" length of the string. (Node: If implementing in Java, please use a
     * character array so that you can perform the operation in place.)
     *
     * EXAMPLE
     * Input: "Mr John Smith    ", 13
     * Output: "Mr%20John%20Smith"
     */
    public static String URLify(String input, int length) {
        char[] array = input.toCharArray();
        int l = input.length();
        l--;
        for(int i=length-1; i>=0; i--) {
            if(array[i] != ' ') {
                array[l--] = array[i];
            } else {
                array[l--] = '0';
                array[l--] = '2';
                array[l--] = '%';
            }
        }
        return new String(array);
     }

     /**
      * 1.4
      * Palindrome Permutation: Given a string, write a function to check if it is a permutation
      * of a palindrome. A palindrome is a word or a phrase is the same forwards and backwards.
      * A permutation is a rearrangement of letters. The palindrome does not need to be limited
      * to just dictionary words.
      * EXAMPLE
      * Input: Tact Coa
      * Output: True (permutations: "taco cat", "atco cta", etc.)
      */
     public static boolean palindromePermutation(String input) {
         HashSet<Character> set = new HashSet<Character>();
         boolean all_spaces = true;
         input = input.toLowerCase();
         for(int i=0; i<input.length(); i++) {
             if(!Character.isLetter(input.charAt(i))) {
                 continue;
             }
             all_spaces = false;
             if(set.contains(input.charAt(i))) {
                 set.remove(input.charAt(i));
             } else {
                 set.add(input.charAt(i));
             }
         }
         if(set.size() < 2 && !all_spaces) {
             return true;
         }
         return false;
     }

     /**
      * 1.5
      * One Away: There are three types of edits that can be perofrmed on strings:
      * insert a character, remove a character, or replace a character. Given two strings,
      * write a function to check if they are one edit (or zero edits) away.
      */
     public static boolean oneAway(String a, String b) {
         // Replace
         if(a.length() == b.length()) {
             int diff = 0;
             for(int i=0; i<a.length(); i++) {
                 if(a.charAt(i) != b.charAt(i)) {
                     diff++;
                 }
                 if(diff > 1) {
                     return false;
                 }
             }
             return true;
         }
         // Insert
         if(a.length() + 1 == b.length()) {
             int index_a = 0;
             int index_b = 0;
             while(index_a < a.length() && index_b < b.length()) {
                 if(a.charAt(index_a) == b.charAt(index_b)) {
                     index_a++;
                     index_b++;
                 } else {
                     if(index_a != index_b) {
                         return false;
                     }
                     index_a++;
                 }
             }
             return true;
         }
         // Remove
         if(a.length()-1 == b.length()) {
             int index_a = 0;
             int index_b = 0;
             while(index_a < a.length() && index_b < b.length()) {
                 if(a.charAt(index_a) == b.charAt(index_b)) {
                     index_a++;
                     index_b++;
                 } else {
                     if(index_a != index_b) {
                         return false;
                     }
                     index_b++;
                 }
             }
             return true;
         }
         return false;
     }

     /**
      * 1.6
      * String Compression: Implement a method to perform basic string compression using
      * the counts of repeated charactes. For example, the string aabcccccaaa would become
      * a2b1c5a3. If the "compressed" string would not become smaller than the original
      * string, your method should return the original string. You can assume the string
      * has only uppercase and lowercase letters (a-z).
      */
     public static String stringCompression(String input) {
         return input;
     }

    /**
     * 1.7
     * Rotate Matrix: Given an image represented by an NxN matrix, where each ixel in the image is
     * 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
     */


    /**
     * 1.8
     * Zero Matrix: Write an algorithm such that if an element iin an MxN matrix is 0, its entire
     * row and clolumn are set to 0.
     */


    /**
     * 1.9
     * String Rotation: Assume you have a methos isSubstring which checks if one word is a substring
     * of another. Given two strings, s1 and s2, write code to check if s2 is a rotation og s1 using
     * only one call to isSubstring *e.g., "waterbottle" is a rotation of "erbottlewat").
     */



    public static void main(String[] args) {
        System.out.println(oneAway("a", "ab"));
        System.out.println(oneAway("a", "abc"));
        System.out.println(oneAway("a", "b"));
        System.out.println(oneAway("aa", "bb"));
    }
}
