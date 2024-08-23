package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 到达第K级台阶的方案数 {
    public static void main(String[] args) {
        int i = new Solution3154().waysToReachStair(131064);
        System.out.println(i);
    }
}

class Solution3154 {
    Map<Long, Integer> memo;
    int k;
    public int waysToReachStair(int k) {
        memo = new HashMap<>();
        this.k = k;
        // 往下走一格 或者往上走2^jump格子
        return dfs(1, 0, 1, 0);
    }

    private int dfs(int stage, int downFlag, int jump, int cnt) {
        if (stage > k + 1) return 0;
        // 状态压缩
        long key = (long) stage << 32 | (long) cnt << 1 | downFlag;
        if (memo.containsKey(key)) return memo.get(key);
        int res = 0;
        if (stage == k) ++res;
        if (downFlag == 0) {
            // 向下走一格
            res += dfs(stage - 1, 1, jump, cnt);
        }
        // 向上走2^jump格子
        res += dfs(stage + jump, 0, jump << 1, cnt + 1);
        memo.put(key, res);
        return res;
    }
}