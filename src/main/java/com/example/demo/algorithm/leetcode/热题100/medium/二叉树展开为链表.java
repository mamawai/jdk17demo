package com.example.demo.algorithm.leetcode.热题100.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

public class 二叉树展开为链表 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(5, null, new TreeNode(6)));
        new Solution114().flatten(root);
        System.out.println(root);

    }
}

class Solution114 {
    TreeNode dummy;
    public void flatten(TreeNode root) {
        dummy = new TreeNode(0);
        dfs(root);
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        TreeNode right = node.right;
        dummy.right = node;
        dummy = dummy.right;
        dfs(node.left);
        node.left = null;
        dfs(right);
    }
}