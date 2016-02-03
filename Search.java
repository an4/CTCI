public class Search {
    /** Binary Search */
    private static int binarySearch(int[] arr, int value) {
        int start = 0;
        int end = arr.length-1;
        int middle = (start+end)/2;
        while(start<end) {
            if(value == arr[middle]) {
                return middle;
            } else if (value < arr[middle]) {
                end = middle-1;
            } else {
                start = middle+1;
            }
            middle = (start+end)/2;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8,9};

        System.out.println(binarySearch(arr, 1));
        System.out.println(binarySearch(arr, 11));
    }
}
