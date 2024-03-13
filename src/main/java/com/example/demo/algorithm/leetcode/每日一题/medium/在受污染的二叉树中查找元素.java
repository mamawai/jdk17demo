package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.HashSet;

public class 在受污染的二叉树中查找元素 {
    public static void main(String[] args) {

    }
}

class FindElements {

    TreeNode eRoot;

    int[] valArray;

    public FindElements(TreeNode root) {
        valArray = new int[1000000];
        root.val = 0;
        dfs(root.left, 0, true);
        dfs(root.right, 0, false);
        eRoot = root;
        HashSet<Object> objects = new HashSet<>();
        boolean add = objects.add(new Object());

    }

    public boolean find(int target) {
        return valArray[target] != 0;
    }

    private void dfs(TreeNode node, int e, boolean isLeft) {
        if (node == null) {
            return;
        }
        int val;
        if (isLeft) {
            val = 2 * e + 1;
        } else {
            val = 2 * e + 2;
        }
        valArray[val] ++;
        dfs(node.left, val, true);
        dfs(node.right, val, false);
    }
}
