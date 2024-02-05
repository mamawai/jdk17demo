package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 重复的DNA序列 {
    public static void main(String[] args) {
        List<String> strings = new Solution187().findRepeatedDnaSequences("AAAAAAAAAAA");
    }
}

class Solution187 {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length() - 9; i++) {
            String truncated = s.substring(i, i + 10);
            map.put(truncated, map.getOrDefault(truncated, 0) + 1);
        }
        return map.entrySet().stream().filter(se -> se.getValue() > 1).map(Map.Entry::getKey).toList();
    }
}
