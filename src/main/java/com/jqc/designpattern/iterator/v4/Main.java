package com.jqc.designpattern.iterator.v4;

/**
 * v1构建一个容器，可以添加对象
 * v2用链表来实现一个容器
 * v3添加容器的共同接口，实现容器的替换
 */
public class Main {

    public static void main(String[] args) {
        Collection_ list = new ArrayList_();
        for (int i = 0; i < 15; i++) {
            list.add(new String("s" + i));
        }
        System.out.println(list.size());
        ArrayList_ al = (ArrayList_) list;
        for (int i = 0; i < list.size(); i++) {
            //如果用这种遍历，就不能实现通用了
        }
    }

}

