import java.util.ArrayList;

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
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
        for(Integer a : set) {
            ArrayList<ArrayList<Integer>> new_subsets = new ArrayList<ArrayList<Integer>>();
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

    public static void main(String[] args) {
        // System.out.println(tripleStep(4));
        int[] arr = {-1,0,2,6,7,8,9};
        System.out.println(magicIndex(arr));

        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(1);
        test.add(2);
        test.add(3);
        powerSet(test);
    }
}
