package com.jqc.designpattern.command;

public class Main {
    public static void main(String[] args) {
        Content c = new Content();
        Command insertCommand = new InsertCommand(c);
        insertCommand.doit();
        System.out.println(c.msg);
        insertCommand.undo();


        Command copyCommand = new CopyCommand(c);
        copyCommand.doit();
        System.out.println(c.msg);
        copyCommand.undo();


        Command deleteCommand = new DeleteCommand(c);
        deleteCommand.doit();
        System.out.println(c.msg);
        deleteCommand.undo();

        System.out.println(c.msg);

    }
}
