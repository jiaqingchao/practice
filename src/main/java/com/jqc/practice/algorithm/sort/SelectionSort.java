package com.jqc.practice.algorithm.sort;

import com.jqc.practice.algorithm.sort.util.SortBase;

//最简单但是最没用的排序算法，也有优化空间
//初步优化思路，当前找到最小值，那么也找到一个最大值呢
//更进一步优化：一次一个数和第一位的数比，一次两个数呢
public class SelectionSort extends SortBase {

    // O(n^2)
    // 根据视频思路，自己写的（未看完视频，多花了半个小时）
    @Override
    public void sort(int[] arr){
        //   1          1                  n - 1        n
        for (int i = 0, len = arr.length; i < len - 1; i++){
            int minPos = i;

            for (int j = i + 1; j < len; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }
            swap(arr, i, minPos);
        }
        //printArr(arr, "sort end : ");
    }
    @Override
    public void sort2(int[] arr){// 根据优化思路，自己写的
        for (int i = 0,len = arr.length; i < len / 2; i++){

            int minPos = i;
            int maxPos = len - i - 1;

            for(int j = i + 1; j < len - i; j++){
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }

            swap(arr, i, minPos);

            for(int j = len - i - 2 ; j >= i; j--){
                maxPos = arr[j] > arr[maxPos] ? j : maxPos;
            }

            swap(arr, len - i - 1, maxPos);

        }
        //printArr(arr, "sort2 end : ");
    }

    public void sort4(int[] arr){ // 证明排序不稳定性

        arr = new int[]{5,1,5,4,3};

        int[] posArr = new int[arr.length];
        for (int i = 0, len = arr.length; i < len; i++){
            posArr[i] = i;
        }

        for (int i = 0, len = arr.length; i < len - 1; i++){

            int minPos = i;

            for (int j = i + 1; j < len; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }
            swap(arr, i, minPos);

            swap(posArr, i, minPos);

            printArr(arr, "sort3 end : \t");
            printArr(posArr, "arrPos end : \t");
        }

        printArr(arr, "sort3 end : ");
    }

}


