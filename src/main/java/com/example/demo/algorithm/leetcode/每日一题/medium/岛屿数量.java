package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayDeque;
import java.util.Queue;

public class 岛屿数量 {
    public static void main(String[] args) {
        int i = new Solution200().numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}});
        System.out.println(i);
    }
}

class Solution200 {
    public int numIslands(char[][] grid) {
        int ans = 0;
        // bfs找陆地
        int[][] offsets = new int[][]{{0, 1},{1, 0},{0, -1},{-1, 0}};
        for (int i = 0; i < grid.length; i++) {
            char[] chars = grid[i];
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                // 如果是陆地并且没有被访问过 bfs
                if (c == '1') {
                    ans++;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i,j});
                    while (!queue.isEmpty()) {
                        int[] polled = queue.poll();
                        grid[polled[0]][polled[1]] = 0;
                        for (int[] offset : offsets) {
                           int newX = polled[0] + offset[0];
                           int newY = polled[1] + offset[1];
                           if (newX >= 0 && newY >= 0 && newX < grid.length && newY < grid[0].length) {
                               if (grid[newX][newY] == '1') {
                                   queue.offer(new int[]{newX, newY});
                                   grid[newX][newY] = 0;
                               }
                           }
                        }
                    }
                }
            }
        }
        return ans;
    }
}