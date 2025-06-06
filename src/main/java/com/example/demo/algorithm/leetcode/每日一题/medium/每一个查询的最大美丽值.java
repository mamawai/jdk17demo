package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 每一个查询的最大美丽值 {
    public static void main(String[] args) {
        int[] ints = new Solution2070().maximumBeauty(new int[][]{{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}}, new int[]{1, 2, 3, 4, 5,6});
        System.out.println(Arrays.toString(ints));
    }
}

class Solution2070 {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        Integer[] idx = new Integer[queries.length];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (i, j) -> queries[i] - queries[j]);

        int[] ans = new int[queries.length];
        int maxBeauty = 0;
        int j = 0;
        for (int i : idx) {
            int q = queries[i];
            // 增量地遍历满足 queries[i-1] < price <= queries[i] 的物品
            while (j < items.length && items[j][0] <= q) {
                maxBeauty = Math.max(maxBeauty, items[j][1]);
                j++;
            }
            ans[i] = maxBeauty;
        }
        return ans;
    }
}