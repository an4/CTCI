public class Sort {
    /** Bubble Sort */
    private static void bubbleSort(int[] a) {
        System.out.println("Bubble Sort:");
        int n = a.length;
        for(int i=0; i<n-1; i++) {
            printArray(a);
            for(int j=i+1; j<n; j++) {
                if(a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        printArray(a);
    }

    private static void printArray(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * Selection sort
     * Given a list, take the current element and exchange it with the smallest element on the right
     * hand side of the current element.
     */
    private static void selectionSort(int[] arr) {
        System.out.println("Selection Sort:");
        int n = arr.length;
        printArray(arr);
        for(int i=0; i<n-1; i++) {
            int min = i;
            for(int j=i+1; j<n; j++) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
            printArray(arr);
        }
    }

    /**
     * Insertion sort
     * Given a list, take the current element and insert it at the appropriate position of the list,
     * adjusting the list every time you insert. It is similar to arranging the cards in a Card game.
     */
    private static void insertionSort(int[] arr) {
        System.out.println("Insertion Sort:");
        printArray(arr);
        int n = arr.length;
        for(int i=1; i<n; i++) {
            int temp = arr[i];
            int j = 0;
            for(j=i-1; j>=0 && temp < arr[j]; j--) {
                arr[j+1] = arr[j];
            }
            arr[j+1] = temp;
            printArray(arr);
        }
    }

    /** Quick sort */
    private static void quickSort(int[] arr) {
        
    }

    public static void main(String[] args) {
        int[] array = {9,8,7,6,5,4,3,2,1,0};

        // bubbleSort(array);
        // selectionSort(array);
        insertionSort(array);
    }
}
