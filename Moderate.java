import java.lang.StringBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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

    /**
     * 16.9
     * Operations: Write methods to implement the multiply, subtract, and divide operations for
     * integers. The results of all of these are integeers. Use only he add operator.
     */
    // Subtraction
    public static int negate(int a) {
        int neg = 0;
        int newSign = a < 0 ? 1 : -1;
        while(a != 0) {
            neg += newSign;
            a += newSign;
        }
        return neg;
    }

    public static int negate_faster(int a) {
        int neg = 0;
        int newSign = a < 0 ? 1 : -1;
        int delta = newSign;
        while(a != 0) {
            boolean differentSigns = (a + delta > 0) != (a > 0);
            if(a+delta != 0 && differentSigns) {
                delta = newSign;
            }
            neg += delta;
            a += delta;
            delta += delta;
        }
        return neg;
    }

    public static int minus(int a, int b) {
        return a + negate(b);
    }

    // Multiplication
    public static int multiply(int a, int b) {
        if(a < b) {
            return multiply(b, a);
        }
        int sum = 0;
        for(int i=abs(b); i>0; i = minus(i, 1)) {
            sum += a;
        }
        if(b<0) {
            sum = negate(sum);
        }
        return sum;
    }

    public static int abs(int a) {
        if(a<0) {
            return negate(a);
        }
        return a;
    }

    // Division
    public static int divide(int a, int b) throws java.lang.ArithmeticException {
        if(b == 0) {
            throw new java.lang.ArithmeticException("ERROR");
        }
        int absa = abs(a);
        int absb = abs(b);

        int product = 0;
        int x = 0;
        while(product + absb <= absa) {
            product += absb;
            x++;
        }

        if((a < 0 && b < 0) || (a > 0 && b > 0)) {
            return x;
        }
        return negate(x);
    }

    /**
     * 16.10
     * Living People: Given a list of people with their birth and death years, implement a method to
     * compute the year with the most number of people alive. You may assume that all people were
     * born between 1900 and 2000 (inclusive). If a person was alive during any portion of the year,
     * they should be included in that year's count. For example, Person (birth = 1908, death = 1909)
     * is included in the counts for both 1908 and 1909.
     */
    class Person {
        private Integer birthYear;
        private Integer deathYear;

        public Person(int b, int d) {
            this.birthYear = b;
            this.deathYear = d;
        }

        public int getBirthYear() {
            return this.birthYear;
        }

        public int getDeathyear() {
            return this.deathYear;
        }
    }
    public static int livingPeople(Person[] people) {
        int start_year = 1900;
        int end_year = 2000;

        int[] years = new int[end_year - start_year + 2];

        for(Person person : people) {
            years[person.getBirthYear() - start_year]++;
            years[person.getDeathyear() - start_year + 1]++;
        }

        int max = years[0];
        for(int i=1; i<years.length-1; i++) {
            if(max < years[i]) {
                max = years[i];
            }
        }
        return max;
    }

    /**
     * 16.11
     * Diving Board: You are building a diving board by placing a bunch of planks of wood end-to-end.
     * There are two types of planks, one of length shorter and one of length longer. You must use
     * exactly k planks of wood. Write a method to generate all possible lengths of the diving board.
     */
    public static int divingBoard(int k, int shorter, int longer) {
        if(shorter == longer) {
            return 1;
        }
        HashSet<Integer> lengths = new HashSet<Integer>();
        int shortest = k * shorter;
        int longest = k * longer;
        lengths.add(shortest);
        lengths.add(longest);
        int current = shortest;
        for(int i=1; i<k-1; i++) {
            current -= shorter;
            current += longer;
            lengths.add(current);
        }
        return lengths.size();
    }

    /**
     * 16.12
     * XML Encoding: Since XML is very verbose, you are given a way of encoding it where each tag
     * gets mapped to a pre-defined integer value. The language/grammar is as follows:
     * Element --> Tag Attributes END Children END
     * Attribute --> Tag Value
     * END --> 0
     * Tag --> some predefined mapping to int
     * Value --> string value END
     * For example, the following XML might be converted into the compressed string below (assuming
     * a mapping of family->1, person->2, firstName->3, lastName->4, state->5).
     * <family lastName="McDowell" state="CA">
     *    <person firstName="Gayle">Some Message</person>
     * </family>
     * 1 4 McDowell 5 CA 0 2 3 Gayle 0 Some Message 0 0.
     * Write code to print the encoded version of an XML element (passed in Element and Attribute
     * objects).
     */
    // public static String XMLEncoding() {
    //
    // }

    /**
     * 16.13
     * Bisect Squares: Given two squres on a two-dimensional plane, find a line that would cut these
     * two squares in half. Assume that the top and the bottom sides of the square run parallel to
     * the x-axis.
     */

    /**
     * 16.14
     * Best Line: Given a two-dimensional graph with points on it, find a line which passes the most
     * number of points.
     */

    /**
     * 16.15
     * Master Mind: The game of Master Mind is played as follows:
     * The computer has four slots, each slot will contain a ball that is red(R), yellow(Y),
     * green(G) or blue(B). For example, the computer might hame RGGB (Slot #1 is red, Slots #2 and
     * #3 are green, Slot #4 is blue).
     * You, the user, are trying to guess the solution. You might, fo example, guess YRGB.
     * When you guess the correct color for the correct slot, you get a "hit". If you guess a color
     * that exists but is in the wrong slot, you get a "pseudo-hit". Note that a slot that is a hit
     * can never count as a pseudo-hit.
     * For example, if the actual solution is RGBY and you guess GGRR, you have one hit and one
     * pseudo-hit. Write a method that, given a guess and a solution, return the number of hits
     * and pseudo-hits.
     */
    public static void masterMind(String guess, String solution) {
        
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
