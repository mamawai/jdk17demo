package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class 从前序与中序遍历序列构造二叉树 {
    public static void main(String[] args) {
        TreeNode treeNode = new Solution106().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});

    }
}

class Solution105 {
    private Map<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // pre 根左右
        // in 左根右
        int n = preorder.length;
        // 构造中序遍历哈希映射
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0,n-1,0,n-1);
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }
}
class Solution106 {
    private Map<Integer, Integer> indexMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(postorder, inorder, 0,n-1,0,n-1);
    }
    private TreeNode myBuildTree(int[] postorder, int[] inorder, int postorder_left, int postorder_right, int inorder_left, int inorder_right) {
        if (postorder_left > postorder_right) {
            return null;
        }
        // 后序遍历中的最后一个节点就是根节点
        int postorder_root = postorder_right;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(postorder[postorder_root]);
        // 先把根节点建立出来
        TreeNode root = new TreeNode(postorder[postorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(postorder, inorder, postorder_left, postorder_left + size_left_subtree - 1, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(postorder, inorder, postorder_left + size_left_subtree, postorder_right - 1, inorder_root + 1, inorder_right);
        return root;
    }
}