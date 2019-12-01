package com.jqc.designpattern.state.thread;

public class RunringState extends ThreadState_{
    Thread_ t;
    public RunringState(Thread_ t) {
        t = t;
    }

    @Override
    void move(Action input) {
        if(input.msg == "runring"){
            t.state = new TerminatedState(t);
        }
    }

    @Override
    void run() {

    }
}
