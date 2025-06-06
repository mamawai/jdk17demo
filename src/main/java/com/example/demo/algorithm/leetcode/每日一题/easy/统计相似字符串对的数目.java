package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.HashMap;
import java.util.Map;

public class 统计相似字符串对的数目 {
    public static void main(String[] args) {

    }
}

class Solution2506 {
    public int similarPairs(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            map.put(mask, map.getOrDefault(mask, 0) + 1);
        }
        int ans = 0;
        for (int mask : map.keySet()) {
            ans += map.get(mask) * (map.get(mask) - 1) / 2;
        }
        return ans;
    }
}
