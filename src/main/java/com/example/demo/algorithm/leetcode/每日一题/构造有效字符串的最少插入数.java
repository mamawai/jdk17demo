package com.example.demo.algorithm.leetcode.每日一题;

public class 构造有效字符串的最少插入数 {
    public static void main(String[] args) {
        int i = new SolutionB2645().addMinimum("cbcabc");
        System.out.println(i);
    }
}
/**
 * 题解又是动态规划
 */
class Solution2645 {
    public int addMinimum(String word) {
        char[] charArray = word.toCharArray();
        char before = ' ';
        int ans = 0;
        for (char c : charArray) {
            if (before == ' ') {
                if (c == 'b') {
                    ans++;
                } else if (c == 'c') {
                    ans+=2;
                }
            } else {
                if (c - before == -1 || c - before == 2) {
                    // ba cb || ac
                    ans++;
                }
                if (c == before) {
                    ans+=2;
                }
            }
            before = c;
        }
        if (before == 'a') {
            ans+=2;
        }
        if (before == 'b') {
            ans++;
        }
        return ans;
    }
}

class SolutionB2645 {
    public int addMinimum(String word) {
        int n = word.length();
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 2;
            if (i > 1 && word.charAt(i - 1) > word.charAt(i - 2)) {
                d[i] = d[i - 1] - 1;
            }
        }
        return d[n];
    }
}
// 计算组数
class SolutionC2645 {
    public int addMinimum(String word) {
        int n = word.length(), cnt = 1;
        for (int i = 1; i < n; i++) {
            if (word.charAt(i) <= word.charAt(i - 1)) {
                cnt++;
            }
        }
        return cnt * 3 - n;
    }
}