package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.ListNode;

public class 两两交换链表中的节点 {
    public static void main(String[] args) {
        ListNode node = new Solution24().swapPairs(
//                new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))))
        new ListNode(2, new ListNode(5,new ListNode(3,new ListNode(4,new ListNode(6,new ListNode(2, new ListNode(2)))))))
        );
        System.out.println(node);
    }
}

class Solution24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = new ListNode();
        ListNode dummy = new ListNode(0, head.next);
        // 开头的处理 换两次指向
        head.next = dummy.next.next;
        dummy.next.next = head;
        node.next = dummy.next;
        dummy.next = head.next;
        // 一顿换 换三次指向
        while (dummy.next != null && dummy.next.next != null) {
            head.next = dummy.next.next;
            dummy.next.next = head.next.next;
            head.next.next = dummy.next;
            dummy.next = dummy.next.next;
            head = head.next.next;
        }
        return node.next;
    }
}