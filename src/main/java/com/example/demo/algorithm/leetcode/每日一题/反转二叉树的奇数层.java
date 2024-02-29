package com.example.demo.algorithm.leetcode.每日一题;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 反转二叉树的奇数层{
    public static void main(String[] args) {
        TreeNode treeNode = new Solution2415().reverseOddLevels(new TreeNode(2, new TreeNode(3, new TreeNode(8, null, null), new TreeNode(13, null, null)),
                new TreeNode(5, new TreeNode(21, null, null), new TreeNode(34, null, null))));

        String regex = "[A-Z]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("1");
    }
}

class Solution2415 {
    public TreeNode reverseOddLevels(TreeNode root) {
        dsfSameTime(root.left, root.right, 1);
        return  root;
    }
    private void dsfSameTime(TreeNode left, TreeNode right, int i) {
        if (left == null) {
            return;
        }
        if (i % 2 != 0) {
            int tmp = left.val;
            left.val = right.val;
            right.val = tmp;
        }
        i++;
        dsfSameTime(left.left, right.left, i);
        dsfSameTime(left.right, right.right, i);
    }
}
