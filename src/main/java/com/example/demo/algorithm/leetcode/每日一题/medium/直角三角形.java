package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 直角三角形 {
    public static void main(String[] args) {

    }
}

class Solution3128 {
    public long numberOfRightTriangles(int[][] grid) {
        long ans = 0;
        // 以每一个1作为直角的顶点来计算
        // 先遍历整个grid统计每行的1的个数
        int m = grid.length;
        int n = grid[0].length;
        int[] col = new int[n];
        List<Integer>[] rowList = new ArrayList[m];
        Arrays.setAll(rowList, e->new ArrayList<>());
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    col[j]++;
                    rowList[i].add(j);
                }
            }
        }
        for (List<Integer> eachRow : rowList) {
            if (eachRow.size() > 1) {
                for (int e : eachRow) {
                    ans += (long) (eachRow.size() - 1) * (col[e] - 1);
                }
            }
        }
        return ans;
    }
}

class Solution3128B {
    public long numberOfRightTriangles(int[][] grid) {
        long ans = 0;
        // 以每一个1作为直角的顶点来计算
        int m = grid.length;
        int n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        // 接着遍历整个grid
        for (int i = 0; i < m; i++) {
            if (row[i] - 1 == 0) continue;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (col[j] - 1 == 0) continue;
                    ans += (long) (row[i] - 1) * (col[j] - 1);
                }
            }
        }
        return ans;
    }
}