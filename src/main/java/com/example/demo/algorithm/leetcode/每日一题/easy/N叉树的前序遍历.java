package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.example.demo.algorithm.leetcode.structure.ListNode;
import com.example.demo.algorithm.leetcode.structure.Node;

import java.util.ArrayList;
import java.util.List;

public class N叉树的前序遍历 {
    public static void main(String[] args) {
        ArrayList<Node> children = new ArrayList<>();
        ArrayList<Node> children1 = new ArrayList<>();
        children1.add(new Node(5));
        children1.add(new Node(6));
        children.add(new Node(3, children1));
        children.add(new Node(2));
        children.add(new Node(4));
        List<Integer> preorder = new Solution589().preorder(new Node(1, children));
        System.out.println(preorder);
    }
}

class Solution589 {
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(Node root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        ans.add(root.val);
        if (root.children == null) {
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            dfs(root.children.get(i), ans);
        }
    }
}