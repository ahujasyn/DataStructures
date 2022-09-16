package com.java.sortingtest;

import java.util.Arrays;

public class SelectionSort {

    /**
     *It is an algorithm which will compare the data with every element , and wherever we find less element it will swap the number.
     And start iterating In the same manner  for the rest of the data.

     1. Time Complexity : O(N2) - as we have to iterate over the complete list
     a. Space Complexity : O(N-1) as its get swapped right at the moment.
     2. Pros :
     a. It’s a simple algorithm to sort out the list
     3. Cons :
     a. Even the list is sorted we still have to go with iteration to check .
     It makes O(n2) swaps and O(n2) comparisons.
     */

        public static void  swap(int [] list , int iIndex, int jIndex ){

            int temp = list[iIndex];
            list[iIndex] = list[jIndex];
            list[jIndex] = temp;

        }

        public static void selectionSort(int [] unsortedList){

            for(int i=0;i<unsortedList.length;i++){
                System.out.println("\ni =" + i);
                for(int j=i+1;j<unsortedList.length;j++){
                    if(unsortedList[i]>unsortedList[j]){
                        swap(unsortedList,i,j);  // passing index with list to method.
                        System.out.print("Swapping "+i +" and "+ j + " ");
                        System.out.println(Arrays.toString(unsortedList));
                    }
                }// end of the internal  loop

            }

        }

        /**
         * So here we will stop the loop , if the list got sorted out.
         * @param unsortedList
         */
        public static void selectionSortEarly(int [] unsortedList){

            for(int i=0;i<unsortedList.length;i++){
                boolean swapped = false;
                System.out.println("\ni =" + i);
                for(int j=i+1;j<unsortedList.length;j++){
                    if(unsortedList[i]>unsortedList[j]){
                        swap(unsortedList,i,j);  // passing index with list to method.
                        swapped= true;
                        System.out.print("Swapping "+i +" and "+ j + " ");
                        System.out.println(Arrays.toString(unsortedList));
                    }
                } // end of the internal  loop
                if(!swapped){
                    break;
                }
            }

        }

        public static void main(String[] args) {
            int [] arr = new int[]{31,12,11,9,8,7,6,80,90};
            System.out.println("Unsorted List:"+Arrays.toString(arr));
            selectionSort(arr);
            selectionSortEarly(arr); // to early break the loop
        }

}
