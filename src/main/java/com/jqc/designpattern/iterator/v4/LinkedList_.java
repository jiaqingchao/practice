package com.jqc.designpattern.iterator.v4;

class LinkedList_ implements Collection_ {
    Node head = null;
    Node tail = null;
    //容器中有多少元素
    private int size = 0;

    @Override
    public void add(Object o) {
        Node n = new Node(o);
        n.next = null;
        if (head == null) {
            head = n;
            tail = n;
        }
        tail.next = n;
        tail = n;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node {
        Object o;
        Node next = null;

        public Node(Object o) {
            this.o = o;
        }
    }

}
