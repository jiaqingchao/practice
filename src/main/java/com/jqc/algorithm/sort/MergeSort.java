package com.jqc.algorithm.sort;

import com.jqc.algorithm.sort.util.SortBase;

public class MergeSort extends SortBase {
    @Override
    public void sort(int[] arr) { // 再看视频前写的，没有写出
        //arr = new int[]{1, 3, 6, 7, 2, 4, 5, 8, 9};
        // mergeSort(arr);
        mergeSort(arr, 0, arr.length - 1);
//        printArr(arr, "sort end : ");
//        printArr(arr, "sort end : ");

    }

    // public int[] mergeSort(int[] arr){
    public void mergeSort(int[] arr, int left, int right) {

//        if (checkArrShortAndSwap(arr))
//            return arr;
        if (left == right)
            return;

        //mergeArr(arr);

//        int mid = arr.length >> 1;
//        int[] temp = new int[arr.length];

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        // mergeArr(arr, left, mid+1 , right);

        mergeArr(arr, left, mid + 1, right);
    }

    private void mergeArr(int[] arr, int leftPtr, int rightPtr, int rightBound) {
        int[] temp = new int[rightBound - leftPtr + 1];

        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        while (i <= rightPtr - 1 && j <= rightBound) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= rightPtr - 1) {
            temp[k++] = arr[i++];
        }
        while (j <= rightBound) {
            temp[k++] = arr[j++];
        }

//        printArr(temp, "sort end : ");
//        arr = temp;
//        printArr(arr, "sort end : ");
//        return temp;
        for (int m = 0; m < temp.length; m++) {
            arr[leftPtr + m] = temp[m];
        }
    }

//    private boolean checkArrShortAndSwap(int[] arr) {
//        if(arr.length == 1){
//            return true;
//        }
//        if(arr.length == 2){
//            if(arr[0] > arr[1]){
//                swap(arr, 0, 1);
//            }
//            return true;
//        }
//        return false;
//    }

//    private int[] mergeArr(int[] arr) {
//        int mid = arr.length >> 1;
//
//        int[] arr1 = new int[mid];
//        int[] arr2 = new int[arr.length - mid];
//
//        System.arraycopy(arr,0,arr1,0, arr1.length);
//        arr1 = mergeSort(arr1);
//        System.arraycopy(arr,mid,arr2,0, arr2.length);
//        arr2 = mergeSort(arr2);
//
//        System.arraycopy(arr1,0,arr,0, arr1.length);
//        System.arraycopy(arr2,0,arr,mid, arr2.length);
//    }

    @Override
    public void sort2(int[] arr) {
        mergeSort2(arr, 0, arr.length - 1);
//        printArr(arr, "sort end : ");
    }

    public void mergeSort2(int[] arr, int left, int right) { // 看视频写出
        if (left == right)
            return;

        int mid = left + (right - left) / 2;

        mergeSort2(arr, left, mid);
        mergeSort2(arr, mid + 1, right);

        mergeArr2(arr, left, mid + 1, right);

    }

    public void mergeArr2(int[] arr, int leftPtr, int rightPtr, int rightBound) {

        int[] temp = new int[rightBound - leftPtr + 1];

        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        while (i <= rightPtr - 1 && j <= rightBound) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= rightPtr - 1) {
            temp[k++] = arr[i++];
        }
        while (j <= rightBound) {
            temp[k++] = arr[j++];
        }

        for (int m = 0; m < temp.length; m++) {
            arr[leftPtr + m] = temp[m];
        }

    }

    @Override
    public void sort3(int[] arr) { // 看视频后独立写出
        mergeSort3(arr, 0, arr.length - 1);
    }

    private void mergeSort3(int[] arr, int left, int right) {
        if (left == right) return; //这个的位置看了代码

        int mid = left + (right - left) / 2; //防止溢出
        //int mid = (right + left) / 2;
        mergeSort3(arr, left, mid);
        mergeSort3(arr, mid + 1, right);

        mergeArr3(arr, left, mid + 1, right);
    }

    private void mergeArr3(int[] arr, int leftPtr, int rightPtr, int rightBound) {

        int[] temp = new int[rightBound - leftPtr + 1];
        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        while (i <= rightPtr - 1 && j <= rightBound) {
            if (arr[i] <= arr[j]) { // 之前不是<=,第二天改回
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }

        }
        while (i <= rightPtr - 1) {
            temp[k++] = arr[i++];
        }
        while (j <= rightBound) {
            temp[k++] = arr[j++];
        }
        for (int m = 0; m < temp.length; m++) {
            arr[leftPtr + m] = temp[m];
        }
    }
}
