package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.Arrays;

public class 猫和老鼠 {
    public static void main(String[] args) {
        int i = new Solution913().catMouseGame(
                new int[][]{
                        {2, 5},
                        {3},
                        {0, 4, 5},
                        {1, 4, 5},
                        {2, 3},
                        {0, 2, 3}
                }
        );
    }
}

class Solution913错误答案 {
    Boolean[][] memo;
    public int catMouseGame(int[][] graph) {
        // 对于猫，猫如果让老鼠到达0，那么就要回溯选择另一条路走，并记录当前猫鼠位置，表示猫输了，如果回溯的源头都不能赢，说明老鼠赢了
        // 对于老鼠，如果下一步走完，附近一格内有猫，或者被逼近死胡同，那么就要回溯选择另一条路走，并记录当前猫鼠位置，表示老鼠输了，如果回溯的源头都不能赢，说明猫赢了
        memo = new Boolean[graph.length][graph.length];
        dfs(1, 2, graph, -1, -1);
        return 0;
    }

    private boolean dfs(int mouse, int cat, int[][] graph, int pmouse, int pcat) {
        if (memo[mouse][cat] != null) return memo[mouse][cat]; // 记忆化
        boolean catWin = true; // 默认猫赢
        for (int mNext : graph[mouse]) {
            if (mNext == 0) return memo[mouse][cat] = true; // 记录当前猫鼠位置为true
            if (cat == mNext || mNext == pmouse) continue; // 如果老鼠下一格子是猫，不能走，跳过

            for (int cNext : graph[cat]) {
                if (cNext == 0 || cNext == pcat) continue; // 猫不能走0
                if (dfs(mNext, cNext, graph, mouse, cat)) catWin = false; // 如果有老鼠赢的可能
            }
            // 如果猫赢
            if (catWin) memo[mouse][cat] = false;
        }
        return memo[mouse][cat] = !catWin; // 记录当前猫鼠位置为false，表示老鼠无路可走
    }
}

class Solution913 {
    static final int MOUSE_WIN = 1;
    static final int CAT_WIN = 2;
    static final int DRAW = 0;
    int n;
    int[][] graph;
    int[][][] dp;

    public int catMouseGame(int[][] graph) {
        this.n = graph.length;
        this.graph = graph;
        this.dp = new int[n][n][2 * n * (n - 1)];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return getResult(1, 2, 0);
    }

    public int getResult(int mouse, int cat, int turns) {
        if (turns == 2 * n * (n - 1)) {
            return DRAW;
        }
        if (dp[mouse][cat][turns] < 0) {
            if (mouse == 0) {
                dp[mouse][cat][turns] = MOUSE_WIN;
            } else if (cat == mouse) {
                dp[mouse][cat][turns] = CAT_WIN;
            } else {
                getNextResult(mouse, cat, turns);
            }
        }
        return dp[mouse][cat][turns];
    }

    public void getNextResult(int mouse, int cat, int turns) {
        int curMove = turns % 2 == 0 ? mouse : cat;
        int defaultResult = curMove == mouse ? CAT_WIN : MOUSE_WIN;
        int result = defaultResult;
        int[] nextNodes = graph[curMove];
        for (int next : nextNodes) {
            if (curMove == cat && next == 0) {
                continue;
            }
            int nextMouse = curMove == mouse ? next : mouse;
            int nextCat = curMove == cat ? next : cat;
            int nextResult = getResult(nextMouse, nextCat, turns + 1);
            if (nextResult != defaultResult) {
                result = nextResult;
                if (result != DRAW) {
                    break;
                }
            }
        }
        dp[mouse][cat][turns] = result;
    }
}