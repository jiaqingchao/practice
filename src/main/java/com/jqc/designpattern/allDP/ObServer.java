package com.jqc.designpattern.allDP;

import java.util.ArrayList;
import java.util.List;

public class ObServer {
    public static void main(String[] args) {
        Button b = new Button();
        b.addListener(new MyActionListener());
        b.addListener(new MyActionListener2());
        b.pressed();
    }
}

interface Event<T> {
    T getSource();
}

class ActionEvent implements Event<Button> {
    Button source;

    public ActionEvent(Button source) {
        this.source = source;
    }

    @Override
    public Button getSource() {
        return source;
    }
}

interface ActionListener<E> {
    void actionPerformed(E e);
}

class MyActionListener implements ActionListener<Event<Button>> {
    @Override
    public void actionPerformed(Event<Button> e) {
        Button b = e.getSource();
        System.out.println(b + " pressed");
    }
}

class MyActionListener2 implements ActionListener<Event<Button>> {
    @Override
    public void actionPerformed(Event<Button> e) {
        Button b = e.getSource();
        System.out.println(b + " pressed2");
    }
}

class Button {
    List<ActionListener> actionListeners = new ArrayList<>();

    public void addListener(ActionListener al) {
        actionListeners.add(al);
    }

    public void pressed() {
        Event e = new ActionEvent(this);
        actionListeners.forEach(al -> al.actionPerformed(e));
    }
}
