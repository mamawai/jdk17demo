package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.HashSet;

public class 最小化字符串长度 {
    public static void main(String[] args) {

    }
}

class Solution2716 {
    public int minimizedStringLength(String s) {
        HashSet<Character> set = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            set.add(s.charAt(i));
        }
        return set.size();
    }
}