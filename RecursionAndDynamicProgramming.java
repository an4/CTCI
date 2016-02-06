import java.util.ArrayList;
import java.util.HashSet;

public class RecursionAndDynamicProgramming {
    /**
     * 8.1
     * Triple Step: A chils is running up a staircase with n steps and can hop either 1 step, 2
     * steps or 3 steps at a time. Implement a method to count how many possible ways the child can
     * run up the stairs.
     */
    public static int tripleStep(int n) {
        int[] steps = {1,2,3};
        int[] count = new int[n+1];

        for(int i=1; i<=n; i++) {
            for(int j=0; j<steps.length; j++) {
                if(i-j == 0) {
                    count[i]++;
                }
                if(i-j > 0) {
                    count[i] += count[i-j];
                }
            }
        }
        return count[n];
    }

    /**
     * 8.2
     * Robot in a Grid: Imagine a robot sitting on the upper left corner of the grid with r rows
     * and c columns. The robot can only move in two directions, right and down, but certain cells
     * are "off limits" such that the robot cannot step on them. Design an algorithm to find a path
     * for the robot from the top left to the bottom right.
     */
    public static void robotInAGrid(int[][] grid) {

    }

    /**
     * 8.3
     * Magic Index: A magig index in an array A[0 .. n-1]  is defined to be an index such that
     * A[i] = i. Given a sorted array of distinct integers, write a method to find the magic index,
     * if one exists, in array A.
     * FOLLOW UP
     * What if the values are not distinct?
     */
    public static int magicIndexDistinct(int[] array) {
        int start = 0;
        int end = array.length-1;
        while(start<=end) {
            int middle = (start+end)/2;
            if(array[middle] == middle) {
                return middle;
            }
            if(array[middle] > middle) {
                end = middle-1;
            } else {
                start = middle+1;
            }
        }
        return -1;
    }

    // FOLLOW UP
    public static int magicIndex(int[] array) {
        return magicIndex(array, 0 , array.length-1);
    }

    public static int magicIndex(int[] array, int start, int end) {
        if(start > end) {
            return -1;
        }
        int middle = (start+end)/2;
        if(middle == array[middle]) {
            return middle;
        }
        // Search left
        int leftIndex = Math.min(middle -1, array[middle]);
        int left = magicIndex(array, start, leftIndex);
        if(left >= 0) {
            return left;
        }
        // Search right
        int rightIndex = Math.max(middle+1, array[middle]);
        int right = magicIndex(array, rightIndex, end);
        if(right >= 0) {
            return right;
        }
        return -1;
    }

    /**
     * 8.4
     * Power Set: Write a method to return all subsets of a set.
     * 2^n subsets
     */
    public static void powerSet(ArrayList<Integer> set) {
        HashSet<ArrayList<Integer>> output = new HashSet<ArrayList<Integer>>();
        output.add(new ArrayList<Integer>());
        for(Integer a : set) {
            HashSet<ArrayList<Integer>> new_subsets = new HashSet<ArrayList<Integer>>();
            ArrayList<Integer> el_set = new ArrayList<Integer>(a);
            new_subsets.add(el_set);
            for(ArrayList<Integer> subset: output) {
                ArrayList<Integer> new_subset = new ArrayList<Integer>(subset);
                new_subset.add(a);
                new_subsets.add(new_subset);
            }
            if(new_subsets.size() > 0) {
                output.addAll(new_subsets);
            }
        }
        for(ArrayList<Integer> subset: output) {
            System.out.print("{");
            for(Integer el: subset) {
                System.out.print(el + ",");
            }
            System.out.println("}");
        }
    }

    /**
     * 8.5
     * Recursive Multiply: Write a recursive function to multiply two numbers without using the *
     * operator. You can use addition, substraction, bit shifting, but you should minimize the
     * number of thse operations.
     */
    public static int minProduct(int min, int max) {
        if(min == 0) {
            return 0;
        }
        if(min == 1) {
            return max;
        }
        int s = min >> 1;
        int half = minProduct(s, max);

        if(min % 2 == 0) {
            return half + half;
        }
        return half + half + max;
    }

    public static int recursiveMultiply(int a, int b) {
        int min = a <= b ? a : b;
        int max = a > b ? a : b;
        return minProduct(min, max);
    }

    /**
     * 8.6
     * Towers of Hanoi: In the classic problem of the Towers of Hanoi, you have 3 towers and N
     * disks of different sizes which can slide onto any tower. The puzzle starts with disks sorted
     * in ascending order of size from top to bottom. (i.e., each disk sits on top of an even
     * larger one). You have the following constraints:
     * (1) Only one disk can be moved at a time.
     * (2) A disk is slid off the top of one tower onto the next tower.
     * (3) A disk can only be places on top of a larger disk.
     * Write a progrm to move the disks from the first tower to the last using stacks.
     */

    /**
     * 8.7
     * Permutations without Dups: Write a method to compute all permutations of a string of unique
     * characters.
     */
    public static void permutationsWithoutDups(String str) {
        ArrayList<String> perm = new ArrayList<String>();
        perm.add(str.substring(0,1));
        for(int i=1; i<str.length(); i++) {
            ArrayList<String> output = new ArrayList<String>();
            for(String p: perm) {
                for(int j=0; j<=p.length(); j++) {
                    output.add(p.substring(0,j) + str.substring(i,i+1) + p.substring(j));
                }
            }
            perm = output;
        }

        for(String s: perm) {
            System.out.println(s);
        }
    }

    /**
     * 8.8
     * Permutations with Dups: Write a method to compute all permutations of a string whose
     * characters are not necessarily unuque. The list of permutations should not have duplicates.
     */
    public static void permutationsWithDups(String str) {
        HashSet<String> perm = new HashSet<String>();
        perm.add(str.substring(0,1));
        for(int i=1; i<str.length(); i++) {
            HashSet<String> output = new HashSet<String>();
            for(String p: perm) {
                for(int j=0; j<=p.length(); j++) {
                    output.add(p.substring(0,j) + str.substring(i,i+1) + p.substring(j));
                }
            }
            perm = output;
        }

        for(String s: perm) {
            System.out.println(s);
        }
    }

    /**
     * 8.9
     * Parens: Implement an algorithm to print all valid (e.g, properly opened and closed)
     * combinations of n pairs of parantheses.
     * EXAMPLE
     * Input: 3
     * Output: ((())), (()()), (())(), ()(()), ()()()
     */
    public static void parens_helper(String s, int open, int closed) {
        if(open == 0 && closed == 0) {
            System.out.println(s);
            return;
        }
        if(open > 0) {
            parens_helper(s+"(", open-1, closed);
        }
        if(open<closed) {
            parens_helper(s+")", open, closed-1);
        }
    }

    public static void parens(int n) {
        parens_helper("", n, n);
    }

    /**
     * 8.10
     * Paint Fill: Implement the "paint fill" function that one might see on many image editing
     * programs. That is, given a screen (represented by a two-dimensional array of colors), a
     * point, and a new color, fill in the surrounding area until the color chnages from the
     * original color.
     */
    public static void paintFill(int[][] screen, int x, int y, int color) {
        
    }

    public static void main(String[] args) {
        // System.out.println(tripleStep(4));
        int[] arr = {-1,0,2,6,7,8,9};
        System.out.println(magicIndex(arr));

        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(1);
        test.add(2);
        test.add(3);
        powerSet(test);

        // permutationsWithDups("aabc");

        parens(3);


    }
}
