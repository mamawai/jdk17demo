package com.example.demo.algorithm.leetcode.每日一题.medium;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class 螺旋矩阵 {
    public static void main(String[] args) {
        List<Integer> list = new Solution54().spiralOrder(new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}});
        System.out.println(list);
    }
}

class Solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int count = 0;
        List<Integer> ans = new ArrayList<>();
        boolean[][] vis = new boolean[matrix.length][matrix[0].length];
        int x = 0;
        int y = 0;
        int[][] offsets = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int curIndex = 0;
        while (true) {
            ans.add(matrix[x][y]);
            ++count;
            if (count == matrix.length * matrix[0].length) {
                break;
            }
            vis[x][y] = true;
            int newX = 0;
            int newY = 0;

            while (curIndex < offsets.length) {
                int[] offset = offsets[curIndex];
                // 是否越界
                newX = x + offset[0];
                newY = y + offset[1];
                if (newX >= matrix.length || newX < 0 || newY < 0 || newY >= matrix[0].length) {
                    curIndex++;
                    continue;
                }
                // 是否visit过
                if (!vis[newX][newY]) {
                    break;
                }
                curIndex++;
                if (curIndex == 4){
                    curIndex = 0;
                }
            }
            x = newX;
            y = newY;
        }
        return ans;
    }
}