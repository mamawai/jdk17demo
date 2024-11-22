package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 图片平滑器 {
    public static void main(String[] args) {
        int[][] ints = new Solution661().imageSmoother(new int[][]{
                {1,1,1},
                {1,0,1},
                {1,1,1}
        });
    }
}

class Solution661 {
    public int[][] imageSmoother(int[][] img) {
        int[][] offsets = new int[][]{{-1,-1}, {-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        int[][] ans = new int[img.length][img[0].length];
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                int sum = img[i][j];
                int cnt = 0;
                for (int[] offset : offsets) {
                    int newX = i + offset[0];
                    int newY = j + offset[1];
                    if (newX >= 0 && newX < img.length && newY >= 0 && newY < img[0].length) {
                        sum += img[newX][newY];
                        cnt++;
                    }
                }
                ans[i][j] = sum / (cnt + 1);
            }
        }
        return ans;
    }
}