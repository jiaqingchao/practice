package com.jqc.designpattern.allDP;

import java.util.ArrayList;
import java.util.List;

public class Composite {
    public static void main(String[] args) {
        Branch root = new Branch("root");
        Branch a1 = new Branch("a1");
        File f11 = new File("f11");
        a1.add(f11);
        Branch a2 = new Branch("a2");
        File f21 = new File("f21");
        File f22 = new File("f22");
        a2.add(f21).add(f22);
        root.add(a1).add(a2);
        Branch b11 = new Branch("b11");
        a1.add(b11);
        Branch f111 = new Branch("f111");
        b11.add(f111);
        Branch b21 = new Branch("b21");
        a2.add(b21);
        Branch c111 = new Branch("c111");
        b11.add(c111);
        Branch f1111 = new Branch("f1111");
        c111.add(f1111);

        printNode(root,0);
    }
    public static void printNode(Node n, int layer){
        for(int i = 0;i < layer;i++){
            System.out.print("--");
        }
        n.print();
        if(n instanceof Branch){
            Branch b = (Branch)n;
            layer++;
            for (Node node: b.nodes) {
                printNode(node, layer);
            }
        }
    }

}
abstract class Node{
    public abstract void print();
}
class Branch extends Node{
    List<Node> nodes = new ArrayList<>();
    String name;

    public Branch(String name) {
        this.name = name;
    }
    @Override
    public void print(){
        System.out.println(name);
    }

    public Branch add(Node n){
        nodes.add(n);
        return this;
    }
}
class File extends Node{
    String name;

    public File(String name) {
        this.name = name;
    }
    @Override
    public void print(){
        System.out.println(name);
    }

}
