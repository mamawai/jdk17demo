package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 分割字符频率相等的最少子字符串 {
    public static void main(String[] args) {
        int i = new Solution3144().minimumSubstringsInPartition("abbcceeddf");
        System.out.println(i);
    }
}

class Solution3144 {
    public int minimumSubstringsInPartition(String s) {
        char[] S = s.toCharArray();
        int n = S.length;
        int[] memo = new int[n];
        return dfs(n - 1, S, memo);
    }

    private int dfs(int i, char[] s, int[] memo) {
        // 从右向左dfs
        if (i < 0) return 0;
        if (memo[i] > 0) return memo[i];
        int res = Integer.MAX_VALUE;
        int[] cnt = new int[26];
        int k = 0, maxCnt = 0;
        for (int j = i; j >= 0; j--) {
            // 计算字母种类
            k += cnt[s[j] - 'a']++ == 0 ? 1 : 0;
            // 更新最大出现次数
            maxCnt = Math.max(maxCnt, cnt[s[j] - 'a']);
            // 满足此条件说明可以分割成子串、也可以不分割继续for循环
            if (i - j + 1 == k * maxCnt) {
                res = Math.min(res, dfs(j - 1, s, memo) + 1);
            }
        }
        memo[i] = res;
        return res;
    }
}

class Solution3144dt {
    public int minimumSubstringsInPartition(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cnt, 0);
            // 这里统计k和cnt的做法也很巧妙
            int k = 0;
            int maxCnt = 0;
            for (int j = i; j >= 0; j--) {
                k += cnt[s[j] - 'a']++ == 0 ? 1 : 0;
                maxCnt = Math.max(maxCnt, cnt[s[j] - 'a']);
                // 可以分割的条件 这个if判断是重点
                if (i - j + 1 == k * maxCnt) {
                    f[i + 1] = Math.min(f[i + 1], f[j] + 1);
                }
            }
        }
        return f[n];
    }
}