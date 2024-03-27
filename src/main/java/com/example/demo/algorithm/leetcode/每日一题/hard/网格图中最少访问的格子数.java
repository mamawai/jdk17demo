package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 网格图中最少访问的格子数 {
    public static void main(String[] args) {
        int i = new Solution2617E().minimumVisitedCells(new int[][]{{3, 4, 2, 1}, {4, 2, 3, 1}, {2, 1, 0, 0}, {2, 4, 0, 0}});
        System.out.println(i);
    }
}

class Solution2617 {
    public int minimumVisitedCells(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        int dfsed = dfs(0, 0, grid, memo);
        return dfsed == grid.length + grid[0].length ? -1 : dfsed;
    }

    private int dfs(int i, int j, int[][] grid, int[][] memo) {
        if (memo[i][j] != -1) return memo[i][j];
        int moves = grid[i][j];
        if (moves == 0 && !(i == grid.length-1 && j == grid[0].length - 1)) return grid.length + grid[0].length;
        if (i == grid.length-1 && j == grid[0].length - 1) return 1;
        int minTakes = grid.length + grid[0].length;
        // 往下走
        for (int k = Math.min(moves + i, grid.length - 1); k >= i + 1; k--) {
            minTakes = Math.min(minTakes, dfs(k, j, grid, memo) + 1);
        }
        // 往右走
        for (int k = Math.min(moves + j, grid[0].length - 1); k >= j + 1; k--) {
            minTakes = Math.min(minTakes, dfs(i, k, grid, memo) + 1);
        }

        return memo[i][j] = minTakes;
    }
}

class Solution2617B {
    public int minimumVisitedCells(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1 && j == grid[0].length - 1) {
                    dp[i][j] = 1;
                } else {
                    int moves = grid[i][j];
                    int minTakes = grid.length + grid[0].length;
                    // 往下走
                    for (int k = Math.min(moves + i, grid.length - 1); k >= i + 1; k--) {
                        minTakes = Math.min(minTakes, dp[k][j] + 1);
                    }
                    // 往右走
                    for (int k = Math.min(moves + j, grid[0].length - 1); k >= j + 1; k--) {
                        minTakes = Math.min(minTakes, dp[i][k] + 1);
                    }
                    dp[i][j] = minTakes;
                }
            }
        }
        return dp[0][0] == grid.length + grid[0].length ? -1 : dp[0][0];
    }
}

class Solution2617C {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int mn = 0;
        List<int[]>[] colStacks = new ArrayList[n]; // 每列的单调栈，为了能二分用 ArrayList
        Arrays.setAll(colStacks, i -> new ArrayList<int[]>());
        List<int[]> rowSt = new ArrayList<>(); // 行单调栈
        for (int i = m - 1; i >= 0; i--) {
            rowSt.clear();
            for (int j = n - 1; j >= 0; j--) {
                int g = grid[i][j];
                List<int[]> colSt = colStacks[j];
                mn = i < m - 1 || j < n - 1 ? Integer.MAX_VALUE : 1;
                if (g > 0) { // 可以向右/向下跳
                    // 在单调栈上二分查找最优转移来源
                    int k = search(rowSt, j + g);
                    if (k < rowSt.size()) {
                        mn = rowSt.get(k)[0] + 1;
                    }
                    k = search(colSt, i + g);
                    if (k < colSt.size()) {
                        mn = Math.min(mn, colSt.get(k)[0] + 1);
                    }
                }
                if (mn < Integer.MAX_VALUE) {
                    // 插入单调栈
                    while (!rowSt.isEmpty() && mn <= rowSt.get(rowSt.size() - 1)[0]) {
                        rowSt.remove(rowSt.size() - 1);
                    }
                    rowSt.add(new int[]{mn, j});
                    while (!colSt.isEmpty() && mn <= colSt.get(colSt.size() - 1)[0]) {
                        colSt.remove(colSt.size() - 1);
                    }
                    colSt.add(new int[]{mn, i});
                }
            }
        }
        return mn < Integer.MAX_VALUE ? mn : -1; // 最后一个算出的 mn 就是 f[0][0]
    }

    // 开区间二分，见 https://www.bilibili.com/video/BV1AP41137w7/
    private int search(List<int[]> st, int target) {
        int left = -1, right = st.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            int mid = left + (right - left) / 2;
            if (st.get(mid)[1] <= target) {
                right = mid; // 范围缩小到 (left, mid)
            } else {
                left = mid; // 范围缩小到 (mid, right)
            }
        }
        return right;
    }
}
class Solution2617D {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        SegTree tree1 = new SegTree(m * n);
        SegTree tree2 = new SegTree(m * n);
        tree1.update(0, 0, 1);
        tree2.update(0, 0, 1);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int step = Math.min(tree1.query(j * m + i), tree2.query(i * n + j));
                if (step == SegTree.INF) continue;
                // 向下 (i+1, j) ~ (i+grid[i][j], j) 区间更新, 注意i+grid[i][j]不能越界
                tree1.update(j * m + i + 1, j * m + Math.min(i + grid[i][j], m - 1), step + 1);
                // 向右 (i, j+1) ~ (i, j+grid[i][j]) 区间更新, 注意j+grid[i][j]不能越界
                tree2.update(i * n + j + 1, i * n + Math.min(j + grid[i][j], n - 1), step + 1);
            }
        }
        int ans = Math.min(tree1.query(m * n - 1), tree2.query(m * n - 1));
        return ans == SegTree.INF ? -1 : ans;
    }
}

