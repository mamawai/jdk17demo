package com.example.demo.algorithm.B;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class 最大括号深度 {
    // 输入获取
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    // 算法入口
    public static int getResult(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        LinkedList<Character> stack = new LinkedList<>();

        int maxDepth = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (stack.size() > 0 && map.get(c) == stack.getLast()) {
                stack.removeLast();
            } else {
                stack.add(c);
                maxDepth = Math.max(maxDepth, stack.size());
            }
        }

        // 他这里是判断当前括号如果能跟最后一个括号抵消那么移除最后一个括号 反之就正常加进去
        // 正常情况下最后的stack size应该为0 反之则说明括号不符合规范返回0
        if (stack.size() > 0) return 0;
        return maxDepth;
    }
}
