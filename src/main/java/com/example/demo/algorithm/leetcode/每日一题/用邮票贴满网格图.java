package com.example.demo.algorithm.leetcode.每日一题;

import java.util.*;

public class 用邮票贴满网格图 {
    public static void main(String[] args) {
        boolean b = new Solution2132().possibleToStamp(new int[][]{{0,0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0 ,0, 1}, {0, 0, 0 ,1, 1}}, 2, 2);
        System.out.println(b);

    }
}

class Solution2132 {
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
                int m = grid.length;
                int n = grid[0].length;

                // 1. 计算 grid 的二维前缀和
                int[][] s = new int[m + 1][n + 1];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
                    }
                }

                // 2. 计算二维差分
                // 为方便第 3 步的计算，在 d 数组的最上面和最左边各加了一行（列），所以下标要 +1
                int[][] d = new int[m + 2][n + 2];
                for (int i2 = stampHeight; i2 <= m; i2++) {
                    for (int j2 = stampWidth; j2 <= n; j2++) {
                        int i1 = i2 - stampHeight + 1;
                        int j1 = j2 - stampWidth + 1;
                        // 这个if是判断当前贴的邮票范围内是否数组都为0 即可以贴邮票
                        // 那么就将d(二位差分数组)对应位置的左上角和右下角都加一右上角和左下角都减一，
                        // 等到第三步的时候再还原差分数组
                        if (s[i2][j2] - s[i2][j1 - 1] - s[i1 - 1][j2] + s[i1 - 1][j1 - 1] == 0) {
                            d[i1][j1]++;
                            d[i1][j2 + 1]--;
                            d[i2 + 1][j1]--;
                            d[i2 + 1][j2 + 1]++;
                        }
                    }
                }

                // 1 1-1-1
                // 1 0-1 0
                //-1-1 1 1
                //-1 0 1 0



                // 1 2 1-1
                // 2 3 1 0
                // 1 1 0 1
                //-1 0 1 0
                // 3. 还原二维差分矩阵对应的计数矩阵（原地计算） | 就是求前缀和
                // 还原后的矩阵把最外面一圈的数字剔除（因为这个d矩阵之前加了2）就是每个格子在一次次从左上角顶点贴邮票，邮票覆盖的次数
                // 如果还原后的矩阵存在 0 grid也是0 那么说明该位置是空的但没有被覆盖
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        d[i + 1][j + 1] += d[i + 1][j] + d[i][j + 1] - d[i][j];
                        if (grid[i][j] == 0 && d[i + 1][j + 1] == 0) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
class MatrixSum {
    private final int[][] sum;

    public MatrixSum(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        sum = new int[m + 1][n + 1]; // 注意：如果 matrix[i][j] 范围很大，需要使用 long
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];
            }
        }
    }

    // 返回左上角在 (r1,c1) 右下角在 (r2-1,c2-1) 的子矩阵元素和（类似前缀和的左闭右开）
    public int query(int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r2][c1] - sum[r1][c2] + sum[r1][c1];
    }
}