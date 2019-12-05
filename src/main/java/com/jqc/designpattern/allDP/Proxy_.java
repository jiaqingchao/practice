package com.jqc.designpattern.allDP;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Proxy_ {

    public void before(){
        System.out.println("method start.."+System.currentTimeMillis());
    }
    public void after(){
        System.out.println("method stop.."+System.currentTimeMillis());
    }
    public void start(){
        System.out.println("proxy_ start");
    }

    public static void main(String[] args) {
        aopProxy();
    }

    private static void aopProxy(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop_app.xml");
        Proxy_ p = (Proxy_)context.getBean("proxy");
        p.start();
    }

    private static void cglibDynamicProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Tank.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("cglib gbefore");
                methodProxy.invokeSuper(o, objects);
                System.out.println("cglib after");
                return o;
            }
        });
        Tank tank = (Tank)enhancer.create();
        tank.move();
    }

    private static void dynamicProxy() {
        Tank tank = new Tank();
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");//使jdk自动生成代理的class文件
        Movable m = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(),
                new Class[]{Movable.class},
                new TimeProxy(tank));
        m.move();
    }

    static void staticProxy(){
        new LogMoveProxy(new TimeMoveProxy(new Tank())).move();
        new LogMoveProxy(new TimeMoveProxy(new Bullet2())).move();
    }
}
class TimeMoveProxy implements Movable{
    Movable m;

    public TimeMoveProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        before();
        m.move();
        after();
    }

    private void before() {
        System.out.println("startTime : " + System.currentTimeMillis());
    }

    private void after() {
        System.out.println("endTime : " +System.currentTimeMillis());
    }
}
class LogMoveProxy implements Movable{
    Movable m;

    public LogMoveProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        before();
        m.move();
        after();
    }

    private void before() {
        System.out.println("move before");
    }

    private void after() {
        System.out.println("move after");
    }
}
interface Movable{
    void move();
}
class Tank implements Movable{
    public void move(){
        System.out.println("tank move");
    }
}
class Bullet2 implements Movable{
    public void move(){
        System.out.println("bullet move");
    }
}

class TimeProxy implements InvocationHandler {
    Tank tank;

    public TimeProxy(Tank tank) {
        this.tank = tank;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        before();
        method.invoke(tank,objects);
        after();
        return o;
    }

    private void before() {
        System.out.println("before");
    }
    private void after() {
        System.out.println("after");
    }
}



