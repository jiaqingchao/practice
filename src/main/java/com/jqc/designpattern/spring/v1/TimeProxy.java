package com.jqc.designpattern.spring.v1;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;

public class TimeProxy {
    public void before(){
        System.out.println("method start.."+System.currentTimeMillis());
    }
    public void after(){
        System.out.println("method stop.."+System.currentTimeMillis());
    }
}
