package com.jqc.practice.algorithm.sort.util;

import java.util.Arrays;
import java.util.Random;

public class DataChecker{
    public static int[] generateRandomArray(){
        int[] arr = new int[10000];

        Random r = new Random();
        for (int i = 0; i < 10000;i++) {
            arr[i] = r.nextInt(10000);
            //arr[i] = i;
        }

        return arr;
    }
    public static int[] generateRandomArray(int length){
        int[] arr = new int[length];

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(length);
        }

        return arr;
    }

    public static int[] generateRandomArray(int length, int start, int end){
        int[] arr = new int[length];

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(end - start + 1) + start;
        }

        return arr;
    }

    public static int[] copyArrAndDoSortArr(int [] arr){

        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);

        Arrays.sort(arr);

        return arr2;
    }

    public static boolean check(int[] arr, int[] arr2){

        boolean same = true;
        for (int j = 0; j < arr2.length; j++){
            if(arr[j] != arr2[j]) same = false;
        }

        return same;

    }

}
