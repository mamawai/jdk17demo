package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.*;

public class 二叉搜索树最近节点查询 {
    public static void main(String[] args) {
        ArrayList<Integer> queries = new ArrayList<>();
        queries.add(2);
        queries.add(5);
        queries.add(16);
        List<List<Integer>> lists = new Solution2476()
                .closestNodes(new TreeNode(6,new TreeNode(2,new TreeNode(1),new TreeNode(4)), new TreeNode(13,new TreeNode(9),new TreeNode(15,new TreeNode(14),null))), queries);

    }
}

class Solution2476 {
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Integer> values = new ArrayList<>();
        while (!queue.isEmpty()) {
            Deque<TreeNode> queue2 = new ArrayDeque<>();
            for (TreeNode node : queue) {
                values.add(node.val);
                if (node.left != null) {
                    queue2.offer(node.left);
                }
                if (node.right != null) {
                    queue2.offer(node.right);
                }
            }
            queue = queue2;
        }
        Collections.sort(values);
        // 二分找吧
        a:for (int query : queries) {
            List<Integer> e = new ArrayList<>();
            int l = 0;
            int r = values.size();
            while (l < r) {
                int mid = (l + r) / 2;
                Integer getMid = values.get(mid);
                if (getMid == query) {
                    e.add(query);
                    e.add(query);
                    ans.add(e);
                    continue a;
                }
                if (getMid > query) {
                    r = mid;
                }
                if (getMid < query) {
                    l = mid + 1;
                }
            }
            if (l == 0) {
                e.add(-1);
                e.add(values.get(l));
            } else if (l == values.size()) {
                e.add(values.get(l - 1));
                e.add(-1);
            } else {
                e.add(values.get(l - 1));
                e.add(values.get(l));
            }
            ans.add(e);
        }
        return ans;
    }
}
