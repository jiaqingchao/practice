package com.jqc.designpattern.allDP;

public class Bridge {
    public static void main(String[] args) {
        Gift gift = new WarmGift(new Flower());
        new GG().give(new MM(), gift);

    }
}

class GG {
    public void give(MM mm, Gift gift) {
        System.out.println("give : " + gift);
    }
}

class MM {
}

abstract class Gift {
    GiftImpl giftImpl;
}

class WarmGift extends Gift {
    public WarmGift(GiftImpl giftImpl) {
        this.giftImpl = giftImpl;
    }
}

class ColdGift extends Gift {
    public ColdGift(GiftImpl giftImpl) {
        this.giftImpl = giftImpl;
    }
}

abstract class GiftImpl {
    abstract void print();
}

class Car extends GiftImpl {
    @Override
    void print() {
        System.out.println("one car");
    }
}

class Flower extends GiftImpl {
    @Override
    void print() {
        System.out.println("one flower");
    }
}