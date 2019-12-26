package com.jqc.designpattern.allDP;

public class Iterator {
    public static void main(String[] args) {
        List_<String> list = new LinkedList_<>();
        for (int i = 0; i < 1; i++) {
            list.add("str" + i);
        }
        Iterator_ it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}

interface Iterator_<E> {
    boolean hasNext();

    E next();
}

interface List_<E> {
    void add(E e);

    int size();

    Iterator_<E> iterator();
}

class ArrayList_<E> implements List_<E> {
    E[] arr = (E[]) new Object[10];
    int index = 0;

    @Override
    public void add(E e) {
        if (index >= arr.length) {
            E[] arr2 = (E[]) new Object[arr.length * 2];
            System.arraycopy(arr, 0, arr2, 0, arr.length);
            arr = arr2;
        }
        arr[index++] = e;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public Iterator_ iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator_ {
        int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex >= index) {
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            return arr[currentIndex++];
        }
    }
}

class LinkedList_<E> implements List_<E> {
    int size = 0;

    class Node {
        E e;
        Node next = null;

        public Node(E e) {
            this.e = e;
        }
    }

    Node head = null;
    Node tail = null;

    @Override
    public void add(E e) {
        Node n = new Node(e);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator_<E> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator_ {
        Node currentNode = head;

        @Override
        public boolean hasNext() {
            if (currentNode == null) {
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            E e = currentNode.e;
            currentNode = currentNode.next;
            return e;
        }
    }
}
