package com.jqc.practice.algorithm.sort;

import com.jqc.practice.algorithm.sort.util.DataChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AllSort {

    /**
     * 选择排序
     * 一次循环找出最小值的下标，
     * 然后将其放在当前查找数组的最前边
     * 稳定性：不稳，所以基本不用
     * @param arr
     */

    public void selectionSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            int minPos = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[minPos]){
                    minPos = j;
                }
            }
            swap(arr, i, minPos);
        }

//        printArr(arr);
    }
    /**
     * 冒泡排序
     * 从前往后找，两两比较，如果前边的数小，则交换位置，直到最后一位，
     * 之后重复这一过程
     * 注释部分为最快时间复杂度的写法，不过提高平均时间复杂度
     * 稳定性：稳，但速度太慢，基本不用
     * @param arr
     */

    public void bubbleSort(int[] arr){
        for(int i = arr.length - 1; i > 0; i--){
//            boolean falg = true;
            for(int j = 0; j < i; j++){
                if(arr[j + 1] < arr[j]){
                    swap_binary(arr, j + 1, j);
//                    falg = false;
                }
            }
//            if(falg) return;
        }
    }

    /**
     * 插入排序
     * 将前边的m个数作为一个有序数组，初始为1，
     * 然后将有序数组后边的的数插入有序数组，从后边比较，把大的往后挪，
     * 把这个数放到应该放的位置然后有序数组长度加1，重复这个过程
     * 简单排序首选，速度不输于选择排序，并且是稳定的，优化后速度更快
     * 注释部分为未优化的
     * @param arr
     */

    public void insertionSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int temp = arr[i];
            int j = i;
            while (j > 0 && temp < arr[j - 1]){ // temp < arr[j - 1] 放在 if 里,搞了半个小时
            //while (j > 0 && arr[j] < arr[j - 1]){
                arr[j] = arr[--j];
                //swap_binary(arr, j ,j - 1); // i和j换位置
                //j--;
            }
            arr[j] = temp;
        }

    }

    /**
     * 希尔排序
     * @param arr
     */
    public void shellSort(int[] arr){
        int h = 1;
        while(h < arr.length / 3){ //knuth 间隔序列
            h = 3 * h + 1;
        }
        for(h = h; h > 0; h = (h - 1) / 3){ //bug int h = arr.length/3
            for(int i = h; i < arr.length; i++){
                int temp = arr[i];
                int j = i;
                while (j >= h && temp < arr[j - h]){ //bug j > h
                    arr[j] = arr[j - h];
                    j = j - h;
                }
                arr[j] = temp;
            }
        }
    }

    /**
     * 归并排序
     * @param arr
     */
    public void mergeSort(int[] arr){
        mergeRecursion(arr, 0, arr.length - 1);

    }

    public void mergeRecursion(int arr[], int left, int right){
        if(left >= right) return; //bug 未加

        int mid = left + (right - left) / 2;

        mergeRecursion(arr, left, mid);
        mergeRecursion(arr, mid + 1, right);

        merge(arr, left,mid + 1 ,right);
    }

    public void merge(int arr[], int leftPtr, int rightPtr , int rightBound){
        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

        int[] temp = new int[rightBound - leftPtr + 1];

        while (i <= rightPtr - 1 && j <= rightBound){
            if(arr[i] < arr[j]){
                temp[k++] = arr[i++];
            }else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= rightPtr - 1){
            temp[k++] = arr[i++];

        } while (j <= rightBound){
            temp[k++] = arr[j++];
        }

        for (int m = 0; m < temp.length; m++){
            arr[leftPtr + m] = temp[m];
        }
    }

    /**
     * 快速排序
     * @param arr
     */
    public void quickSort(int[] arr){ // QuickSort 类里的有问题
        quickRecursion(arr, 0, arr.length - 1);

    }
    public void quickRecursion(int arr[], int left, int right){
        if(left >= right) return;

        int mid = quickSort(arr, left, right);

        mergeRecursion(arr, left, mid - 1);
        mergeRecursion(arr, mid + 1, right);


    }

    public int quickSort(int arr[], int leftBound, int rightBound){

        int left = leftBound;
        int right = rightBound - 1;
        int pivot = arr[rightBound];

        while (left <= right){
//            printString_Arrr(arr, "while start : ");

            while(left <= right && arr[left] <= pivot) left++;
            while(left <= right && arr[right] > pivot) right--; // bug right++

            if(left < right) //bug arr[left]>arr[right]
                swap_binary(arr, left, right);

//            printString_Arrr(arr, "while end : ");
        }
        swap(arr, left, rightBound);

//        printString_Arrr(arr, "end : ");

        return left;
    }

    /**
     * 双轴快排
     */
    public void dualQuickSort(int[] arr){ // 写完直接ok  DualQuickSort类里的有问题
        quickRecursion(arr, 0, arr.length - 1);
    }

    public void dualQuickSort(int arr[], int leftBound, int rightBound){
        if(leftBound >= rightBound) return;

        int less = leftBound;
        int mid = leftBound;
        int more = rightBound - 2;

        if(arr[rightBound - 1] > arr[rightBound]){
            swap_binary(arr, rightBound - 1, rightBound);
        }

        int pivot = arr[rightBound - 1];
        int pivot2 = arr[rightBound];

        while (mid <= more){
//            printString_Arrr(arr, "while start : ");

            while(mid <= more && arr[less] <= pivot){less++;mid++;}
            while(mid <= more && arr[mid] > pivot && arr[mid] <= pivot2) mid++;
            while(mid <= more && arr[more] > pivot2) more--;

            if(mid <= more && arr[mid] <= pivot){
                swap(arr, less, mid);
                swap(arr, mid, more);
            }else if(mid <= more && arr[mid] > pivot2){
                swap(arr, mid, more);
                swap(arr, less, mid);
            }else if(mid <= more){
                swap(arr, less, more);
            }
//            printString_Arrr(arr, "while end : ");
        }
        swap(arr, less, rightBound - 1);
        swap(arr, mid, rightBound);

        mergeRecursion(arr, leftBound, less - 1);
        mergeRecursion(arr, less + 1, mid - 1);
        mergeRecursion(arr, mid + 1, rightBound);
    }

    /**
     *计数排序
     * @param arr
     */
    public int[] countSort(int[] arr){
        int maxInt = arr[0];
        int minInt = arr[0];
        for(int i = 1; i< arr.length;i++){
            if(arr[i]<minInt) minInt = arr[i];
            if(arr[i]>maxInt) maxInt = arr[i];
        }
        int[] count = new int[maxInt-minInt+1];
        int[] result = new int[arr.length];

        for(int i = 0; i< arr.length;i++){ // bug i=0， i< arr.length //count.length i = count[0]
            count[arr[i] - minInt]++; // bug count[i]++ count[arr[i]]++;
        }

        for(int i = 1;i<count.length;i++){//bug i = count[0]
            //System.out.println(i +" " + (arr[i] - minInt) + " " + count[arr[i] - minInt]);
            count[i] = count[i] + count[i-1];
        }

        for(int i = arr.length - 1;i >= 0; i--){ //bug i > 0 // 20分钟

//            System.out.println(i +" " + (arr[i] - minInt) + " "+ arr[i] + " " + count[arr[i] - minInt]);
            result[--count[arr[i] - minInt]] = arr[i]; //result[--count[arr[i]]] = arr[i];
            //printArr(result);
        }
        return result;
    }

    /**
     *基数排序
     * @param arr
     */
    public void radixSort(int[] arr){
        int bit = 1;
        for(int i = 0; i< arr.length;i++){
            bit = String.valueOf(arr[i]).length() > bit ? String.valueOf(arr[i]).length() : bit;
        }
        int[] count = new int[10];
        int[] result = new int[arr.length];

        for(int i =0;i < bit;i++){
            int division = (int) Math.pow(10,i); // bug Math.pow(10,bit);
            for(int j = 0; j< arr.length;j++){
                count[arr[j] /division %10]++;
            }
            for(int j = 1;j<count.length;j++){
                count[j] = count[j] + count[j-1];
            }
            for(int j = arr.length - 1;j >= 0; j--){
                result[--count[arr[j] / division %10]] = arr[j];
            }
            System.arraycopy(result, 0, arr, 0, arr.length);
            Arrays.fill(count, 0);
        }
    }

    /**
     *桶排序
     * @param arr
     */
    public void bucketSort(int[] arr){
        int maxInt = arr[0];
        int minInt = arr[0];
        for(int i = 1; i< arr.length;i++){
            if(arr[i]<minInt) minInt = arr[i];
            if(arr[i]>maxInt) maxInt = arr[i];
        }
        int[] result = new int[arr.length];
        int[] bucket = new int[10];
        var bucketSize = (maxInt - minInt) / 10;
        if(bucketSize == 0) bucketSize = 1;

        for(int i = 0; i <  bucket.length; i++){
            List bucketArr = new ArrayList<Integer>();
            for(int j = 0; j < arr.length; j++){
                int bucketMinInt = minInt + bucketSize * i;
                //bug int bucketMaxInt = maxInt - bucketSize * (bucket.length - j - 1));
                int bucketMaxInt = minInt + bucketSize * (i + 1);
                //bug if(j == arr.length - 1){
                if(i == bucket.length - 1){
                    bucketMaxInt = maxInt + 1;
                }
                /// bug if(arr[j] >= bucketMinInt && arr[j] < bucketMaxInt){
                if(arr[j] >= bucketMinInt && arr[j] < bucketMaxInt){
                    bucket[i]++;
                    bucketArr.add(arr[j]);
                }
            }
            int left = 0;
            if(i > 0){
                bucket[i] = bucket[i] + bucket[i - 1];
                left = bucket[i - 1];
            }

            int right = bucket[i] - 1;

            for(int j = 0; j< bucketArr.size(); j++ ){
                result[left + j] = (int)bucketArr.get(j);
            }

            mergeRecursion(result, left, right);
        }

        System.arraycopy(result, 0, arr, 0, arr.length);
//        printArr(result);
    }

    private  void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private void swap_binary(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
    private static void printArr(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
    private static void printString_Arrr(int[] arr, String beforeStr){
        System.out.println(beforeStr + Arrays.toString(arr));
    }

    public static int[] generateRandomArray(int length, int start, int end){
        int[] arr = new int[length];

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(end - start + 1) + start;
        }

        return arr;
    }

    protected static void check(){
        int wrong = 0;

        for(int i = 0; i < 100; i++){
            int[] arr = generateRandomArray(10000,0,5000);

            int[] arr2 = new int[arr.length];
            System.arraycopy(arr, 0, arr2, 0, arr.length);

            Arrays.sort(arr);

            new AllSort().bucketSort(arr2);

            if(!DataChecker.check(arr, arr2))
                wrong++;

        }
        System.out.println("wrong : " + wrong);

    }

    public static void sort(){
        int[] arr = generateRandomArray(20,1,150);
        //int[] arr = {2,4,6,8,10,1,3,5,7,9};
        printArr(arr);
        new AllSort().bucketSort(arr);
        printArr(arr);
    }

    public static void main(String[] args) {
        sort();
        check();
    }

}
