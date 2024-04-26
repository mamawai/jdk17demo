package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 从双倍数组中还原原数组 {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(3);
        integers.add(1);
        integers.add(2);
        Integer[] array = integers.toArray(value -> new Integer[0]);
        System.out.println();
    }
}

class Solution2007 {
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 > 0) return new int[]{};
        int[] ans = new int[changed.length / 2];
        Arrays.sort(changed);
        Map<Integer, Integer> map = new HashMap<>();
        for (int c : changed) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int index = 0;
        for (int c : changed) {
            Integer one = map.get(c);
            if (one == 0) continue;
            map.put(c, one - 1);
            ans[index++] = c;
            Integer two = map.get(2 * c);
            if (two == null || two == 0) return new int[]{};
            map.put(2 * c, two - 1);
        }
        return ans;
    }
}