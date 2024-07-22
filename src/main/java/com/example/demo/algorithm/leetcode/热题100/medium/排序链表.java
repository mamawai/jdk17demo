package com.example.demo.algorithm.leetcode.热题100.medium;

import com.example.demo.algorithm.leetcode.structure.ListNode;

/**
 * 应该用分治法 归并排序
 */
public class 排序链表 {
    public static void main(String[] args) {
        ListNode node = new Solution148().sortList(new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3)))));
        System.out.println(node);
    }
}

class Solution148 {
    ListNode dummy;
    public ListNode sortList(ListNode head) {
        dummy = new ListNode();
        if ((dummy.next = head) == null) return null;
        ListNode next;
        while ((next = head.next) != null) {
            if (next.val < head.val) {
                head.next = next.next;
                dfs(dummy.next, next, true);
            }
            head = next;
        }
        return dummy.next;
    }

    private boolean dfs(ListNode node, ListNode next, boolean isFirst) {
        if (next.val < node.val) {
            next.next = node;
            if (isFirst) dummy.next = next;
            return true;
        } else {
            if (dfs(node.next, next, false)) node.next = next;
        }
        return false;
    }
}