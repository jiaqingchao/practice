package com.jqc.designpattern.spring.v1;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class TimeProxy {
    @Before("execution(void com.jqc.designpattern.allDP.Proxy_.start())")
    public void before(){
        System.out.println("method start.."+System.currentTimeMillis());
    }
    @After("execution(void com.jqc.designpattern.allDP.Proxy_.start())")
    public void after(){
        System.out.println("method stop.."+System.currentTimeMillis());
    }
}
