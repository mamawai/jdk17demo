package com.example.demo.algorithm.leetcode.每日一题;

public class 寻找峰值2 {
    public static void main(String[] args) {

    }
}
/**
 * 为了实现时间复杂度为nlogm或者mlogn，需要进行二分查找
 */
class Solution1901 {
    public int[] findPeakGrid(int[][] mat) {
        int l = 0;
        int r = mat.length - 1;
        if (r == 0) {
            return new int[]{0, findMaxPos(mat, 0)};
        }
        while (l <= r) {
            int mid = (l + r) / 2;
            int maxPos = findMaxPos(mat, mid);
            // 该位置上界越界
            if (mid == 0) {
                if (mat[0][maxPos] > mat[1][maxPos]) {
                    return new int[]{0, maxPos};
                } else {
                    l = 1;
                }
            }
            // 该位置下界越界
            else if (mid == mat.length - 1) {
                return new int[]{mat.length - 1, maxPos};
            } else {
                if (mat[mid][maxPos] > mat[mid - 1][maxPos] && mat[mid][maxPos] > mat[mid + 1][maxPos]) {
                    return new int[]{mid, maxPos};
                }
                // 如果比上面小说明上面存在峰值 反之下面存在峰值
                if (mat[mid][maxPos] < mat[mid - 1][maxPos]) {
                    r = mid - 1;
                } else if (mat[mid][maxPos] < mat[mid + 1][maxPos]){
                    l = mid + 1;
                }
            }
        }
        return new int[] {-1, -1};
    }

    public int findMaxPos(int[][] mat, int mid) {
        int min = -1;
        int minPos = -1;
        int[] ints = mat[mid];
        for (int j = 0; j < ints.length; j++) {
            int i = ints[j];
            if (i > min) {
                min = i;
                minPos = j;
            }
        }
        return minPos;
    }
}
