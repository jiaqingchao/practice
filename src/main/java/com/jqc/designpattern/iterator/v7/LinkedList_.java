package com.jqc.designpattern.iterator.v7;

class LinkedList_<E> implements Collection_<E> {
    Node head = null;
    Node tail = null;
    //容器中有多少元素
    private int size = 0;

    @Override
    public void add(E e){
        Node n = new Node(e);
        n.next = null;
        if(head == null){
            head = n;
            tail = n;
        }
        tail.next = n;
        tail = n;
        size++;
    }

    @Override
    public int size(){
        return size;
    }

    private class  Node {
        E e;
        Node next = null;

        public Node(E e) {
            this.e = e;
        }
    }

    public Iterator_<E> iterator(){
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator_<E> {
        private Node currentNode = head;

        @Override
        public boolean hasNext() {
            if(currentNode == null){
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
