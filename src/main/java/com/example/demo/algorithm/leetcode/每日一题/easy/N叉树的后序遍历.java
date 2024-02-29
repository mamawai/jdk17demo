package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.example.demo.algorithm.leetcode.structure.Node;

import java.util.ArrayList;
import java.util.List;

public class N叉树的后序遍历 {
    public static void main(String[] args) {

    }
}

class Solution590 {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }

    private void dfs(Node root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (root.children == null) {
            ans.add(root.val);
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            dfs(root.children.get(i), ans);
        }
        ans.add(root.val);
    }
}
