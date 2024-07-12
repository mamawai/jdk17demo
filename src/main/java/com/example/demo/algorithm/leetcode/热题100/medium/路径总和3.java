package com.example.demo.algorithm.leetcode.热题100.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class 路径总和3 {
    public static void main(String[] args) {
//        int i = new Solution437().pathSum(new TreeNode(5, new TreeNode(4,new TreeNode(11,new TreeNode(7), new TreeNode(2)), null),
//                new TreeNode(8,new TreeNode(13),new TreeNode(4,new TreeNode(5),new TreeNode(1)))), 22);
        int i1 = new Solution437().pathSum(new TreeNode(10, new TreeNode(5, new TreeNode(3, new TreeNode(3), new TreeNode(-2)), new TreeNode(2, null, new TreeNode(1))),
                new TreeNode(-3, null, new TreeNode(11, new TreeNode(8), null))), 8);
        System.out.println(i1);
    }
}

class Solution437 {
    int t;
    int ans;
    public int pathSum(TreeNode root, int targetSum) {
        t = targetSum;
        List<Long> values = new ArrayList<>();
        if (root == null) return 0;
        values.add((long) root.val);
        if (root.val == t) ans++;
        if (root.left != null) dfs(root.left, values);
        if (root.right != null) dfs(root.right, values);
        return ans;
    }

    private void dfs(TreeNode node, List<Long> values) {
        // 到了叶子节点 利用前缀和计算
        values.add(values.get(values.size() - 1) + node.val);
        long val = values.get(values.size() - 1);
        if (val == t) ans++;
        for (int j = values.size() - 2; j >= 0; j--) {
            if (val - values.get(j) == t) ans++;
        }
        if (node.left != null) dfs(node.left, values);
        if (node.right != null) dfs(node.right, values);
        values.remove(values.size() - 1);
    }
}