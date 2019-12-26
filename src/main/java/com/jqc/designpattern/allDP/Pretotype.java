package com.jqc.designpattern.allDP;

public class Pretotype {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person2 p = new Person2(11, new Person2.Locahost(11, "sss"));
        System.out.println(p);
        Person2 p2 = p.clone();
        p.loc.name = "ww";
        System.out.println(p2);

    }

}

class Person2 implements Cloneable {
    int id;
    Locahost loc;

    public Person2(int id, Locahost loc) {
        this.id = id;
        this.loc = loc;
    }

    static class Locahost implements Cloneable {
        int roomNo;
        String name;

        public Locahost(int roomNo, String name) {
            this.roomNo = roomNo;
            this.name = name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "Locahost{" +
                    "roomNo=" + roomNo +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", loc=" + loc +
                '}';
    }

    @Override
    public Person2 clone() throws CloneNotSupportedException {
        Person2 p = (Person2) super.clone();
        p.loc = (Locahost) this.loc.clone();
        return p;
    }
}