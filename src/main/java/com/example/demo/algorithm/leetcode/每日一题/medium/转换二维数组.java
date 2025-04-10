package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class 转换二维数组 {
    public static void main(String[] args) {
        List<List<Integer>> matrix = new Solution2610().findMatrix(new int[]{1, 3, 4, 1, 2, 3, 1});
        System.out.println(matrix);
    }
}

class Solution2610 {
    public List<List<Integer>> findMatrix(int[] nums) {
        List<Integer>[] res = new List[200];
        res[0] = new ArrayList<>();
        int idx = 0;
        for (int num : nums) {
            boolean flag = false;
            for (List<Integer> list : res) {
                if (list != null && !list.contains(num)) {
                    flag = true;
                    list.add(num);
                    break;
                }
            }
            if (!flag) {
                List<Integer> list1 = new ArrayList<>();
                list1.add(num);
                res[++idx] = list1;
            }
        }
        List<List<Integer>> res1 = new ArrayList<>();
        for (int i = 0; i <= idx; i++) {
            if (res[i] != null) {
                res1.add(res[i]);
            }
        }
        return res1;
    }
}