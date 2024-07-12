package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.TreeMap;

public class 最小化曼哈顿距离 {
    public static void main(String[] args) {

    }
}

class Solution3102 {
    public int minimumDistance(int[][] points) {
        TreeMap<Integer, Integer> xs = new TreeMap<>();
        TreeMap<Integer, Integer> ys = new TreeMap<>();
        for (int[] p : points) {
            xs.merge(p[0] + p[1], 1, Integer::sum);
            ys.merge(p[1] - p[0], 1, Integer::sum);
        }

        int ans = Integer.MAX_VALUE;
        for (int[] p : points) {
            int x = p[0] + p[1];
            int y = p[1] - p[0];
            if (xs.get(x) == 1) xs.remove(x);// 移除后为 0，直接移除 x
            else xs.merge(x, -1, Integer::sum); // 移除一个 x
            if (ys.get(y) == 1) ys.remove(y);// 移除后为 0，直接移除 y
            else ys.merge(y, -1, Integer::sum); // 移除一个 y

            int dx = xs.lastKey() - xs.firstKey();
            int dy = ys.lastKey() - ys.firstKey();
            ans = Math.min(ans, Math.max(dx, dy));

            // 恢复现场
            xs.merge(x, 1, Integer::sum);
            ys.merge(y, 1, Integer::sum);
        }
        return ans;
    }
}