package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 数组列表中的最大距离 {
    public static void main(String[] args) {
        ArrayList<List<Integer>> arrays = new ArrayList<>();
        arrays.add(new ArrayList<>(List.of(-1, 1)));
        arrays.add(new ArrayList<>(List.of(-3, 1, 4)));
        arrays.add(new ArrayList<>(List.of(-2, -1,0, 1)));

        int i = new Solution624().maxDistance(arrays);
        System.out.println(i);
    }
}

class Solution624 {
    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        int mi = arrays.get(0).get(0);
        int mx = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < arrays.size(); ++i) {
            var arr = arrays.get(i);
            int a = Math.abs(arr.get(0) - mx);
            int b = Math.abs(arr.get(arr.size() - 1) - mi);
            ans = Math.max(ans, Math.max(a, b));
            mi = Math.min(mi, arr.get(0));
            mx = Math.max(mx, arr.get(arr.size() - 1));
        }
        return ans;
    }
}