package com.jqc.designpattern.chainOfResposibillity.servlet.v3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Request req = new Request();
        req.setStr("大家好:),<script>,欢迎访问 jingqingchao.com ,大家都是996");
        Response res = new Response();
        res.setStr("");

        FilterChain chain = new FilterChain();
        chain.add(new HTMLFilter())
                .add(new SensitiveFilter());

        chain.doFilter(req, res, chain);
        System.out.println(req.str);
        System.out.println("response--" + res.str);
    }
}

class Msg {
    String name;
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface Filter {
    boolean doFilter(Request req, Response res, FilterChain chain);
}

class HTMLFilter implements Filter {
    @Override
    public boolean doFilter(Request req, Response res, FilterChain chain) {
        String r = req.getStr();
        r = r.replaceAll("<", "[").replaceAll(">", "]") + "HTMLFilter()";
        req.setStr(r);
        chain.doFilter(req, res, chain);
        res.setStr(res.getStr() + "--HTMLFilter()");
        return true;
    }
}

class SensitiveFilter implements Filter {
    @Override
    public boolean doFilter(Request req, Response res, FilterChain chain) {
        String r = req.getStr();
        r = r.replaceAll("996", "995") + "SensitiveFilter()";
        req.setStr(r);
        chain.doFilter(req, res, chain);
        res.setStr(res.getStr() + "--SensitiveFilter()");
        return true;
    }
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

class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<>();
    int index = 0;

    public FilterChain add(Filter f) {
        filters.add(f);
        return this;
    }

    public boolean doFilter(Request req, Response res, FilterChain chain) {
        if (index == filters.size()) return false;
        Filter f = filters.get(index);
        index++;
        return f.doFilter(req, res, chain);
    }

}

