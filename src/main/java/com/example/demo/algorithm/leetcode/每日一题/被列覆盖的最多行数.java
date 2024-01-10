package com.example.demo.algorithm.leetcode.每日一题;

public class 被列覆盖的最多行数 {
    public static void main(String[] args) {
        int i = new Solution2397().maximumRows(new int[][]{{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 0, 1}}, 2);
        System.out.println(i);
    }
}

/**
 * 通过2进制的位运算来将二维数组压缩成一维数组，是简单版的 -- 参加考试的最大学生数
 * 位运算来标识占位的状态 很巧妙
 */
class Solution2397 {
    public int maximumRows(int[][] matrix, int numSelect) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] mask = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                // 二维压缩成一维 即把每一行看作一个二进制数，压缩成十进制保存
                mask[i] += matrix[i][j] << (n - j - 1);
            }
        }
        int res = 0;
        int cur = 0;
        int limit = (1 << n);
        while (++cur < limit) {
            // 这里的while和if-continue是为了获取所有的numSelect的选择方案
            if (Integer.bitCount(cur) != numSelect) {
                continue;
            }
            int t = 0;
            for (int j = 0; j < m; j++) {
                // mask[j] & cur 两个数字的二进制都为1计算才为1 反之为0 ，再与mask[j]自己比较如果相等就说明cur能完全覆盖mask[j]中的1
                if ((mask[j] & cur) == mask[j]) {
                    ++t;
                }
            }
            res = Math.max(res, t);
        }
        return res;
    }
}