package com.jqc.designpattern.chainOfResposibillity.servlet;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Request req = new Request();
        Response res = new Response();
        req.setStr("request");
        res.setStr("response");
        //req.setStr("大家好:),<script>,欢迎访问 jingqingchao.com ,大家都是996");

        FilterChain chain = new FilterChain();
        chain.add(new FaceFilter())
                .add(new SensitiveFilter());

        chain.doFilter(req, res, chain);

        System.out.println(req);
        System.out.println(res);
    }
}

interface Filter {
    boolean doFilter(Request req, Response res, FilterChain chain);
}

class Response {
    String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Response{" +
                "str='" + str + '\'' +
                '}';
    }
}

class Request {
    String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Request{" +
                "str='" + str + '\'' +
                '}';
    }
}

class FaceFilter implements Filter {
    @Override
    public boolean doFilter(Request req, Response res, FilterChain chain) {
        System.out.println(1);
        chain.filters.remove(this);

        chain.doFilter(req, res, chain);

        System.out.println(4);
        return false;
    }
}

class SensitiveFilter implements Filter {
    @Override
    public boolean doFilter(Request req, Response res, FilterChain chain) {
        System.out.println(2);
        chain.filters.remove(this);
        chain.doFilter(req, res, chain);
        System.out.println(3);
        return false;
    }
}

class FilterChain implements Filter {
    LinkedList<Filter> filters = new LinkedList<>();

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    @Override
    public boolean doFilter(Request req, Response res, FilterChain chain) {
        for (Filter f : chain.filters) {
            if (!f.doFilter(req, res, chain)) {
                return false;
            }
        }
        return true;
    }

}

