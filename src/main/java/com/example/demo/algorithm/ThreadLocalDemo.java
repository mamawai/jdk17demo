package com.example.demo.algorithm;

public class ThreadLocalDemo {
    public static ThreadLocal<Command> localVar = new ThreadLocal<>();

    public static ThreadLocal<Integer> forVisit = new ThreadLocal<>();

    public static void setLocalVar(Command cmd) {
        localVar.set(cmd);
    }

    public static Command getLocalVar() {
        return localVar.get();
    }

    public static void removeLocalVar() {
        localVar.remove();
    }

    public static void setForVisit(Integer integer) {
        forVisit.set(integer);
    }

    public static Integer getForVisit() {
        return forVisit.get();
    }

    public static void removeForVisit() {
        forVisit.remove();
    }
}
