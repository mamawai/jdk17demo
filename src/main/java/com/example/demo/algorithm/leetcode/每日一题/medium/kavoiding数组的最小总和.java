package com.example.demo.algorithm.leetcode.每日一题.medium;

public class kavoiding数组的最小总和 {
    public static void main(String[] args) {
        int i = new Solution2829().minimumSum(27,49);
        System.out.println(i);
    }
}

class Solution2829 {
    public int minimumSum(int n, int k) {
        int[] used = new int[n+k+1];
        int ans = 0;
        for (int i = 1; n > 0 ; i++) {
            if (used[i] == 0) {
                if (k - i > 0) used[k - i] = -1;
                ans += i;
                n--;
            }
        }
        return ans;
    }
}