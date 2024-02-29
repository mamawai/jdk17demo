package com.example.demo.algorithm.leetcode.每日一题;

import com.example.demo.algorithm.leetcode.structure.ListNode;

public class 删除排序链表中的重复元素1 {
    public static void main(String[] args) {
        ListNode listNode = new Solution83().deleteDuplicates(new ListNode(1, new ListNode(1, new ListNode(2))));

    }
}
class Solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode cur = new ListNode(0, head);
        ListNode dummy = cur;
        while (dummy.next != null && dummy.next.next != null) {
            if (dummy.next.val == dummy.next.next.val) {
                dummy.next = dummy.next.next;
            } else {
                dummy = dummy.next;
            }
        }
        return cur.next;
    }
}