import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

class BitSet {
    int[] bitset;

    public BitSet(int size) {
        bitset = new int[(size>>5) + 1]; // divide by 32
    }

    boolean get(int pos) {
        int wordNumber = (pos>>5); // divide by 32
        int bitNumber = (pos& 0x1F); // mod 32
        return (bitset[wordNumber] & (1 << bitNumber)) != 0;
    }

    void set(int pos) {
        int wordNumber = (pos>>5); // divide by 32
        int bitNumber = (pos & 0x1F); // mod 32
        bitset[wordNumber] |= 1 << bitNumber;
    }
}

public class SortingAndSearching{
    /**
     * 10.1
     * Sorted Merge: You are given two sorted arrays, A and B, where A has a large enough buffer at
     * the end to hold B. Write a method to merge B into A in sorted order.
     */
    public static void sortedMerge(int[] A, int[] B, int len_A) {
        int len_B = B.length-1;
        int i=0;
        for(i=A.length-1; i>=0 && len_A >= 0 && len_B >= 0; i--) {
            if(A[len_A] > B[len_B]) {
                A[i] = A[len_A];
                len_A--;
            } else {
                A[i] = B[len_B];
                len_B--;
            }
        }
        while(len_A >= 0) {
            A[i] = A[len_A];
            i--;
            len_A--;
        }
        while(len_B >= 0) {
            A[i] = B[len_B];
            i--;
            len_B--;
        }
    }

