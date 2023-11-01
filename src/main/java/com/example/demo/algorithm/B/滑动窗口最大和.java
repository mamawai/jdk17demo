package com.example.demo.algorithm.B;

import java.util.Arrays;
import java.util.Scanner;

/**
 * OD248
 */
public class 滑动窗口最大和 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String s = sc.nextLine();
        int m = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(n,s,m));
    }

    private static int getResult(int n, String s, int m) {
        int maxTotal = 0;
        Integer[] nums = Arrays.stream(s.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        if (m == n) {
            return Arrays.stream(nums).reduce(0,Integer::sum);
        }

        for (int i = 0; i < n - m + 1; i++) {
            int current = -Integer.MAX_VALUE;
            for (int j = i; j < m + i; j++) {
                current += nums[j];
            }
            maxTotal = Math.max(maxTotal, current);

        }
        return maxTotal;
    }
}
