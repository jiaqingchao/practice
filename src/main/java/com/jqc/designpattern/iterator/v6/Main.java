package com.jqc.designpattern.iterator.v6;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * v1构建一个容器，可以添加对象
 * v2用链表来实现一个容器
 * v3添加容器的共同接口，实现容器的替换
 */
public class Main {

    public static void main(String[] args) {
        Collection list = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            list.add(new String("s" + i));
        }
        System.out.println(list.size());
        Iterator i = list.iterator();
        while (i.hasNext()) {
            Object o = i.next();
            System.out.println(o);
        }
    }

}

