package com.java.Sorting;

import java.util.Arrays;

/**
 * Here we will be swapping the acjacent element . If it is already sorted we will not swap it .
 * So this way in every iteration we will keep the highest number in the ned , and will do it
 * till it is not sorted out.
 * Best Case Complexity : Omega(n) // where the list is already sorted.
 * Average case : O(n2)
 * Worst  Case  : O(n2)
 */
public class BubbleSort {

    private  static void swap(int[] unsortedList, int indexI, int indexJ) {

        int temp = unsortedList[indexI];
        unsortedList[indexI] = unsortedList[indexJ];
        unsortedList[indexJ] = temp;
    }

    private  static void bubbleSort(int[] unsortedList) {
        for (int i = unsortedList.length-1; i > 0; i--) {
            System.out.println("\ni= " + i);

            for (int j = 0; j < i; j++) {
                if (unsortedList[j] > unsortedList[j + 1]) {
                    swap(unsortedList, j, j + 1);
                    System.out.println("Swapping " + j + " " + "and " + (j + 1) + " ");
                    System.out.println(Arrays.toString(unsortedList));
                }

            }
        }
    }

    /**
     * So here we have used boolean variable which will check for the swapping operation,
     * if the swapping operation is not done , just break out from the loop.
     * So this operation will save us from doing unnescearry loop
     * @param unsortedList
     */
    public static void bubbleSortEarly(int[] unsortedList) {
        for (int i = unsortedList.length-1; i > 0; i--) {
            System.out.println("\ni= " + i);
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                if (unsortedList[j] > unsortedList[j + 1]) {
                    swap(unsortedList, j, j + 1);
                    swapped = true;
                    System.out.println("Swapping " + j + " " + "and " + (j + 1) + " ");
                    System.out.println(Arrays.toString(unsortedList));
                }

            } // end if inner loop

            if(!swapped){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int [] unsorted = new int[] { 21,22,34,55,23,98,99};
        System.out.println("Unsorted Array" + Arrays.toString(unsorted) );
        bubbleSort(unsorted);
        bubbleSortEarly(unsorted);
    }
}
