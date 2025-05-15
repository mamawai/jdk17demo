package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class 简化路径 {
    public static void main(String[] args) {

    }
}

class Solution71 {
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();
        String[] split = path.split("/");
        for (String s : split) {
            if (!s.isEmpty() && !"..".equals(s)) {
                if (".".equals(s)) continue;
                stack.push("/" + s);
            }
            else if ("..".equals(s)) stack.poll();
        }
        while (!stack.isEmpty()) sb.append(stack.pollLast());
        String ret = sb.toString();
        return ret.isEmpty() ? "/" :ret;
    }
}