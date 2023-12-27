package com.example.demo.algorithm.leetcode;

import java.util.*;

public class 最长有效括号 {
    public static void main(String[] args) {
        int i = new Solution32().longestValidParentheses("()((())");
        System.out.println(i);
    }
}

/**
 * 核心--用下标计算长度
 */
class Solution32 {
    public int longestValidParentheses(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);
        int maxAns = 0;
        for (int i = 0; i < s.length(); i++) {
            char charredAt = s.charAt(i);
            if (charredAt == ')') {
                stack.poll();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxAns = Math.max(maxAns, i - stack.peek());
                }
            } else {
                stack.push(i);
            }
        }
        return maxAns;
    }
}
