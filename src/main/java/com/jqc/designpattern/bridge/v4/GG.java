package com.jqc.designpattern.bridge.v4;

public class GG {
    public void chage(MM mm) {
        Gift g = new WarmGift(new Flower());
        give(mm, g);
    }

    private void give(MM mm, Gift g) {
        System.out.println(g + "gived!");
    }
}
