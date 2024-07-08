package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.List;

public class 检查操作是否合法 {
    public static void main(String[] args) {
        boolean checkMove = new Solution1958().checkMove(new char[][]{
                {'B', 'W', '.', 'W', '.', 'W', 'W', '.'},
                {'B', 'W', '.', '.', 'W', 'W', 'W', '.'},
                {'W', 'B', '.', 'W', 'W', 'W', 'W', '.'},
                {'B', 'B', 'W', '.', 'W', '.', '.', 'W'},
                {'B', '.', 'B', 'B', 'B', '.', 'B', '.'},
                {'B', 'W', 'B', '.', 'W', 'B', 'B', '.'},
                {'.', '.', '.', 'B', 'W', 'B', '.', '.'},
                {'W', 'B', '.', 'B', '.', '.', 'B', 'W'}
        }, 2, 7, 'B');
        System.out.println(checkMove);
    }
}

class Solution1958 {
    char[][] board;
    char color;
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        // 八个方向先遍历一次 确定可行方向
        this.board = board;
        this.color = color;
        int[][] offsets = new int[][]{{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
        for (int[] offset : offsets) {
            int newR = rMove + offset[0];
            int newC = cMove + offset[1];
            if (newR >= 0 && newC >= 0 && newR < board.length && newC < board[0].length) {
                char c = board[newR][newC];
                if (c != color && c != '.') {
                    if (dfs(newR, newC, new int[]{offset[0], offset[1]})) return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int[] offset) {
        int newX = x + offset[0];
        int newY = y + offset[1];
        if (newX >= 0 && newY >= 0 && newX < board.length && newY < board[0].length) {
            if (board[newX][newY] == '.') return false;
            else if (board[newX][newY] == color) return true;
            else return dfs(newX, newY, offset);
        }
        return false;
    }
}
