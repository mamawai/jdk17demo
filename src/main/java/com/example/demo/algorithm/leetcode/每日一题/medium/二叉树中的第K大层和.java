package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.*;

public class 二叉树中的第K大层和 {
    public static void main(String[] args) {
        int maxValue = 1000000 * 10000;
        System.out.println(maxValue);
    }
}

class Solution2583 {
    public long kthLargestLevelSum(TreeNode root, int k) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Long> layers = new ArrayList<>();
        while (!queue.isEmpty()) {
            Deque<TreeNode> queue2 = new ArrayDeque<>();
            long sum = 0;
            for (TreeNode node : queue) {
                sum += node.val;
                if (node.left != null) {
                    queue2.offer(node.left);
                }
                if (node.right != null) {
                    queue2.offer(node.right);
                }
            }
            layers.add(sum);
            queue = queue2;
        }
        if (k > layers.size()) {
            return -1;
        }
        Collections.sort(layers);
        return layers.get(k - 1);
    }
}
