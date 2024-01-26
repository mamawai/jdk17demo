package com.example.demo.algorithm.leetcode.每日一题;

import java.util.LinkedList;
import java.util.List;

public class 删除字串后的字符串最小长度 {
    public static void main(String[] args) {

    }
}

class Solution2696 {
    public int minLength(String s) {
        LinkedList<String> stack = new LinkedList<>();
        for (String word : s.split("")) {
            if (stack.size() == 0) {
                stack.push(word);
            } else {
                if (word.equals("B") && stack.peek().equals("A")) {
                    stack.pop();
                } else if (word.equals("D") && stack.peek().equals("C")) {
                    stack.pop();
                } else {
                    stack.push(word);
                }
            }
        }
        return stack.size();
    }
}