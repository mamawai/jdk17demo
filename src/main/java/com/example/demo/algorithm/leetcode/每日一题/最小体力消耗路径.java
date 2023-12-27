package com.example.demo.algorithm.leetcode.每日一题;

import java.util.*;

public class 最小体力消耗路径 {
    public static void main(String[] args) {
        int min = new Solution1631().minimumEffortPath(new int[][]{{1,2,2},{3,3,2},{5,3,5}});
        System.out.println(min);

    }
}

class Solution1631 {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length; // 行数
        int n = heights[0].length; // 列数

        int[][] efforts = new int[m][n]; // 保存到达每个格子的体力消耗
        for (int[] row : efforts) {
            Arrays.fill(row, Integer.MAX_VALUE); // 初始化为最大值
        }

        efforts[0][0] = 0; // 起点的体力消耗为0
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2])); // 优先队列，按体力消耗升序排列
        pq.offer(new int[]{0, 0, 0}); // 将起点加入优先队列

        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // 取出体力消耗最小的格子
            int x = curr[0]; // 当前格子的行坐标
            int y = curr[1]; // 当前格子的列坐标
            int currEffort = curr[2]; // 当前格子的体力消耗

            if (x == m - 1 && y == n - 1) { // 如果到达右下角格子
                return currEffort; // 返回当前的体力消耗
            }

            for (int[] dir : DIRECTIONS) { // 遍历当前格子的四个相邻格子
                int nx = x + dir[0]; // 相邻格子的行坐标
                int ny = y + dir[1]; // 相邻格子的列坐标

                if (nx >= 0 && nx < m && ny >= 0 && ny < n) { // 如果相邻格子合法
                    int newEffort = Math.max(currEffort, Math.abs(heights[x][y] - heights[nx][ny])); // 计算新的体力消耗

                    if (newEffort < efforts[nx][ny]) { // 如果新的体力消耗小于已知的体力消耗
                        efforts[nx][ny] = newEffort; // 更新到达相邻格子的体力消耗
                        pq.offer(new int[]{nx, ny, newEffort}); // 将相邻格子加入优先队列
                    }
                }
            }
        }

        return -1; // 如果无法到达右下角格子，返回-1
    }

    }