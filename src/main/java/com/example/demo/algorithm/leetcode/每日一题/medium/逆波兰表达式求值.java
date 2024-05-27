package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class 逆波兰表达式求值 {
    public static void main(String[] args) {
        Solution150 solution150B = new Solution150();
        int i = solution150B.evalRPN(new String[]{"2", "1", "+", "3", "*"});
        System.out.println(i);
    }
}

class Solution150 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.poll() +  stack.poll());
                    break;
                case "-":
                    Integer polled = stack.poll();
                    Integer polled1 = stack.poll();
                    stack.push(polled1 - polled);
                    break;
                case "*" :
                    stack.push(stack.poll() * stack.poll());
                    break;
                case "/":
                    Integer polled2 = stack.poll();
                    Integer polled3 = stack.poll();
                    stack.push(polled3 / polled2);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.poll();
    }
}