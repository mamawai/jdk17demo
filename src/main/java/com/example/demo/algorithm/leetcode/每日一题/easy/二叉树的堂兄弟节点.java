package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class 二叉树的堂兄弟节点 {
    public static void main(String[] args) {
        boolean cousins = new Solution993().isCousins(new TreeNode(1, new TreeNode(2, null, new TreeNode(4, null, null)), new TreeNode(3, null, new TreeNode(5, null, null))), 5, 4);
        System.out.println(cousins);
    }
}

class Solution993 {
    public boolean isCousins(TreeNode root, int x, int y) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Deque<TreeNode> queue2 = new ArrayDeque<>();
            for (TreeNode fa : queue) {
                if (fa.left != null) queue2.offer(fa.left);
                if (fa.right != null) queue2.offer(fa.right);
            }
            // flag记录本层xy找到的历史 bfs每层会重置flag
            boolean xFlag = false;
            boolean yFlag = false;
            for (TreeNode fa : queue) {
                // 记录当前父节点xy是否找到
                boolean yFound = (fa.left != null && fa.left.val == y) || (fa.right != null && fa.right.val == y);
                boolean xFound = (fa.left != null && fa.left.val == x) || (fa.right != null && fa.right.val == x);
                // 不同父节点匹配xy 返回ture
                if (xFlag && yFound) return true;
                if (yFlag && xFound) return true;
                // 记录是否找到xy
                if (xFound) xFlag = true;
                if (yFound) yFlag = true;
                // 同一个父节点同时找到 返回false
                if (xFound && yFound) {
                    return false;
                }
            }
            queue = queue2;
        }
        return false;
    }
}