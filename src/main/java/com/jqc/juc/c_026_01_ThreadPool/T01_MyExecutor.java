package com.jqc.juc.c_026_01_ThreadPool;

import java.util.concurrent.Executor;

public class T01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
//        new Thread(command).run();
        command.run();
    }
}
