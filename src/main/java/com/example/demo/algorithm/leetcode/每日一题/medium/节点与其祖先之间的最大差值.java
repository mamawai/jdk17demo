package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

public class 节点与其祖先之间的最大差值 {
    public static void main(String[] args) {
        int i = new Solution1026().maxAncestorDiff(new TreeNode(8, new TreeNode(3, new TreeNode(1), new TreeNode(6, new TreeNode(4), new TreeNode(7))), new TreeNode(10, null, new TreeNode(14, new TreeNode(13), null))));
        System.out.println(i);
    }
}

class Solution1026 {
    int ans = 0;

    public int maxAncestorDiff(TreeNode root) {
        // dfs
        dfs(root.left, root.val, root.val);
        dfs(root.right, root.val, root.val);
        return ans;
    }

    private void dfs(TreeNode node, int max, int min) {
        if (node == null) return;
        if (node.val > max) max = node.val;
        if (node.val < min) min = node.val;
        ans = Math.max(ans, max - min);
        dfs(node.left, max, min);
        dfs(node.right, max, min);
    }
}
