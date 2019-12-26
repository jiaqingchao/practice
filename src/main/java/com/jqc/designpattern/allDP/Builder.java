package com.jqc.designpattern.allDP;

public class Builder {
    public static void main(String[] args) {
        Person p = new Person.PersonBuilder().basicInfo(1, "jqc", 24)
                .weight(160)
                .score(100)
                .location("sz", "106")
                .builer();
        System.out.println(p);
    }
}

class Person {
    int id;
    String name;
    int age;
    double weight;
    int score;
    Location loc;

    private Person() {
    }

    static class PersonBuilder {
        Person p = new Person();

        public PersonBuilder basicInfo(int id, String name, int age) {
            p.id = id;
            p.name = name;
            p.age = age;
            return this;
        }

        public PersonBuilder weight(double weight) {
            p.weight = weight;
            return this;
        }

        public PersonBuilder score(int score) {
            p.score = score;
            return this;
        }

        public PersonBuilder location(String name, String roomNO) {
            p.loc = new Location(name, roomNO);
            return this;
        }

        public Person builer() {
            return p;
        }

    }

    static class Location {
        String name;
        String roomNo;

        public Location(String name, String roomNo) {
            this.name = name;
            this.roomNo = roomNo;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "name='" + name + '\'' +
                    ", roomNo='" + roomNo + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", score=" + score +
                ", loc=" + loc +
                '}';
    }
}
