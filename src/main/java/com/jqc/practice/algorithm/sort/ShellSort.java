package com.jqc.practice.algorithm.sort;

import com.jqc.practice.algorithm.sort.util.SortBase;

public class ShellSort extends SortBase {
    @Override
    public void sort(int[] arr) {

//        int h = 1;
//        while(h <= arr.length / 3){ //间隔算法
//            h = h * 3 + 1;
//        }

        //int gap = arr.length / 2;// 希尔的想法
        int gap = arr.length / 2;//我的想法

        for (int nowGap = 0; nowGap < gap; nowGap++){ // 根据老师说的希尔排序思路写出
            // 用i++， 后边的用的判断可以不要，优化后为sort2
            for(int i = nowGap; i < arr.length; i += gap){
                int j;
                int temp = arr[i];

                for(j = i; j > gap - 1 && temp < arr[j - gap]; j -= gap){
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }

            if(nowGap + 1 == gap){ // 多余的操作，可以放在最外层循环
                gap =  gap >> 2;
                nowGap = -1;
            }
        }

    }


    @Override
    public void sort2(int[] arr) { //正确的希尔排序
        // 希尔的想法 arr.length >> 1
        for (int gap = arr.length >> 1; gap > 0; gap = gap >> 1) {
            InsertionSort.insertionSort(arr, gap);
        }

    }

    @Override
    public void sort3(int[] arr) { //根据老师代码优化,不用加判断了

        // knuth 间隔序列
        int h = 1;
        while(h <= arr.length/3){
            h = h * 3 + 1;
        }

        for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
            InsertionSort.insertionSort(arr, gap);
        }
    }

}
