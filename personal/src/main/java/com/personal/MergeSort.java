package com.personal;

public class MergeSort {
    //total complexity of MergeSort algorithm is O(n*log(N))
    public static void sortArray(int[] array, int first, int last) {
        if (first < last) {
            int middle = (first + last) / 2;
            //complexity of sortArray recursive call - O(log(n))
            sortArray(array, first, middle);
            sortArray(array, middle + 1, last);
            //complexity of mergeArrays call - O(n)
            mergeArrays(array, first, middle, last);
        }
    }

    private static void mergeArrays(int[] array, int first, int middle, int last) {
        //this algorithm uses extra space - we create additional arrays
        int[] firstArray = new int[middle - first + 1];
        int[] secondArray = new int[last - middle];

        System.arraycopy(array, first, firstArray, 0, firstArray.length);
        System.arraycopy(array, middle + 1, secondArray, 0, secondArray.length);

        int i = 0;
        int j = 0;
        int k = first;

        while (i < firstArray.length && j < secondArray.length) {
            if (firstArray[i] <= secondArray[j]) {
                array[k] = firstArray[i];
                i++;
            } else {
                array[k] = secondArray[j];
                j++;
            }
            k++;
        }

        while (i < firstArray.length) {
            array[k] = firstArray[i];
            k++;
            i++;
        }

        while (j < secondArray.length) {
            array[k] = secondArray[j];
            k++;
            j++;
        }
    }
}
