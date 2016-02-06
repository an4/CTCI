import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

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
    public static int sparseSearch(String[] arr) {
        
    }


    public static void main(String[] args) {
        // test_sortedMerge();
        // String[] arr = {"abc","aaaa","cba", "zyx", "ana", "bac", "aan", "naa"};
        // groupAnagrams(arr);

        // int[] arr = {7,8,9,0,1,2,3,4,5,6};
        // System.out.println(searchInRotatedArray(arr, 4));

        // int[] arr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        // System.out.println(sortedSearchNoSize(arr, 5));
        // System.out.println(sortedSearchNoSize(arr, 19));
    }
}
