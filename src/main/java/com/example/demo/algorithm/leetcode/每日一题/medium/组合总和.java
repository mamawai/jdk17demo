package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 组合总和 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution39().combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(lists);
    }
}

class Solution39 {
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        ans = new ArrayList<>();
        dfs(target, candidates, new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(int rest, int[] candidates, List<Integer> list, int index) {
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (candidate > rest) {
                return;
            }
            if (candidate == rest) {
                list.add(candidate);
                ans.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                return;
            }
            list.add(candidate);
            dfs(rest - candidate, candidates, list, i);
            list.remove(list.size() - 1);
        }
    }
}