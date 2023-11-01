package com.example.demo.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class 凑11块 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] coins = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int total = Integer.parseInt(sc.nextLine());
        System.out.println(getResult2(coins, total));
    }

    private static int getResult(int[] coins, int total) {

        int[] dp = new int[total + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= total; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[total];
    }

    private static  int getResult2(int[] coins, int total) {
        int[][] dp = new int[coins.length + 1][total + 1];

        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j >= coins[i - 1]) {
                    // 注意 这里是无限个135 所以 dp[i][j - coins[i - 1]] 第一个i不用减一 对照看 代表团坐车->他这里用一次就没有了所以要减一
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length][total];
    }
}
