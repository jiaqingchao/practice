package com.jqc.practice.algorithm.sort;

import com.jqc.practice.algorithm.sort.util.SortBase;

import java.util.Random;

public class QuickSort extends SortBase {// 两边一起找，找到左边比中轴大的，找到右边比中轴小的，两个直接做交换
    @Override
    public void sort(int[] arr) { // 按照快速排序思路自己写出(花了8个小时，感觉不划算) //感觉和归并一样，而且速度没有归并快
        quickSort(arr,0,arr.length-1);
    }

    private void quickSort(int[] arr, int left, int right){
        if(left >= right) return;

        int axisForArr = quickArr(arr, left, right);

        quickSort(arr, left, axisForArr - 1);
        quickSort(arr, axisForArr + 1, right);

    }

    private int quickArr(int[] arr, int left, int right) { //未看视频书写
        int axis = (right + left) >> 1;

        int tempLength = right - left + 1;

        int[] temp = new int[tempLength];

        int j = 0;
        int i = left;
        int k = tempLength - 1;
        while (i <= right){
            if(i == axis){
                i++;
                continue;
            }
            if(arr[i] < arr[axis]){
                temp[j++] = arr[i++];
            }else{
                temp[k--] = arr[i++];
            }
        }

        temp[j] = arr[axis];

//        printArr(temp, "temp:");

        for(int m = 0; m < temp.length; m++){
             arr[left + m] = temp[m];
        }

        return left + j;
    }

    @Override
    public void sort2(int[] arr) { // 根据老师给的思路写的，花了一个半小时，仅一个分区
        //printArr(arr,"");

        quickSort2(arr,0,arr.length - 1);
        //printArr(arr,"");
    }

    private void quickSort2(int[] arr,int left, int right){
//        System.out.println("left: "+ left+"right: "+right);
//        printArr(arr,"arr start");
        if(left >= right) return;

        int leftSize = 0;
        int rightSize = 0;

        int axis = right;

        int i = left;

        while (i < right - rightSize) {
            if (arr[axis] > arr[i]) {
                leftSize++;
                i++;
            } else {

                if(axis - ++rightSize != i)
                    swap(arr,  axis - rightSize , i);

            }
            //printArr(arr,"arr while");
        }

//        System.out.println("leftSize:"+leftSize);

        if(axis != left + leftSize)
            swap(arr, axis, left + leftSize);

        //printArr(arr,"arr end");
        quickSort2(arr, left, left + leftSize - 1);
        quickSort2(arr,left + leftSize + 1, right);
    }

    @Override
    public void sort3(int[] arr) {// 看视频后写出
        quickSort3(arr,0,arr.length - 1);
    }

    private void quickSort3(int[] arr,int leftBound, int rightBound) {

        if(leftBound >= rightBound)
            return;

        int mid = partition3(arr, leftBound, rightBound);

        quickSort3(arr, leftBound, mid - 1);
        quickSort3(arr, mid + 1, rightBound);

    }

    private int partition3(int[] arr,int leftBound, int rightBound){
        int pivot = arr[rightBound];

        int left = leftBound; // 7
        int right = rightBound - 1; // 8

        while (left < right){  // swap(arr, left, rightBound); --> if(arr[left] > arr[rightBound])

            while (left <= right && arr[left] <= pivot) left++; // left < rightBound;
            while (left <= right && arr[right] > pivot) right--;

            if(left < right)
                swap(arr, left, right);

        }

       if(arr[left] > arr[rightBound])
            swap(arr, left, rightBound);

        return left;

    }

    public void sort4(int[] arr) { // 使用随机数
        quickSort4(arr,0,arr.length - 1);
    }

    private void quickSort4(int[] arr,int leftBound, int rightBound) {

        if(leftBound >= rightBound)
            return;

        int mid = partition4(arr, leftBound, rightBound);

        quickSort4(arr, leftBound, mid - 1);
        quickSort4(arr, mid + 1, rightBound);

    }

    private int partition4(int[] arr,int leftBound, int rightBound){

        //优化pivot取随机位置的数 // 整体速度变慢
        int randomNum = new Random().nextInt(rightBound - leftBound + 1);
        if(leftBound + randomNum != rightBound)
            swap(arr,leftBound + randomNum, rightBound);

        int pivot = arr[rightBound];
        int left = leftBound;
        int right = rightBound - 1;

        while (left < right){

            while (left <= right && arr[left] <= pivot) left++; // left < rightBound;
            while (left <= right && arr[right] > pivot) right--;

            if(left < right)
                swap(arr, left, right);
        }

        if(arr[left] > arr[rightBound])
            swap(arr, left, rightBound);
        return left;

    }


}
