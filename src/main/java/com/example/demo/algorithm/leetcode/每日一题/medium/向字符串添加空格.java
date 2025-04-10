package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.StringJoiner;

public class 向字符串添加空格 {
}



class Solution2109 {
    public String addSpaces(String s, int[] spaces) {
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < spaces.length; i++) {
            int space = spaces[i];
            String sub = s.substring(i == 0 ? 0 : spaces[i - 1], space);
            sj.add(sub);
        }
        return sj.add(s.substring(spaces[spaces.length - 1])).toString();
    }
}