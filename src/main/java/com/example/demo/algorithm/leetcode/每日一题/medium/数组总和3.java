package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.List;

public class 数组总和3 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution216().combinationSum3(3, 9);
        System.out.println(lists);
    }
}

class Solution216 {
    List<List<Integer>> ans;
    int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public List<List<Integer>> combinationSum3(int k, int n) {
        ans = new ArrayList<>();
        dfs(n, k, new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(int rest, int k, ArrayList<Integer> list, int index) {
        if (k == 0 && rest != 0) return;
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (rest < candidate) {
                return;
            }
            if (rest == candidate) {
                if (k == 1) {
                    list.add(candidate);
                    ans.add(new ArrayList<>(list));
                    list.remove(list.size() - 1);
                }
                return;
            }
            list.add(candidate);
            dfs(rest - candidate, k -1, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}