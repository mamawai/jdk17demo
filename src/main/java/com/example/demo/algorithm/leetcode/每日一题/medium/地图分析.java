package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

public class 地图分析 {
    public static void main(String[] args) {
        int i = new Solution1162().maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        System.out.println(i);
    }
}

class Solution1162 {
    public int maxDistance(int[][] grid) {
        int ans = 0;
        int[][] distance = new int[grid.length][grid[0].length];
        int[][] offsets = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < grid.length; i++) {
            int[] row = grid[i];
            for (int j = 0; j < row.length; j++) {
                // 只有为1的时候才去找
                if (grid[i][j] == 1) {
                    boolean[][] vis = new boolean[grid.length][grid[0].length];
                    vis[i][j] = true;
                    // bfs
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i,j});
                    int manhattanDis = 1;
                    while (!queue.isEmpty()) {
                        Queue<int[]> deque = new ArrayDeque<>();
                        while (!queue.isEmpty()) {
                            int[] polled = queue.poll();
                            for (int[] offset : offsets) {
                                int newX = offset[0] + polled[0];
                                int newY = offset[1] + polled[1];
                                // 判断新XY的范围以及是否访问过
                                if (!(newX < 0 || newY < 0 || newX >= grid.length || newY >= grid[0].length) && !vis[newX][newY]) {
                                    // 是否为0
                                    if (grid[newX][newY] == 0) {
                                        deque.offer(new int[]{newX, newY});
                                        vis[newX][newY] = true;
                                        distance[newX][newY] = distance[newX][newY] == 0 ? manhattanDis : Math.min(manhattanDis, distance[newX][newY]);
                                    } else {
                                        distance[newX][newY] = -1;
                                    }
                                }
                            }
                        }
                        manhattanDis++;
                        queue.addAll(deque);
                    }
                }
            }
        }
        for (int[] dis : distance) {
            for (int d : dis) {
                ans = Math.max(d, ans);
            }
        }
        return ans == 0 ? -1 : ans;
    }
}

class Solution1162B {

    public int maxDistance(int[][] grid) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        int m = grid.length, n = grid[0].length;
        // 先把所有的陆地都入队。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        // 从各个陆地开始，一圈一圈的遍历海洋，最后遍历到的海洋就是离陆地最远的海洋。
        boolean hasOcean = false;
        int[] point = null;
        while (!queue.isEmpty()) {
            point = queue.poll();
            int x = point[0], y = point[1];
            // 取出队列的元素，将其四周的海洋入队。
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) {
                    continue;
                }
                grid[newX][newY] = grid[x][y] + 1; // 这里我直接修改了原数组，因此就不需要额外的数组来标志是否访问
                hasOcean = true;
                queue.offer(new int[] {newX, newY});
            }
        }

        // 没有陆地或者没有海洋，返回-1。
        if (point == null || !hasOcean) {
            return -1;
        }

        // 返回最后一次遍历到的海洋的距离。
        return grid[point[0]][point[1]] - 1;
    }
}