package com.jqc.algorithm.sort;

import com.jqc.algorithm.sort.util.SortBase;

import java.util.Arrays;

public class RadixSort extends SortBase {
    @Override
    public void sort(int[] arr) { //获取位数方法取自于老师
//        printArr(arr ,"start");
        int bit = 1;
        for (int i = 0; i < arr.length; i++) {
            int len = String.valueOf(i).length();
            bit = bit < len ? len : bit;
        }
        int[] result = radixSort(arr, bit);
//        printArr(result ,"end");
    }

    private int[] radixSort(int[] arr, int bit) {
        int[] result = new int[arr.length];

        for (int i = 0; i < bit; i++) {
            int[] countArr = new int[10];

            int division = (int) Math.pow(10, i);
//            System.out.println(division);

            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / division % 10;
                countArr[num]++;
            }
            for (int j = 1; j < countArr.length; j++) {
                countArr[j] = countArr[j] + countArr[j - 1];
            }
            for (int j = arr.length - 1; j >= 0; j--) {
                int num = arr[j] / division % 10;
                result[--countArr[num]] = arr[j];
            }

//            printArr(result ,"end bit - "+i+":");
            System.arraycopy(result, 0, arr, 0, result.length);
            Arrays.fill(countArr, 0);
        }


        return result;
    }

}
