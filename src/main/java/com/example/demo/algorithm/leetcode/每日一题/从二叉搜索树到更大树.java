package com.example.demo.algorithm.leetcode.每日一题;

import com.example.demo.algorithm.leetcode.ListNode;
import com.example.demo.algorithm.leetcode.TreeNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class 从二叉搜索树到更大树 {
    public static void main(String[] args) {

        TreeNode ans = new Solution1038().bstToGst(new TreeNode(4, new TreeNode(1, new TreeNode(0, null, null), new TreeNode(2, null, new TreeNode(3, null, null))), new TreeNode(6, new TreeNode(5, null, null), new TreeNode(7, null, new TreeNode(8, null, null)))));

    }
}

class Solution1038 {
    public TreeNode bstToGst(TreeNode root) {
        // 每个分支找右子树到底 回溯的时候如果遇到左子树也是先找右子树到底
        TreeNode rightEnd = findRightEnd(root, 0);
        return rightEnd;
    }


    private TreeNode findRightEnd(TreeNode node, int init) {
        if (node.right == null) {
            node.val+=init;
            return findLeftRightEnd(node);
        } else {
            TreeNode rightEnd = findRightEnd(node.right, init);
            node.val += rightEnd.val;
            if (node.left != null) {
                if (node.left.val < node.val) {
                   return findLeftRightEnd(node);
                } else {
                    return node;
                }
            } else {
                return node;
            }
        }
    }

    private TreeNode findLeftRightEnd(TreeNode node) {
        if (node.left == null) {
            return node;
        } else {
            return findRightEnd(node.left, node.val);
        }
    }
}

// 反中序遍历
class Solution反中序遍历 {
    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root != null) {
            bstToGst(root.right);
            sum += root.val;
            root.val = sum;
            bstToGst(root.left);
        }
        return root;
    }
}
// 冒泡排序