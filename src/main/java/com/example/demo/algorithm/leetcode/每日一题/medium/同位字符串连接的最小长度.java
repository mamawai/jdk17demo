package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;

public class 同位字符串连接的最小长度 {
    public static void main(String[] args) {
        int i = new Solution3138().minAnagramLength("aabbbbbbabbbbbba");
        System.out.println(i);
    }
}

class Solution3138 {
    public int minAnagramLength(String s) {
        char[] sc = s.toCharArray();
        int n = sc.length;
        next:
        for (int k = 1; k <= n / 2; k++) {
            if (n % k > 0) {
                continue;
            }
            int[] cnt0 = new int[26];
            for (int j = 0; j < k; j++) {
                cnt0[sc[j] - 'a']++;
            }
            for (int i = k * 2; i <= n; i += k) {
                int[] cnt = new int[26];
                for (int j = i - k; j < i; j++) {
                    cnt[sc[j] - 'a']++;
                }
                if (!Arrays.equals(cnt, cnt0)) {
                    continue next;
                }
            }
            return k;
        }
        return n;
    }
}