/**
 * 线段树 维护最小值
 * 支持区间更新、单点查询
 *
 * 由于单点查询操作查询到的都是叶子节点，所以无需上推
 * 相较于区间查询的线段树可以简化一点，只要维护懒标记
 */
class SegTree {
    static final int INF = 0x3f3f3f3f;
    final int n;
    final int[] lazy;

    SegTree(int n) {
        this.n = n;
        lazy = new int[n << 2];
        Arrays.fill(lazy, INF);
    }

    void update(int i, int j, int val) {
        if (i > j) return;
        update(i, j, val, 0, n - 1, 1);
    }

    void update(int i, int j, int val, int st, int ed, int rt) {
        if (i <= st && ed <= j) {
            lazy[rt] = Math.min(lazy[rt], val);
            return;
        }
        pushDown(rt);
        int mid = st + ed >>> 1;
        if (i <= mid) update(i, j, val, st, mid, rt << 1);
        if (j > mid) update(i, j, val, mid + 1, ed, rt << 1 | 1);
    }

    int query(int i) {
        return query(i, 0, n - 1, 1);
    }

    int query(int i, int st, int ed, int rt) {
        if (st == ed) return lazy[rt];
        pushDown(rt);
        int mid = st + ed >>> 1;
        if (i <= mid) return query(i, st, mid, rt << 1);
        return query(i, mid + 1, ed, rt << 1 | 1);
    }

    void pushDown(int rt) {
        if (lazy[rt] == INF) return;
        lazy[rt << 1] = Math.min(lazy[rt << 1], lazy[rt]);
        lazy[rt << 1 | 1] = Math.min(lazy[rt << 1 | 1], lazy[rt]);
        lazy[rt] = INF;
    }
}


class Solution2617E {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int f = 0;
        PriorityQueue<int[]>[] colHeaps = new PriorityQueue[n]; // 每一列的最小堆
        Arrays.setAll(colHeaps, i -> new PriorityQueue<int[]>((a, b) -> a[0] - b[0]));
        PriorityQueue<int[]> rowH = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 行最小堆
        for (int i = 0; i < m; i++) {
            rowH.clear();
            for (int j = 0; j < n; j++) {
                while (!rowH.isEmpty() && rowH.peek()[1] < j) { // 无法到达第 j 列
                    rowH.poll(); // 弹出无用数据
                }
                PriorityQueue<int[]> colH = colHeaps[j];
                while (!colH.isEmpty() && colH.peek()[1] < i) { // 无法到达第 i 行
                    colH.poll(); // 弹出无用数据
                }

                f = i > 0 || j > 0 ? Integer.MAX_VALUE : 1; // 起点算 1 个格子
                if (!rowH.isEmpty()) {
                    f = rowH.peek()[0] + 1; // 从左边跳过来
                }
                if (!colH.isEmpty()) {
                    f = Math.min(f, colH.peek()[0] + 1); // 从上边跳过来
                }

                int g = grid[i][j];
                if (g > 0 && f < Integer.MAX_VALUE) {
                    rowH.offer(new int[]{f, g + j}); // 经过的格子数，向右最远能到达的列号
                    colH.offer(new int[]{f, g + i}); // 经过的格子数，向下最远能到达的行号
                }
            }
        }
        return f < Integer.MAX_VALUE ? f : -1; // 此时的 f 是在 (m-1, n-1) 处算出来的
    }
}