package com.jqc.designpattern.observer.v9;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        Button b = new Button();
        b.addActionListener(new MyActionListener3());
        b.addActionListener(new MyActionListener4());
        b.buttonPressed();
    }
}

class Button {
    private List<ActionListener2> actionListeners = new ArrayList<ActionListener2>();

    public void buttonPressed() {
        ActionEvent e = new ActionEvent(System.currentTimeMillis(), this);
        for (ActionListener2 ActionListener2 : actionListeners) {
            ActionListener2.actionPerformed(e);
        }
    }

    public void addActionListener(ActionListener2 l) {
        actionListeners.add(l);
    }
}

interface ActionListener2 {
    public void actionPerformed(ActionEvent e);
}

class MyActionListener3 implements ActionListener2 {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("button pressed!");
    }
}

class MyActionListener4 implements ActionListener2 {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("button pressed 2!");
    }
}

interface Event<T> {
    abstract T getSource();
}

class ActionEvent implements Event<Button> {
    long currentTimeMillis;
    Button source;

    public ActionEvent(long currentTimeMillis, Button source) {
        this.currentTimeMillis = currentTimeMillis;
        this.source = source;
    }

    @Override
    public Button getSource() {
        return source;
    }
}