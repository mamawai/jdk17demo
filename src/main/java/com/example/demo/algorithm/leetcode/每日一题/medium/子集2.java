package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 子集2 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution90().subsetsWithDup(new int[]{1, 2, 2, 3, 4, 4});
        System.out.println(lists);
    }
}

class Solution90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(0, nums, ans, path);
        return ans;
    }

    private void dfs(int i, int[] nums, List<List<Integer>> ans, List<Integer> path) {
        int n = nums.length;
        if (i == n) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 选 x
        int x = nums[i];
        path.add(x);
        dfs(i + 1, nums, ans, path);
        path.remove(path.size() - 1); // 恢复现场

        // 不选 x，跳过所有等于 x 的数
        // 如果不跳过这些数，会导致「选 x 不选 x'」和「不选 x 选 x'」这两种情况都会加到 ans 中，这就重复了
        i++;
        while (i < n && nums[i] == x) {
            i++;
        }
        dfs(i, nums, ans, path);
    }
}