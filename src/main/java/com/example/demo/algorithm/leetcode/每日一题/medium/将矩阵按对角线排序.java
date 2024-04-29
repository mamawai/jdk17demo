package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.PriorityQueue;
import java.util.Queue;

public class 将矩阵按对角线排序 {
    public static void main(String[] args) {

    }
}

class Solution1329 {
    public int[][] diagonalSort(int[][] mat) {
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        // 遍历第一行
        for (int i = 0; i < mat[0].length; i++) {
            int j = 0;
            int k = i;
            while (j < mat.length && k < mat[0].length) {
                queue.offer(mat[j][k]);
                j++;
                k++;
            }
            while (!queue.isEmpty()) {
                j--;
                k--;
                mat[j][k] = queue.poll();
            }
        }
        // 第一列
        for (int i = 1; i < mat.length; i++) {
            int j = i;
            int k = 0;
            while (j < mat.length && k < mat[0].length) {
                queue.offer(mat[j][k]);
                j++;
                k++;
            }
            while (!queue.isEmpty()) {
                j--;
                k--;
                mat[j][k] = queue.poll();
            }
        }
        return mat;
    }
}