package com.jqc.practice.designpattern.strategy;

public class Sorter<T> {

    public void sort(T[] arr, Comparator<T> comparator){
        for(int i = 0; i < arr.length - 1; i++){
            int minPos = i;
            for(int j = i + 1; j < arr.length; j++){
                minPos = comparator.compare(arr[j], arr[minPos]) == -1 ? j : minPos;

            }
            swap(arr, i, minPos);
        }
    }
    public void sort(Comparable[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            int minPos = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j].compareTo(arr[minPos]) == -1){
                    minPos = j;
                }
            }
            swap(arr, i, minPos);
        }
    }

    private void swap(Comparable[] arr, int i, int j){
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    void swap(T[] arr, int i, int j){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
