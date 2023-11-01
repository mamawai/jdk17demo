package com.example.demo.algorithm.B;

import java.util.Arrays;
import java.util.Scanner;

public class 阿里巴巴找黄金宝箱五 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] ints = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int window = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(ints,window));
    }

    private static int getResult(int[] ints, int window) {
        int ans = -Integer.MAX_VALUE;
        for (int i = 0 ; i < ints.length -window + 1;i++) {
            int sum = 0;
            for (int j = i; j < i+window;j++) {
                sum+=ints[j];
            }
            if (sum > ans) ans = sum;
        }
        return ans;
    }
}
