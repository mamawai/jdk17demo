package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

public class 另一棵树的子树 {
    public static void main(String[] args) {
//        boolean subtree = new Solution572().isSubtree(new TreeNode(3, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(5)), new TreeNode(4, new TreeNode(1,new TreeNode(1), null), new TreeNode(2)));
        boolean subtree1 = new Solution572().isSubtree(new TreeNode(1, null, new TreeNode(1, null, new TreeNode(1, null, new TreeNode(1, new TreeNode(2), null)))),
                new TreeNode(1, null, new TreeNode(1, null, new TreeNode(1,  new TreeNode(2), null))));
        System.out.println(subtree1);

//        System.out.println(subtree);
    }
}

// 递归写法还需掌握啊
class Solution572 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return dfs(s, t);
    }

    public boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    public boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null || s.val != t.val) {
            return false;
        }
        return check(s.left, t.left) && check(s.right, t.right);
    }
}