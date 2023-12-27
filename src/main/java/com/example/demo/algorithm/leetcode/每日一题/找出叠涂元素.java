package com.example.demo.algorithm.leetcode.每日一题;

import java.util.HashMap;
import java.util.Map;

public class 找出叠涂元素 {
    public static void main(String[] args) {
        Solution2661 solution2661 = new Solution2661();
        int i = solution2661.firstCompleteIndex(new int[]{2, 8, 7, 4, 1, 3, 5, 6, 9}, new int[][]{{3, 2, 5}, {1, 4, 6}, {8, 7, 9}});

    }
}

class Solution2661  {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int mLength = mat.length;
        int[] mArray = new int[mLength];
        int nLength = mat[0].length;
        int[] nArray = new int[nLength];

        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < mLength; i++) {
            for (int j = 0; j < nLength; j++) {
                map.put(mat[i][j], new int[]{i,j});
            }
        }

        // 从map中找出当前arr数字的坐标
        for (int k = 0; k < arr.length; k++) {
            int[] ij = map.get(arr[k]);
            int i1 = ++mArray[ij[0]];
            int i2 = ++nArray[ij[1]];
            if (i1 == nLength || i2 == mLength) {
                return k;
            }
        }
        return -1;
    }
}