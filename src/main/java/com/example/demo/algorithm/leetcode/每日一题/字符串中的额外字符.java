package com.example.demo.algorithm.leetcode.每日一题;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 字符串中的额外字符 {
    public static void main(String[] args) {
        int i = new Solution2707().minExtraChar("leetscode", new String[]{"leet", "code", "leetcode"});
        System.out.println(i);
    }
}

/**
 * 又是动态规划 不会 24/01/11
 */
class Solution2707 {
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> ss = new HashSet<>(Arrays.asList(dictionary));
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1] + 1;
            for (int j = 0; j < i; j++) {
                if (ss.contains(s.substring(j,i))) {
                    f[i] = Math.min(f[i], f[j]);
                }
            }
        }
        return f[n];
    }
}
