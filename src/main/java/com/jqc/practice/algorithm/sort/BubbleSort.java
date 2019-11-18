package com.jqc.practice.algorithm.sort;

import com.jqc.practice.algorithm.sort.util.SortBase;

public class BubbleSort extends SortBase {
    @Override
    public void sort(int[] arr){ // 按照视频动画自己写的
        for(int i = 0, len = arr.length; i < len - 1; i++){
            for (int j = i + 1; j < len; j++){
                if(arr[i] > arr[j]){
                    swap(arr, i, j);
                }
            }

            //printArr(arr, "第"+(i+1)+"次循环：");
        }
       //printArr(arr, "sort end : ");
    }
    @Override
    public void sort2(int[] arr){ // 看视频写出
        for (int i = arr.length - 1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if(arr[j] > arr[j+1])
                    swap(arr, j, j + 1);
            }
        }
        //printArr(arr, "sort end : ");
    }
    @Override
    public void sort3(int[] arr){//网站查询 虽然最快时间是O(n),但在再不是最优的情况下，因为更多的执行语句，速度会慢
        boolean flag;
        for (int i = arr.length - 1; i > 0; i--){
            flag = true;
            for (int j = 0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if(flag){
                return;
            }
        }
        //printArr(arr, "sort end : ");
    }

}
