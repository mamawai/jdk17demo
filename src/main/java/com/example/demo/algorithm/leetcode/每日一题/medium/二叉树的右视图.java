package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class 二叉树的右视图 {
    public static void main(String[] args) {
        List<Integer> list = new Solution199().rightSideView(new TreeNode(1, new TreeNode(2, null, new TreeNode(5)), new TreeNode(3, null, new TreeNode(4))));
        System.out.println(list);
    }
}

class Solution199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // bfs
        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            while (size > 0) {
                TreeNode polled = nodes.poll();
                if (polled.left != null) nodes.offer(polled.left);
                if (polled.right != null) nodes.offer(polled.right);
                size--;
                if (size == 0) res.add(polled.val);
            }
        }
        return res;
    }
}