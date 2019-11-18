package com.jqc.practice.designpattern.strategy;

public class Cat implements Comparable<Cat>, Comparator<Cat>{
    int weight,height;

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int compareTo(Cat c){
        if(this.weight < c.weight){
            return -1;
        }else if(this.weight > c.weight){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    @Override
    public int compare(Cat o1, Cat o2) {
        return 0;
    }
}
