package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class Powxn {
    public static void main(String[] args) {
        int i = new SolutionAAA().maximumScore(new int[]{1, 4, 3, 7, 4, 5}, 3);
        System.out.println(i);
    }
}

class Solution50 {
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }
}

class Solution50B {
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }
}

class SolutionAAA {
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!st.isEmpty() && x <= nums[st.peek()]) {
                st.pop();
            }
            left[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        int[] right = new int[n];
        st.clear();
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            while (!st.isEmpty() && x <= nums[st.peek()]) {
                st.pop();
            }
            right[i] = st.isEmpty() ? n : st.peek();
            st.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int h = nums[i];
            int l = left[i];
            int r = right[i];
            if (l < k && k < r) { // 相比 84 题多了个 if 判断
                ans = Math.max(ans, h * (r - l - 1));
            }
        }
        return ans;
    }
}