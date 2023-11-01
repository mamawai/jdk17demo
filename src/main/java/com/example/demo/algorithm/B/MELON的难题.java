package com.example.demo.algorithm.B;

import java.util.*;

/**
 * OD278
 */
public class MELON的难题 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<Integer> collect = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toList();
        System.out.println(getResult(n,collect));
    }

    private static int getResult(int n, List<Integer> collect) {
        int totalWeight = collect.stream().reduce(0, Integer::sum);
        if (totalWeight % 2 != 0) return -1;
        int bag = totalWeight / 2;

        int[][] dp = new int[n+1][bag+1];

        for(int i = 0; i <= bag; i++) {
            dp[0][i] = n;
        }

        dp[0][0] = 0;

        for (int i = 1; i<=n ;i++) {
            int num = collect.get(i-1);
            for (int j =1; j <= bag ;j++) {
                if (num > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j],1 + dp[i-1][j-num]);
                }
            }
        }

        if (dp[n][bag] == n) {
            return -1;
        } else {
            return dp[n][bag];
        }


    }
}
