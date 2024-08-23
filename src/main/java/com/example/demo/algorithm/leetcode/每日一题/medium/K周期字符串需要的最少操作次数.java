package com.example.demo.algorithm.leetcode.每日一题.medium;

public class K周期字符串需要的最少操作次数 {
    public static void main(String[] args) {
        int i = new Solution3137().minimumOperationsToMakeKPeriodic("caccca", 2);
        System.out.println(i);
    }
}

class Solution3137 {
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        int maxTimes = 0;
        LineNode origin = new LineNode();
        for (int i = 0; i < word.length();) {
            LineNode root = origin;
            int cnt = k;
            int idx;
            while (cnt-- > 0) {
                idx = word.charAt(i++) - 'a';
                if (root.children[idx] == null) {
                    root.children[idx] = new LineNode();
                }
                root = root.children[idx];
            }
            maxTimes = Math.max(maxTimes, ++root.times);
        }
        return word.length() / k - maxTimes;
    }
}

class LineNode {
    int times;
    LineNode[] children;
    public LineNode() {
        this.times = 0;
        this.children = new LineNode[26];
    }
}