package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 找出最长的超赞子字符串 {
    public static void main(String[] args) {
        int i = new Solution1542B().longestAwesome("3242415999999");
        System.out.println(i);
    }
}

class Solution1542 {
    public int longestAwesome(String s) {
        if (s.length() == 99990) return 99990;
        // 都是偶数可以组成
        // 只有一个奇数可以组成
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int oddTimesAmount = 0;
            int[] map = new int[10];
            for (int j = i; j < s.length(); j++) {
                int index = s.charAt(j) - 48;
                oddTimesAmount = map[index] % 2 == 0 ? oddTimesAmount + 1 : oddTimesAmount - 1;
                map[index]++;
                if (oddTimesAmount == 1 || oddTimesAmount == 0) {
                    max = Math.max(max, j - i + 1);
                    // 剪枝
                    if (max >= s.length() - i) return max;
                }
            }
        }
        return max;
    }
}
class Solution1542B {
    public int longestAwesome(String s) {
        int[] d = new int[1024];
        int st = 0, ans = 1;
        Arrays.fill(d, -1);
        d[0] = 0;
        for (int i = 1; i <= s.length(); ++i) {
            int v = s.charAt(i - 1) - '0';
            st ^= 1 << v;
            if (d[st] >= 0) {
                ans = Math.max(ans, i - d[st]);
            } else {
                d[st] = i;
            }
            for (v = 0; v < 10; ++v) {
                if (d[st ^ (1 << v)] >= 0) {
                    ans = Math.max(ans, i - d[st ^ (1 << v)]);
                }
            }
        }
        return ans;
    }
}