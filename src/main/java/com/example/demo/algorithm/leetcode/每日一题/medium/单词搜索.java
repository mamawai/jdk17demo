package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 单词搜索 {
    public static void main(String[] args) {

    }
}

class Solution79 {
    char[][] board;
    char[] word;
    int[][] offsets = new int[][]{{1,0}, {0,1}, {-1,0}, {0,-1}};

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char charredAt = this.word[0];
                if (board[i][j] == charredAt) {
                    board[i][j] = '0';
                    if (dfs(1, i, j)) return true;
                    // 恢复现场
                    board[i][j] = charredAt;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int x, int y) {
        if (i == word.length) return true;
        for (int[] offset : offsets) {
            int newX = x + offset[0];
            int newY = y + offset[1];
            if (newX >= 0 && newY >= 0 && newX <= board.length - 1 && newY <=board[0].length - 1 && board[newX][newY] == word[i]) {
                board[newX][newY] = '0';
                boolean dfsed = dfs(i + 1, newX, newY);
                // 恢复现场
                board[newX][newY] = word[i];
                if (dfsed) return true;
            }
        }
        return false;
    }
}