package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.LinkedList;
import java.util.Queue;

public class 腐烂的橘子 {
    public static void main(String[] args) {
        int i = new Solution994().orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
        System.out.println(i);
    }
}

class Solution994 {
    public int orangesRotting(int[][] grid) {
        int count = 0;
        int times = 0;
        Queue<int[]> rotten = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    rotten.offer(new int[]{i,j});
                }
                if (grid[i][j] == 1) count++;
            }
        }
        // bfs
        int[][] offsets = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        while (true) {
            Queue<int[]> newRotten = new LinkedList<>();
            while (!rotten.isEmpty()) {
                int[] ro = rotten.poll();
                for (int[] offset : offsets) {
                    int newX = ro[0] + offset[0];
                    int newY = ro[1] + offset[1];
                    if (newX >= 0 && newY >= 0 && newX < grid.length && newY < grid[0].length) {
                        if (grid[newX][newY] == 1) {
                            count--;
                            newRotten.offer(new int[]{newX, newY});
                            grid[newX][newY] = 2;
                        }
                    }
                }
            }
            rotten.addAll(newRotten);
            if (rotten.isEmpty()) break;
            times++;
        }
        if (count != 0) return -1;
        return times;
    }
}