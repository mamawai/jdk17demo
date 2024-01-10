package com.example.demo.algorithm.leetcode.每日一题;

import java.util.HashMap;
import java.util.Map;

public class 回旋镖的数量 {
    public static void main(String[] args) {
        int i = new Solution447().numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}});
        System.out.println(i);
    }
}

class Solution447 {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] p : points) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int[] q : points) {
                int dis = (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
                cnt.put(dis, cnt.getOrDefault(dis, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
                int m = entry.getValue();
                ans += m * (m - 1);
            }
        }
        return ans;
    }
}