    public static void test_sortedMerge() {
        int[] A = {0,2,4,6,8,0,0,0,0,0};
        int[] B = {1,3,5,7,9};
        sortedMerge(A, B, 4);
        for(int i=0; i<A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    /**
     * 10.2
     * Group Anagrams: Write a method to sort an array of strings so that all the anagrams are next
     * to each other.
     */
    public static void groupAnagrams(String[] arr) {
        HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
        for(int i=0; i<arr.length; i++) {
            char[] chars = arr[i].toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            if(map.containsKey(sorted)) {
                LinkedList<String> list = map.get(sorted);
                list.add(arr[i]);
            } else {
                LinkedList<String> list = new LinkedList<String>();
                list.add(arr[i]);
                map.put(sorted, list);
            }
        }

        int k=0;
        for(String key : map.keySet()) {
            for(String str: map.get(key)) {
                arr[k] = str;
                k++;
            }
        }

        for(int i=0; i<arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 10.3
     * Search in Rotated Array: Given a sorted array of n integers, that had been rotated an unknown
     * number of times, write code to find an element in the array. You may assume that the array
     * was originally sorted in increasing order.
     * EXAMPLE
     * Input find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
     * Output: 8 (the index of 5 in the array)
     */
    public static int searchInRotatedArray(int[] array, int value) {
        int start = 0;
        int end = array.length-1;
        while(start<=end) {
            int middle = (start+end)/2;
            if(array[middle] == value) {
                return middle;
            }
            if(array[middle] < value && array[end] >= value) {
                start = middle + 1;
            } else if(array[middle] > value && array[start] <= value) {
                end = middle - 1;
            } else if(array[middle] < value && array[end] <= value) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

    // Make it work for duplicates

    /**
     * 10.4
     * Sorted Search, No Size: You are given an array-like data structure Listy which lacks a size
     * method. It does, however, have an elementAt(i) method that returns the element at index i
     * in O(1) time. If i is beyond the bounds of the data structure, it returns -1. (For this
     * reason the data structure only supports positive integers). Given a Listy which contains
     * sorted, positive integers, find the index at which element x occurs. If x occurs multiple
     * times, you may return any index.
     */
    public static int elementAt(int i, int[] listy) {
        if(i >= listy.length) {
            return -1;
        }
        return listy[i];
    }

    public static int sortedSearchNoSize(int[] listy, int x) {
        int i = 1;
        int val = elementAt(i, listy);
        if(val == x) {
            return i;
        }
        while(val >= x ||val != -1) {
            i *= 2;
            val = elementAt(i, listy);
            if(val == x) {
                return i;
            }
        }
        int start = val / 2;
        while(val == -1) {
            i--;
            val = elementAt(i, listy);
            if(val == x) {
                return i;
            }
        }
        // Binary search between start and i;
        while(start<=i) {
            int middle = (start+i)/2;
            if(elementAt(middle, listy) == x) {
                return middle;
            }
            if(elementAt(middle, listy) < x) {
                start = middle+1;
            } else {
                i = middle-1;
            }
        }
        return -1;
    }

    /**
     * 10.5
     * Sparse Search: Given a sorted array of strings that is interspersed with empty strings  write
     * a method to find the location of a given string.
     * EXAMPLE
     * Input: ball, {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
     * Output: 4
     */
    public static int sparseSearch(String[] arr, String item) {
        int start = 0;
        int end = arr.length-1;
        while(start<=end) {
            int middle = (start+end)/2;
            if(arr[middle].equals("")) {
                int left = middle-1;
                int right = middle+1;
                while(arr[left].equals("") && arr[right].equals("") && left>=0 && right<arr.length) {
                    left--;
                    right++;
                }
                if(arr[left].equals("")) {
                    middle = right;
                } else {
                    middle = left;
                }
            }
            if(arr[middle].equals(item)) {
                return middle;
            }
            if(arr[middle].compareTo(item) < 0) {
                start = middle+1;
            } else {
                end = middle-1;
            }
        }
        return -1;
    }

    /**
     * 10.6
     * Sort Big File: Imagine you have a 20 GB file with one string per line. Explain how you
     * would sort the file.
     */
    // External Sort

    /**
     * 10.7
     * Missing Int: Given an input file with four billion non-negative integers, provide an
     * algorithm to generate an integer that is not contained in the file. Assume you have 1 GB
     * of memory available for the task.
     * FOLLOW UP
     * What if you have only 10 MB of memmory? Assume that all the values are distinct and we now
     * have no more than one billion non-negative integers.
     */
    // public static int missingInt(String file) {
    //     // 2^31 non-negative integers
    // }

    /**
     * 10.8
     * Find Dulicates: You have an array with all the numbers from 1 to N, where N is at most
     * 32,000. The array may have duplicate entries and you do not know what N is. With only 4
     * kylobytes of memory available, how would you print all duplicate elements in the array.
     */
    public static void findDuplicates(int[] array) {
        BitSet bs = new BitSet(32000);

        for(int i=0; i<array.length; i++) {
            int num = array[i];
            int num0 = num - 1; // bitset starts at 0, numbers start at 1
            if(bs.get(num0)) {
                System.out.println(num);
            } else {
                bs.set(num0);
            }
        }
    }

    /**
     * 10.9
     * Sorted Matrix Search: Given MxN matrix in which each row and each column is sorted in
     * ascending order, write a method to find an element.
     */
    // public static void sortedMatrixSearch(int[][] matrix, int el) {
    //
    // }

    /**
     * 10.10
     * Rank from Stream: Imagine you are reading in a stream of integers. Periodically, you wish
     * to be able to look up the rank of a number x (the number of values less than or equal to x).
     * Implement the data structure and algorithms to support these operations. That is,
     * implement the method track (int x), which is called when each number is generated, and the
     * method getRankOfNumber (int x), which returns the number of values less than or equal to x
     * (not including x itself).
     * EXAMPLE
     * Stream (in order of appearance) 5, 1, 4, 4, 5, 9, 7, 13, 3
     * getRankOfNumber(1) = 0;
     * getRankOfNumber(3) = 1;
     * getRankOfNumber(4) = 3;
     */
    // public static void track(int x) {
    //
    // }
    //
    // public static int getRankOfNumber(int x) {
    //     return 0;
    // }
    //
    // public static void rankFromStrem() {
    //
    // }

    /**
     * 10.11
     * PeaksAndValleys: In an array of integers, a "peak" is an element which is greater than or
     * or equal to the adjacent integers and a "valley" is an element which is less than or equal
     * to the adjacent integers. For example, in the array {5,8,2,3,4,6}, {8,6} are peaks and
     * {5,2} are valleys. Given an array of integers, sort the array into an alternating
     * sequence of peaks and valleys.
     * EXAMPLE
     * Input: {5,3,1,2,3}
     * Output: {5,1,3,2,3}
     */
    



    public static void main(String[] args) {
        // test_sortedMerge();
        // String[] arr = {"abc","aaaa","cba", "zyx", "ana", "bac", "aan", "naa"};
        // groupAnagrams(arr);

        // int[] arr = {7,8,9,0,1,2,3,4,5,6};
        // System.out.println(searchInRotatedArray(arr, 4));

        // int[] arr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        // System.out.println(sortedSearchNoSize(arr, 5));
        // System.out.println(sortedSearchNoSize(arr, 19));

        // String[] arr =  {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        // System.out.println(sparseSearch(arr, "ball"));
    }
}
