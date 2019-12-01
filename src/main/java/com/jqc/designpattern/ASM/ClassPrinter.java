package com.jqc.designpattern.ASM;

import aj.org.objectweb.asm.*;

import java.io.IOException;

public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(Opcodes.ASM4);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + "extends" + superName+"{");
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object valuer) {
        System.out.println("   "+ name);
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int i, String name, String s1, String s2, String[] strings) {
        System.out.println("   "+ name + "()");
        return null;
    }

    @Override
    public void visitEnd() {
        System.out.println("}");
    }

    public static void main(String[] args) throws IOException {
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("java.lang.Runnable");
        //ClassReader cr = new ClassReader(ClassPrinter.class.getClassLoader().getResourceAsStream("com/jqc/designpattern/ASM/T1.class"));
        cr.accept(cp, 0);
    }
}
