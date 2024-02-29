package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.Node;

import java.util.*;

public class N叉树的层序遍历 {
    public static void main(String[] args) {

    }
}

class Solution429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Node> queue = new ArrayList<>();
        queue.add(root);
        while (queue.size() > 0) {
            List<Node> queue2 = new ArrayList<>();
            List<Integer> integers = new ArrayList<>();
            for (Node node : queue) {
                integers.add(node.val);
                if (node.children != null) {
                    queue2.addAll(node.children);
                }
            }
            ans.add(integers);
            queue = queue2;
        }
        return ans;
    }
}