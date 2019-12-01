package com.jqc.designpattern.state.thread;

public class NewState extends ThreadState_{
    Thread_ t;
    @Override
    void move(Action input) {
        if(input.msg == "start"){
            t.state = new RunringState(t);
        }
    }

    @Override
    void run() {

    }
}
