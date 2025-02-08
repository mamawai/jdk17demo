package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 全排列2 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution47().permuteUnique(new int[]{1});
        System.out.println(lists);
    }
}

class Solution47 {
    List<List<Integer>> res = new ArrayList<>();
    int n;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        this.n = nums.length;
        dfs(nums, 1 >> n,  new ArrayList<>());
        return res;
    }

    private void dfs(int[] nums, int mask, List<Integer> list) {
        if (list.size() == n) { // 退出条件
            res.add(new ArrayList<>(list));
            return;
        }
        int pre = -11;
        for (int i = 0; i < n; i++) {
            // 如果该位置是0 表示可以选
            if (((mask >> i) & 1) == 0) {
                // 如果前一个选择的位置和当前位置一样，则跳过 比如 1，1，2 第二个1直接跳过
                if (pre != -11 && pre == nums[i]) continue;
                pre = nums[i];
                list.add(nums[i]);
                dfs(nums, mask ^ (1 << i), list);
                list.remove(list.size() - 1); // 还原list
            }
        }
    }
}