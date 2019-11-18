package com.jqc.designpattern.strategy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Cat[] catArr = {new Cat(2,1),new Cat(3,2),new Cat(1,3)};
        Dog[] dogArr = {new Dog(2),new Dog(3),new Dog(1)};
        Sorter<Dog> dogSorter = new Sorter<Dog>();
        Sorter<Cat> catSorter = new Sorter<Cat>();
//        sorter.sort(catArr);
//        sorter.sort(dogArr);

        DogComparator dogComparator = new DogComparator();

       // dogSorter.sort(dogArr, new DogComparator());
//        catSorter.sort(catArr, new CatWeightComparator());
        catSorter.sort(catArr, (o1, o2)->{
            if(o1.weight<o2.weight)return -1;
            else if(o1.weight>o2.weight)return 1;
            else return 0;
        });
        //catSorter.sort(catArr, new CatHeightComparator());


        System.out.println(Arrays.toString(catArr));
        System.out.println(Arrays.toString(dogArr));
    }
}
