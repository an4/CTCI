import java.util.Arrays;
import java.util.HashMap;
import java.util.StringBuilder;

public class Moderate {
    /**
     * 16.1
     * Number Swapper: Write a function to swap a number in place (that is, without temporary
     * variables).
     */
    public static void numberSwapper(int a, int b) {
        System.out.println("a: " + a + "; b: " + b);
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println("a: " + a + "; b: " + b);
    }

    /**
     * 16.2
     * Word Frequencies: Design a method to find the frequency of occurences of any given word in a
     * book. What if we were running this algorithm multiple times.
     */
    public static void wordFrequencies(String[] book, String[] word) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i=0; i<book.length; i++) {
            String key = book[i].toLowerCase();
            if(map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        for(int i=0; i<word.length; i++) {
            String key = word[i].toLowerCase();
            if(map.containsKey(key)) {
                System.out.println(word[i] + ": " + map.get(key));
            } else {
                System.out.println("The word is not in the book.");
            }
        }
    }

    /**
     * 16.3
     * Intersection: Given two straight line segments (represented as a start point and an end
     * point), compute the point of intersection, if any.
     */
    // public static void intersection(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
    //
    // }

    /**
     * 16.4
     * Tic Tac Toe: Design an algorithm to figure out if someone has won a game of tic-tac-toe.
     */
    public static boolean hasWinner(char a, char b, char c) {
        if(a == b && b == c && a != '.') {
            return true;
        }
        return false;
    }

    public static void ticTacToeWin(char[][] board) {
        // X - player 1
        // 0 - player 2
        // . - empty square

        // Check columns
        for(int i=0; i<3; i++) {
            if(hasWinner(board[0][i],board[1][i],board[2][i])) {
                System.out.println("Player " + board[0][i] + " won.");
                return;
            }
        }

        // Check rows
        for(int i=0; i<3; i++) {
            if(hasWinner(board[i][0],board[i][1],board[i][2])) {
                System.out.println("Player " + board[i][0] + " won.");
                return;
            }
        }

        // Check diagonals
        if(hasWinner(board[0][0],board[1][1],board[1][2])) {
            System.out.println("Player " + board[0][0] + " won.");
            return;
        }

        if(hasWinner(board[0][0],board[1][1],board[2][0])) {
            System.out.println("Player " + board[0][0] + " won.");
            return;
        }
    }

    /**
     * 16.5
     * Factorial Zeros: Write an algorithm to compute the number of trailing zeros in n factorial.
     */
    public static int factorialZeros(int num) {
        int count = 0;
        if(num < 0) {
            return -1;
        }
        for(int i=5; num/i > 0; i*=5) {
            count += num / i;
        }
        return count;
    }

    /**
     * 16.6
     * Smallest Difference: Given two arrays of integers, compute the pair of values (one value in
     * each array) with the smallest (non-negative) difference. Return the difference.
     * Input: {1,3,15,11,2}, {23,127,235,19,8}
     * Output: 3. That is, the pair {11,8}
     */
    // Brute Force
    public static int smallestDifference_BF(int[] a, int[] b) {
        int min = Math.abs(a[0] - b[0]);
        for(int i=0; i<a.length; i++) {
            for(int j=0; j<b.length; j++) {
                if(min > Math.abs(a[i] - b[j])) {
                    min = Math.abs(a[i] - b[j]);
                }
                if(min == 0) {
                    return min;
                }
            }
        }
        return min;
    }

    // Optimal Solution
    public static int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int min = Math.abs(a[0] - b[0]);
        int i=0;
        int j=0;
        while(i<a.length && j<b.length && min != 0) {
            int diff = Math.abs(a[i] - b[j]);
            if(diff < min) {
                min = diff;
            }
            if(a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        return min;
    }

    /**
     * 16.7
     * Number Max: Write a method that finds the maximum of two numbers. You should not use if-else
     * or any other comparison operators.
     */
    public static int flip(int bit) {
        return 1^bit;
    }

    public static int sign(int a) {
        return flip((a>>31) & 0x1);
    }

    public static int numberMax(int a, int b) {
        int c = a-b;

        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);

        int use_sign_of_a = sa^sb;

        int use_sign_of_c = flip(sa^sb);

        int k = use_sign_of_a * sa + use_sign_of_c * sc;
        int q = flip(k);

        return a*k+b*q;
    }

    /**
     * 16.8
     * English Int: Given any integer, print an English phrase that describes the integer (e.g.,
     * "One Thousand Two Hundred Thirty Four").
     */
    public static String getDigit(int n) {
        switch(n) {
            case 9:
                return "Nine";
            case 8:
                return "Eigth";
            case 7:
                return "Seven";
            case 6:
                return "Six";
            case 5:
                return "Five";
            case 4:
                return "Four";
            case 3:
                return "Three";
            case 2:
                return "Two";
            case 1:
                return "One";
            default:
                return "";
        }
    }

    public static String getTeens(int n) {
        switch(n) {
            case 19:
                return "Nineteen";
            case 18:
                return "Eigthteen";
            case 17:
                return "Seventeen";
            case 16:
                return "Sixteen";
            case 15:
                return "Fifteen";
            case 14:
                return "Fourteen";
            case 13:
                return "Thirteen";
            case 12:
                return "Twelve";
            case 11:
                return "Eleven";
            default:
                return "";
        }
    }

    public static String getTens(int n) {
        switch(n) {
            case 90:
                return "Ninety";
            case 80:
                return "Eigthty";
            case 70:
                return "Seventy";
            case 60:
                return "Sixty";
            case 50:
                return "Fifty";
            case 40:
                return "Fourty";
            case 30:
                return "Thirty";
            case 20:
                return "Twenty";
            case 10:
                return "Ten";
            default:
                return "";
        }
    }

    public static void englishInt(int n) {
        StringBuilder br = new StringBuilder();
    }


    public static void main(String[] main) {
        // numberSwapper(10,25);
        //
        // String[] book = {"book", "map", "horse", "Horse", "map", "pan", "block", "text", "word", "TexT"};
        // String[] queries = {"test", "TEXT"};
        // wordFrequencies(book, queries);
        // System.out.println(numberMax(Integer.MAX_VALUE, Integer.MIN_VALUE));

    }
}
