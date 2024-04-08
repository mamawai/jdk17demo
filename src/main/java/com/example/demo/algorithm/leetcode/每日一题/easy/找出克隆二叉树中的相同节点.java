package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

public class 找出克隆二叉树中的相同节点 {
    public static void main(String[] args) {

    }
}

class Solution1379 {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return dfs(cloned, target.val);
    }

    private TreeNode dfs(TreeNode node, int val) {
        if (node == null) return null;
        if (node.val == val) return node;
        TreeNode leftDfs = dfs(node.left, val);
        TreeNode rightDfs = dfs(node.right, val);
        return leftDfs == null ? rightDfs : leftDfs;
    }
}

/**
 * 直接递归比较节点的地址
 */
class Solution1379B {
    public TreeNode getTargetCopy(TreeNode original, TreeNode cloned, TreeNode target) {
        if (original == null || original == target) {
            return cloned;
        }
        TreeNode leftRes = getTargetCopy(original.left, cloned.left, target);
        if (leftRes != null) {
            return leftRes; // 已经找到 target，无需递归右子树
        }
        return getTargetCopy(original.right, cloned.right, target);
    }
}