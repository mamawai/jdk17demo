package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 甲板上的战舰 {
    public static void main(String[] args) {
        int i = new Solution419().countBattleships(new char[][]{
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
        });
        System.out.println(i);
    }
}

class Solution419 {
    public int countBattleships(char[][] board) {
        int count = 0;
        int[][] offsets = new int[][]{{0,1},{1,0}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c == 'X') {
                    count++;
                    board[i][j] = '.';
                    // dfs吧
                    for (int[] offset : offsets) {
                        dfs(i + offset[0], j + offset[1], offset, board);
                    }
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y, int[] offset, char[][] board) {
        if (x < board.length && y < board[0].length && board[x][y] == 'X') {
            board[x][y] = '.';
            dfs(x + offset[0], y + offset[1], offset, board);
        }
    }
}