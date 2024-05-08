package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.Arrays;

public class 拆炸弹 {
    public static void main(String[] args) {
        int[] decrypt = new Solution1652().decrypt(new int[]{2,4,9,3}, -2);
        System.out.println("");
    }
}

class Solution1652 {
    public int[] decrypt(int[] code, int k) {
        int[] ans = new int[code.length];
        if (k == 0) {
            Arrays.fill(code, 0);
            return code;
        }
        int[] doubleCode = new int[code.length * 2];
        for (int i = 0, j = code.length; j < doubleCode.length; i++, j++) {
            doubleCode[i] = code[i];
            doubleCode[j] = code[i];
        }
        int total = 0;
        if (k > 0) {
            for (int i = 1; i <= k; i++) {
                total += doubleCode[i];
            }
            ans[0] = total;
            for (int i = 1; i < code.length; i++) {
                ans[i] = total - doubleCode[i] + doubleCode[i + k];
                total = ans[i];
            }
        } else {
            for (int i = code.length - 1; i >= code.length + k; i--) {
                total += doubleCode[i];
            }
            ans[0] = total;

            for (int i = code.length + 1; i < doubleCode.length; i++) {
                ans[i - code.length] = total + doubleCode[i - 1] - doubleCode[i + k - 1];
                total = ans[i - code.length];
            }
        }
        return ans;
    }
}