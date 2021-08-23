package com.personal;

public class InsertionSort {
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int k = i;
            for (int j = k - 1; j >= 0 && array[j] > array[k]; j--, k--) {
                int temp = array[j];
                array[j] = array[k];
                array[k] = temp;
            }
        }
    }
}

