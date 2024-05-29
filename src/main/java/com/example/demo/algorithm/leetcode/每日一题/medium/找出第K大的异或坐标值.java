package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 找出第K大的异或坐标值 {
    public static void main(String[] args) {
        int i = new Solution1738().kthLargestValue(new int[][]{{10,9,5},{2,0,4},{1,0,9},{3,4,8}}, 10);
        System.out.println(i);
        // 10 3 6
    }
}

class Solution1738 {
    public int kthLargestValue(int[][] matrix, int k) {
        List<Integer> ans = new ArrayList<>();
        int[][] res = new int[matrix.length][matrix[0].length];
        res[0][0] = matrix[0][0];
        ans.add(res[0][0]);
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            for (int j = 1; j < matrix[i].length; j++) {
                if (i == 0) {
                    ans.add(res[i][j] = matrix[i][j] ^ res[i][j - 1]);
                } else {
                    rowSum = matrix[i][j] ^= matrix[i][j - 1];
                    res[i][j] = rowSum;
                    res[i][j] ^= res[i-1][j];
                    ans.add(res[i][j]);
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            ans.add(matrix[i][0] ^= matrix[i - 1][0]);
        }
        ans.sort((num1, num2) -> num2 - num1);
        return ans.get(k - 1);
    }
}

class Solution1738B {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                results.add(pre[i][j]);
            }
        }

        results.sort((num1, num2) -> num2 - num1);
        return results.get(k - 1);
    }
}