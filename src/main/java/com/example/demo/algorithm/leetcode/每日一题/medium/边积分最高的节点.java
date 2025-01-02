package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 边积分最高的节点 {
    public static void main(String[] args) {
        int i = new Solution2374().edgeScore(new int[]{1, 0, 0, 0, 0, 7, 7, 5});
        System.out.println(i);
    }
}

class Solution2374 {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] arr = new long[n];
        long[] maxPair = new long[2];
        for (int i = 0; i < n; i++) {
            int pointed = edges[i];
            long tmp = (arr[pointed] += i);
            if (maxPair[1] < tmp) {
                maxPair[1] = tmp;
                maxPair[0] = pointed;
            } else if (maxPair[1] == tmp) maxPair[0] = Math.min(maxPair[0], pointed);
        }
        return (int) maxPair[0];
    }
}