package com.jqc.practice.algorithm.sort;

import com.jqc.practice.algorithm.sort.util.SortBase;

public class InsertionSort extends SortBase {
    @Override
    public void sort(int[] arr){//按照视频动画自己写的
        for(int i = 1; i < arr.length; i++){
            for(int j = i; j > 0 && arr[j - 1] > arr[j]; j--){
                //if(arr[j - 1] > arr[j]) 判断放到for中,可以减少不必要的判断
                    swap(arr, j, j - 1 );
//                else continue; // 错误优化，多一步，时间变长
            }
        }
        //printArr(arr, "sort end : ");

    }
    @Override
    public void sort2(int[] arr){//根据老师优化思路优化（百度）
        insertionSort(arr, 1);
    }

    public static void insertionSort(int[] arr, int gap) {
        for (int i = gap; i < arr.length; i++) {
            int j;
            int temp = arr[i];
            for (j = i; j > gap - 1 && temp < arr[j - gap]; j -= gap) {
                arr[j] = arr[j - gap];
            }
            arr[j] = temp;
        }
    }
}
