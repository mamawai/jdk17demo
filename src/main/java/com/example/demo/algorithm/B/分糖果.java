package com.example.demo.algorithm.B;

import java.util.Scanner;

import java.util.Scanner;

/**
 * OD166
 */
public class 分糖果 {
    public static void main(String[] args) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        long candies = sc.nextLong();
        System.out.println(getResult(candies,count));
    }

    private static int getResult(long candies, int count) {
        int[] ans = {Integer.MAX_VALUE};

        // 15 14 7 6 3 2 1
        // 15 16 8 4 2 1

        // 5 4 2 1
        // 5 4 3

        dfs(ans, candies, count);

        return ans[0];
    }

    // 要拦截 1或2 的情况 否则candies会+1无限循环
    private static void dfs(int[] ans, long candies, int count) {
        if (candies == 1) {
            ans[0] = Math.min(ans[0], count);
            return;
        }

        if (candies % 2 == 0) {
            dfs(ans, candies / 2, count+1);
        } else {
            dfs(ans, candies-1, count+1);
            dfs(ans, candies+1, count+1);
        }
    }

}
