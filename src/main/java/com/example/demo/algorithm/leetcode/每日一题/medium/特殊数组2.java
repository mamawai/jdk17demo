package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.List;

public class 特殊数组2 {
    public static void main(String[] args) {
        boolean[] arraySpecial = new Solution3152().isArraySpecial(new int[]{4, 3, 1, 6}, new int[][]{{0, 2}, {2, 3}});

    }
}

class Solution3152 {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int[] s = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            s[i] = s[i - 1] + ((nums[i - 1] & 1) == (nums[i] & 1) ? 1 : 0);
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            ans[i] = s[q[0]] == s[q[1]];
        }
        return ans;
    }
}