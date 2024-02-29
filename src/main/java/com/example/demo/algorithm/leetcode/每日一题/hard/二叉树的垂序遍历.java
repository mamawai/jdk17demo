package com.example.demo.algorithm.leetcode.每日一题.hard;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class 二叉树的垂序遍历 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution987().verticalTraversal(new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7))));

    }
}

class Solution987 {
    int minPos = Integer.MAX_VALUE;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        dfs(root, map, 0, 0);
        int size = map.size();
        List<Integer>[] ans = new List[size];
        // 3 -1 0 1
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
            List<int[]> values = entry.getValue();
            values.sort((o1, o2) -> {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            });
            ans[entry.getKey() -  minPos] =  values.stream().map(v -> v[0]).collect(Collectors.toList());
        }
        return Arrays.stream(ans).toList();
    }

    private void dfs(TreeNode root, Map<Integer, List<int[]>> map, int i, int j) {
        if (root == null) {
            return;
        }
        minPos = Math.min(minPos, j);
        List<int[]> orDefault = map.getOrDefault(j, new ArrayList<>());
        orDefault.add(new int[]{root.val, i});
        map.put(j, orDefault);
        dfs(root.left, map, i + 1, j - 1);
        dfs(root.right, map, i + 1, j + 1);
    }
}