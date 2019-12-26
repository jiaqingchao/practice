package com.jqc.io.netty.s02;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFram extends Frame {
    TextArea ta = new TextArea();
    TextField tf = new TextField();

    public ClientFram() {
        this.setSize(600, 400);
        this.setLocation(100, 20);
        this.add(ta, BorderLayout.CENTER);
        this.add(tf, BorderLayout.SOUTH);
        tf.addActionListener((ActionEvent e) -> {
            ta.setText(ta.getText() + tf.getText());
            tf.setText("");
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ClientFram();
    }
}
