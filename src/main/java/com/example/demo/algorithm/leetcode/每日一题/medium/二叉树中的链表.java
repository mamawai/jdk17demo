package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.ListNode;
import com.example.demo.algorithm.leetcode.structure.TreeNode;

public class 二叉树中的链表 {
    public static void main(String[] args) {
        ListNode head = new ListNode(2, new ListNode(2, new ListNode(1)));

        TreeNode root = new TreeNode(2, null, new TreeNode(2, null, new TreeNode(2, null, new TreeNode(1))));
        boolean subPath = new Solution1367().isSubPath(head, root);
        System.out.println(subPath);
    }
}


/**
 * 枚举二叉树中的每个节点为起点往下的路径是否有与链表相匹配的路径。为了判断是否匹配我们设计一个递归函数 dfs(rt,head) ，其中 rt 表示当前匹配到的二叉树节点，head 表示当前匹配到的链表节点，整个函数返回布尔值表示是否有一条该节点往下的路径与 head 节点开始的链表匹配，如匹配返回 true，否则返回 false ，一共有四种情况：<br>
 * 链表已经全部匹配完，匹配成功，返回 true
 * 二叉树访问到了空节点，匹配失败，返回 false
 * 当前匹配的二叉树上节点的值与链表节点的值不相等，匹配失败，返回 false
 * 前三种情况都不满足，说明匹配成功了一部分，我们需要继续递归匹配，所以先调用函数 dfs(rt→left,head→next) ，其中 rt→left 表示该节点的左儿子节点， head→next 表示下一个链表节点，如果返回的结果是 false，说明没有找到相匹配的路径，需要继续在右子树中匹配，继续递归调用函数 dfs(rt→right,head→next) 去找是否有相匹配的路径，其中 rt→right 表示该节点的右儿子节点， head→next 表示下一个链表节点。
 * 匹配函数确定了，剩下只要枚举即可，从根节点开始，如果当前节点匹配成功就直接返回 true ，否则继续找它的左儿子和右儿子是否满足，也就是代码中的 dfs(root,head) || isSubPath(head,root->left) || isSubPath(head,root->right) ，然后不断的递归调用。
 * 这样枚举所有节点去判断即能找出是否有一条与链表相匹配的路径。
 */
class Solution1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        return dfs(root, head) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    public boolean dfs(TreeNode rt, ListNode head) {
        // 链表已经全部匹配完，匹配成功
        if (head == null) {
            return true;
        }
        // 二叉树访问到了空节点，匹配失败
        if (rt == null) {
            return false;
        }
        // 当前匹配的二叉树上节点的值与链表节点的值不相等，匹配失败
        if (rt.val != head.val) {
            return false;
        }
        return dfs(rt.left, head.next) || dfs(rt.right, head.next);
    }
}