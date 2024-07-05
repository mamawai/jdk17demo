package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.ArrayList;
import java.util.List;

public class 修改矩阵 {
    public static void main(String[] args) {

    }
}

class Solution3033 {
    public int[][] modifiedMatrix(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            List<Integer> idx = new ArrayList<>();
            int colMax = 0;
            for (int j = 0; j < matrix.length; j++) {
                int mat = matrix[j][i];
                colMax = Math.max(colMax, mat);
                if (mat == -1) idx.add(j);
            }
            for (int index : idx) {
                matrix[index][i] = colMax;
            }
        }
        return matrix;
    }
}