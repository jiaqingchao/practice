package com.jqc.practice.algorithm.sort.util;

import com.jqc.practice.algorithm.sort.*;

public abstract class SortBase {
    /**
     * 位运算交换，i=j 时 值会变为0;
     * @param arr
     * @param i
     * @param j
     */
    protected static void swap(int[] arr, int i, int j) {
        arr[i] = arr[j]^arr[i];
        arr[j] = arr[j]^arr[i];
        arr[i] = arr[j]^arr[i];

    }
    /**
     * 正常交换
     * @param arr
     * @param i
     * @param j
     */
    protected static void swap2(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
    protected static void printArr(int[] arr, String s) {
        System.out.print(s);
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k] + " ");
        }
        System.out.println();
    }

    public void sort(int[] arr){}
    public void sort2(int[] arr){}
    public void sort3(int[] arr){}
    public void sort4(int[] arr){}

    protected void check(){
        int wrong = 0;
        for(int i=0; i<100; i++){
            int[] arr = DataChecker.generateRandomArray(10000,0,5000);
            int[] arr2 = DataChecker.copyArrAndDoSortArr(arr);
            sort(arr2);
            if(!DataChecker.check(arr, arr2))
                wrong++;

        }
        System.out.println("wrong : " + wrong);

    }
    protected void check2(){
        int wrong = 0;
        for(int i = 0; i < 100; i++){
            int[] arr = DataChecker.generateRandomArray(10000,1000,6000);
            int[] arr2 = DataChecker.copyArrAndDoSortArr(arr);
            sort2(arr2);
            if(!DataChecker.check(arr, arr2))
                wrong++;

        }
        System.out.println("wrong : " + wrong);
    }
    protected void check3(){
        int wrong = 0;
        for(int i = 0; i < 100; i++){
            int[] arr = DataChecker.generateRandomArray(10000,1000,6000);
            int[] arr2 = DataChecker.copyArrAndDoSortArr(arr);
            sort3(arr2);
            if(!DataChecker.check(arr, arr2))
                wrong++;

        }
        System.out.println("wrong : " + wrong);
    }
    protected void check4(){
        int wrong = 0;
        for(int i = 0; i < 100; i++){
            int[] arr = DataChecker.generateRandomArray(10000,1000,6000);
            int[] arr2 = DataChecker.copyArrAndDoSortArr(arr);
            sort4(arr2);
            if(!DataChecker.check(arr, arr2))
                wrong++;

        }
        System.out.println("wrong : " + wrong);
    }

    protected void sortTime(int[] checkArr){
        int[] arr = new int[checkArr.length];

        System.arraycopy(checkArr,0,arr,0,checkArr.length);
        long startTime = System.nanoTime();
        sort(arr);
        long endTime = System.nanoTime();
        System.out.println("sort time : " + (endTime - startTime)/1000);

        System.arraycopy(checkArr, 0, arr, 0, checkArr.length);
        startTime = System.nanoTime();
        sort2(arr);
        endTime = System.nanoTime();
        if(endTime - startTime > 10_0000)
            System.out.println("sort2 time : " + (endTime - startTime)/1000);

        System.arraycopy(checkArr, 0, arr, 0, checkArr.length);
        startTime = System.nanoTime();
        sort3(arr);
        endTime = System.nanoTime();
        if(endTime - startTime > 10_0000)
            System.out.println("sort3 time : " + (endTime - startTime)/1000);

        System.arraycopy(checkArr, 0, arr, 0, checkArr.length);
        startTime = System.nanoTime();
        sort4(arr);
        endTime = System.nanoTime();
        if(endTime - startTime > 10_0000)
            System.out.println("sort4 time : " + (endTime - startTime)/1000);
    }

    public static void main(String[] args) {

        int[] arr = DataChecker.generateRandomArray(150, 5 ,110);
//        int[] arr = new int[]{2,0,5,6,8,1,8,3,5,0};
//        int[] arr = new int[]{7,7,8};
        SortBase sort;
//        sort = new BubbleSort();
//        sort = new SelectionSort();
//        sort = new InsertionSort();
//        sort = new ShellSort();
//        sort = new MergeSort();
//        sort = new QuickSort();
//        sort = new DualQuickSort();
//        sort = new BucketSort();
        sort = new RadixSort();
//        sort.sort(arr);
//        sort.sort2(arr);
//        sort.sort3(arr);
//        sort.sort4(arr);
        sort.check();
//        sort.check2();
//        sort.check3();
//        sort.check4();
      //  showTime();
//printArr(arr,"");
    }
    static void showTime(){

        int[] timeArr = DataChecker.generateRandomArray(10000, 1000,6000);

        SortBase sort;

//        System.out.println("bubbleSort : ");
//        sort = new BubbleSort();
//        sort.sortTime(timeArr);
//        System.out.println("===============================");
//        System.out.println("selectionSort : ");
//        sort = new SelectionSort();
//        sort.sortTime(timeArr);

//        System.out.println("===============================");
//        System.out.println("insertionSort : ");
//        sort = new InsertionSort();
//        sort.sortTime(timeArr);
//        System.out.println("===============================");
//        System.out.println("shellSort : ");
//        sort = new ShellSort();
//        sort.sortTime(timeArr);
//        System.out.println("===============================");
//        System.out.println("mergeSort : ");
//        sort = new MergeSort();
//        sort.sortTime(timeArr);
//        System.out.println("===============================");
//        System.out.println("quickSort : ");
//        sort = new QuickSort();
//        sort.sortTime(timeArr);
//        System.out.println("===============================");
//        System.out.println("dualQuickSort : ");
//        sort = new DualQuickSort();
//        sort.sortTime(timeArr);
        System.out.println("===============================");
        System.out.println("bucketSort : ");
        sort = new BucketSort();
        sort.sortTime(timeArr);
        System.out.println("===============================");
        System.out.println("radixSort : ");
        sort = new RadixSort();
        sort.sortTime(timeArr);

    }

}
