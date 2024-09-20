package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.ListNode;

import java.util.ArrayList;
import java.util.List;

public class 合并零之间的节点 {
    public static void main(String[] args) {
        ListNode node = new Solution2181().mergeNodes(new ListNode(0, new ListNode(3, new ListNode(1, new ListNode(0, new ListNode(4, new ListNode(0, new ListNode(2, new ListNode(5, new ListNode(0))))))))));
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}

class Solution2181 {
    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = head;
        do {
            dummy.val += head.next.val;
            head = head.next;
        } while (head.val != 0);
        if (head.next == null) {
            dummy.next = null;
            return dummy;
        }
        dummy.next = mergeNodes(head);
        return dummy;
    }
}