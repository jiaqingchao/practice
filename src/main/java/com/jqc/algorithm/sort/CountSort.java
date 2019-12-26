package com.jqc.algorithm.sort;

import com.jqc.algorithm.sort.util.SortBase;

public class CountSort extends SortBase {
    @Override
    public void sort(int[] arr) {// 改进 改成稳定，和下标不为0
        //printArr(arr ,"start");
        //int[] result = quickSort(arr);
//        printArr(result ,"end");
    }

    private int[] quickSort(int[] arr) {
        int[] result = new int[arr.length];
        int[] countArr = new int[5001];
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]]++;
        }
        //printArr(countArr ,"end");
        for (int i = 0, j = 0; i < countArr.length; i++) {
            while (countArr[i]-- > 0) {
                result[j++] = i;
            }
        }
        System.arraycopy(result, 0, arr, 0, result.length);
        return result;
    }

    @Override
    public void sort2(int[] arr) {// 下标不为0，稳定
        //printArr(arr ,"start");
        int[] result = quickSort2(arr, 1000, 6000);
//        printArr(result ,"end");
    }

    private int[] quickSort2(int[] arr, int start, int end) { // 下标不为0

        int[] result = new int[arr.length];
        int[] countArr = new int[end - start + 1];

        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - start]++;
        }
        //printArr(countArr ,"end");
        for (int i = 0, j = 0; i < countArr.length; i++) { //我想出的稳定的排序，因为用了双层循环，速度会慢
            int k = 0;
            while (countArr[i] > 0) {
                if (i + start == arr[k]) {
                    result[j++] = arr[k];
                    countArr[i]--;
                }
                k++;
            }
        }

        System.arraycopy(result, 0, arr, 0, result.length);
        return result;
    }

    @Override
    public void sort3(int[] arr) {// 稳定
        //printArr(arr ,"start");
        int[] result = quickSort3(arr, 1000, 6000);
//        printArr(result ,"end:");
    }

    private int[] quickSort3(int[] arr, int start, int end) {//老师的，使用累加数组

        int[] result = new int[arr.length];
        int[] countArr = new int[end - start + 1];

        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - start]++;
        }
        //老师的==========================================

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i] + countArr[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            result[--countArr[arr[i] - start]] = arr[i];
        }

        //================================================

//        printArr(arr,"");
        System.arraycopy(result, 0, arr, 0, result.length);

        return result;
    }

}
