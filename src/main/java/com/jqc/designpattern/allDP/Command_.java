package com.jqc.designpattern.allDP;

import java.util.LinkedList;
import java.util.List;

public class Command_ {
    public static void main(String[] args) {
        Text_ t = new Text_();
        Command c = new CommandChain(t);
        c.doit();
        System.out.println(t.msg);
        c.undo();
        System.out.println(t.msg);

    }
}

class Text_ {
    public String msg = "1234567890abcdefghijklmnopqrstuvwxyz";
}

class CommandChain implements Command{
    List<Command> commands = new LinkedList<>();

    public CommandChain(Text_ text) {
        add(new DelectCommand(text));
        add(new AddCommand(text));
    }

    public void add(Command c) {
        commands.add(c);
    }

    @Override
    public void doit() {
        commands.forEach(command -> {
            command.doit();
        });
    }

    @Override
    public void undo() {
        for (int i = commands.size() -1 ; i>= 0;i--) {
            commands.get(i).undo();
        }
    }
}

interface Command{
    void doit();
    void undo();
}
class DelectCommand implements Command{
    Text_ text;
    String delStr;

    public DelectCommand(Text_ text) {
        this.text = text;
    }

    @Override
    public void doit() {
        String msg = text.msg;
        delStr = msg.substring(0,10);
        text.msg = msg.substring(10, msg.length());
    }

    @Override
    public void undo() {
        text.msg = delStr + text.msg;
    }
}
class AddCommand implements Command{
    Text_ text;

    public AddCommand(Text_ text) {
        this.text = text;
    }

    @Override
    public void doit() {
        text.msg = text.msg + "1234567890";
    }

    @Override
    public void undo() {
        text.msg =  text.msg.substring(0,text.msg.length() - 10);
    }
}