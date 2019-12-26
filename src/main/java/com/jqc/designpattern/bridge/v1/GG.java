package com.jqc.designpattern.bridge.v1;

public class GG {
    public void chage(MM mm) {
        Gift g = new Book();
        give(mm, g);
    }

    private void give(MM mm, Gift g) {
    }
}
