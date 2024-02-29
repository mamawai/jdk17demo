package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class 二叉树的最近公共祖先 {
    public static void main(String[] args) {
        TreeNode q = new TreeNode(4);
        TreeNode p1 = new TreeNode(6);
        TreeNode p = new TreeNode(5, p1, new TreeNode(2, new TreeNode(7), q));
        TreeNode q1 = new TreeNode(0);
        TreeNode treeNode = new SolutionB236().lowestCommonAncestor(new TreeNode(3, p, new TreeNode(1, q1, new TreeNode(8))), p, q);
        System.out.println(treeNode.val);
    }
}

class Solution236 {
    List<TreeNode> pTreeNodes = new ArrayList<>();
    List<TreeNode> qTreeNodes = new ArrayList<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p.val, q.val, new ArrayDeque<>());
        int min = Math.min(pTreeNodes.size(), qTreeNodes.size());
        for (int i = 0; i < min; i++) {
            if (pTreeNodes.get(i).val != qTreeNodes.get(i).val) {
                return pTreeNodes.get(i - 1);
            }
        }
        return pTreeNodes.get(min - 1);
    }

    private void dfs(TreeNode root, int pVal, int qVal, Deque<TreeNode> temp) {
        if (pTreeNodes.size() > 0 && qTreeNodes.size() > 0) return;
        if (root != null) {
            temp.add(root);
        } else {
            return;
        }
        if (root.val == pVal && pTreeNodes.size() == 0) {
            pTreeNodes.addAll(temp);
        }
        if (root.val == qVal && qTreeNodes.size() == 0) {
            qTreeNodes.addAll(temp);
        }
        dfs(root.left,pVal,qVal,temp);
        if (root.left != null) temp.pollLast();
        dfs(root.right,pVal,qVal,temp);
        if (root.right != null) temp.pollLast();
    }
}

class SolutionB236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) {
            return right;
        }
        if(right == null) {
            return left;
        }
        return root;
    }
}