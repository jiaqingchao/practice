package com.jqc.designpattern.iterator.v7;

/**
 * 相比数组，这个容器不考虑边界问题，可以动态扩展
 */
class ArrayList_<E> implements Collection_<E> {
    E[] objects = (E[]) new Object[10];
    //objects中下一个空的位置在哪er,或者说目前容器中有多少个元素
    private int index = 0;

    @Override
    public void add(E e) {
        if (index == objects.length) {
            E[] newObjects = (E[]) new Object[objects.length * 2];
            System.arraycopy(objects, 0, newObjects, 0, objects.length);
            objects = newObjects;
        }
        objects[index] = e;
        index++;
    }

    @Override
    public int size() {
        return index;
    }

    public Iterator_<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator_<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (currentIndex >= index) {
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            E e = objects[currentIndex];
            currentIndex++;
            return e;
        }
    }

}
