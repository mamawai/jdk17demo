package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 求出胜利玩家的数目 {
    public static void main(String[] args) {

    }
}

class Solution3238 {
    public int winningPlayerCount(int n, int[][] pick) {
        int ans = 0;
        int[][] arr =new int[n][10];
        int[] win = new int[n];
        for (int[] p : pick) {
            if (++arr[p[0]][p[1]] > p[0] && win[p[0]] == 0) {
                ans++;
                win[p[0]] = 1;
            }
        }
        return ans;
    }
}