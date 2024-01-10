package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

public class Testprint {
    public static void main(String[] args) {
        ArrayList<List<String>> content = new ArrayList<>();
        ArrayList<String> e = new ArrayList<>();
        e.add("1");
        e.add("101");
        e.add("zhangsan");
        content.add(e);
        PrintTable printTable = new PrintTable(content);
        printTable.printTable("|");
    }
}
