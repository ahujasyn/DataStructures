package com.java.Sorting;

import com.sun.xml.internal.bind.v2.runtime.SwaRefAdapter;

import java.util.Arrays;

/** Insertion SOrt : So here we will compare elements if those are sorted
 * than we will leave, if it is not sorted than swap .
 * For the third element we will do the same and compare with the previous two ,
 * this way list will grow to compare and finally we will get the sorted list .
    Time Complexity : Its O(N2) as we are comparing each element and the best case it will
     be O(N)
    Space Complexity : Its O(1) as we are using no extra space
 */
public class InsertionSort {

private static  void swap(int [] unsortedList, int indexi,int indexj ){
    int temp = unsortedList[indexi]; //this got empty
    unsortedList[indexi] = unsortedList[indexj];
    unsortedList[indexj] = temp;

}

private  static  void insertionSort(int [] unsorted){

    for(int i=0;i<unsorted.length-1;i++){ // so here it will be length -1 . Keep note.
        System.out.println("\n i"+i);

        for(int j=i+1;j>0;j--){
            if(unsorted[j]<unsorted[j-1]) { // so here comparing next and previous element for exmple 10,5 : so here 5 will be compared with 10
                // and so on as j will increase it has to match with previous ones
                swap(unsorted, j,j-1 );
                System.out.println("Swapping "+ j +" and "+ (j-1) +" " );
                System.out.println(Arrays.toString(unsorted));
            }

        }
    }

}

    public static void main(String[] args) {
        int [] arr = new int[]{12,43,6,3,6,7,1,5,7,8,2,7};
        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
    }

}
/**
 * Arrays Passed :  [12, 43, 6, 3, 6, 7, 1, 5, 7, 8, 2, 7]
 *
 *  i0
 *
 *  i1
 * Swapping 2 and 1
 * [12, 6, 43, 3, 6, 7, 1, 5, 7, 8, 2, 7]
 * Swapping 1 and 0
 * [6, 12, 43, 3, 6, 7, 1, 5, 7, 8, 2, 7]
 *
 *  i2
 * Swapping 3 and 2
 * [6, 12, 3, 43, 6, 7, 1, 5, 7, 8, 2, 7]
 * Swapping 2 and 1
 * [6, 3, 12, 43, 6, 7, 1, 5, 7, 8, 2, 7]
 * Swapping 1 and 0
 * [3, 6, 12, 43, 6, 7, 1, 5, 7, 8, 2, 7]
 *
 *  i3
 * Swapping 4 and 3
 * [3, 6, 12, 6, 43, 7, 1, 5, 7, 8, 2, 7]
 * Swapping 3 and 2
 * [3, 6, 6, 12, 43, 7, 1, 5, 7, 8, 2, 7]
 *
 *  i4
 * Swapping 5 and 4
 * [3, 6, 6, 12, 7, 43, 1, 5, 7, 8, 2, 7]
 * Swapping 4 and 3
 * [3, 6, 6, 7, 12, 43, 1, 5, 7, 8, 2, 7]
 *
 *  i5
 * Swapping 6 and 5
 * [3, 6, 6, 7, 12, 1, 43, 5, 7, 8, 2, 7]
 * Swapping 5 and 4
 * [3, 6, 6, 7, 1, 12, 43, 5, 7, 8, 2, 7]
 * Swapping 4 and 3
 * [3, 6, 6, 1, 7, 12, 43, 5, 7, 8, 2, 7]
 * Swapping 3 and 2
 * [3, 6, 1, 6, 7, 12, 43, 5, 7, 8, 2, 7]
 * Swapping 2 and 1
 * [3, 1, 6, 6, 7, 12, 43, 5, 7, 8, 2, 7]
 * Swapping 1 and 0
 * [1, 3, 6, 6, 7, 12, 43, 5, 7, 8, 2, 7]
 *  and so on.....
 */
