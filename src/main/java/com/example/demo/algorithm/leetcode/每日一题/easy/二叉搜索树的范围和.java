package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

public class 二叉搜索树的范围和 {
    public static void main(String[] args) {

    }
}

class Solution938 {
    int ans = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root.val >= low && root.val <= high) {
            ans += root.val;
        }
        if (root.left != null) {
            rangeSumBST(root.left, low, high);
        }
        if (root.right != null) {
            rangeSumBST(root.right, low, high);
        }
        return ans;
    }
}