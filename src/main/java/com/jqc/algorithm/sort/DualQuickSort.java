package com.jqc.algorithm.sort;

import com.jqc.algorithm.sort.util.SortBase;

public class DualQuickSort extends SortBase {
    @Override
    public void sort(int[] arr) {// 看视频后写出
        //printArr(arr ,"start");
        quickSort(arr, 0, arr.length - 1);
        //printArr(arr ,"end");
    }

    private void quickSort(int[] arr, int leftBound, int rightBound) {
        if (leftBound >= rightBound)
            return;

        partition(arr, leftBound, rightBound);

    }

    private void partition(int[] arr, int leftBound, int rightBound) {

        // {2,5, 8 ,1,4,7, 10 , 3 ,6,9};
//        printArr(arr ,"start,leftBound : " + leftBound +",rightBound : " + rightBound +":");
        if (arr[rightBound] < arr[rightBound - 1])
            swap(arr, rightBound, rightBound - 1);

        int pivot = arr[rightBound - 1];
        int pivot2 = arr[rightBound];

        int less = leftBound;
        int mid = leftBound;
        int more = rightBound - 2;
        if (mid > more) {
            return;
        }

        while (mid <= more) {
            //printArr(arr ,"while start,less : " + less +",mid : " + mid+",more : " + more +": ");
            while (mid <= more && arr[less] <= pivot) {
                less++;
                mid++;
            }
            while (mid <= more && arr[mid] > pivot && arr[mid] <= pivot2)
                mid++;

            while (mid <= more && arr[more] > pivot2) more--; //2-8 3-1 7-3


            if (less < mid && arr[mid] <= pivot) {
                swap(arr, less, mid);
            }

            if (mid < more && arr[mid] > pivot2) {
                swap(arr, mid, more);
            }

            //printArr(arr ,"while end,less : " + less +",mid : " + mid+",more : " + more +": ");

        }

        if (less == mid && less != rightBound - 1) {
            swap2(arr, less, rightBound - 1);
            swap(arr, rightBound, rightBound - 1);
        } else {
            if (arr[less] > arr[rightBound - 1]) {
                swap2(arr, less, rightBound - 1);
                swap2(arr, mid, rightBound - 1);
            }
            if (arr[mid + 1] > arr[rightBound]) {
                swap(arr, mid + 1, rightBound);
            }
        }

        //printArr(arr ,"end,less : " + less +",mid : " + mid+",more : " + more +": ");

        quickSort(arr, leftBound, less - 1);
        quickSort(arr, less + 1, mid);
        quickSort(arr, more + 2, rightBound);
    }

}
