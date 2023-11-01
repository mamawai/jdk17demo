package com.example.demo.algorithm.B;

import java.util.Arrays;
import java.util.Scanner;

/**
 * OD128
 */
public class 代表团坐车 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int capacity = sc.nextInt();
        System.out.println(getResult(s,capacity));
    }

    private static int getResult(String s, int capacity) {
        Integer[] members = Arrays.stream(s.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        int[][] dp = new int[members.length+1][capacity+1];
        dp[0][0] = 1;
        for (int i = 1; i<= members.length; i++) {
            int weight = members[i-1];
            for (int j = 0; j<= capacity; j++) {
                if (weight > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j]+dp[i-1][j-weight];
                }
            }
        }
        return dp[members.length][capacity];
    }
}
