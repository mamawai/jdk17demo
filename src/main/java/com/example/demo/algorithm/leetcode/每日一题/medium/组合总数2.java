package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总数2 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution40().combinationSum2(new int[]{1,1,1,1,1,1,1,1,3}, 4);
        System.out.println(lists);
    }
}

class Solution40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 先排序
        dfs(-1, candidates, target, res, new ArrayList<>()); // dfs
        return res;
    }

    private void dfs(int i, int[] candidates, int target, List<List<Integer>> res, List<Integer> list) {
        // 如果剩下的是0 直接加入res并返回
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 如果i在最后一个或者下一个candidate大于target，直接返回
        if (i == candidates.length - 1 || candidates[i + 1] > target) return;
        // 选下一个
        for (int j = i + 1; j < candidates.length; j++) {
            if (j > i + 1 && candidates[j] == candidates[j - 1]) continue;
            list.add(candidates[j]); // 加入下一个candidate
            dfs(j, candidates, target - candidates[j], res, list);
            list.remove(list.size() - 1); // 还原list
        }
    }
}