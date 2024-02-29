package com.example.demo.algorithm.leetcode;

import com.example.demo.algorithm.leetcode.structure.ListNode;

public class 两数相加 {
    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        ListNode n1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode n2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode listNode = solution2.addTwoNumbers(n1, n2);
    }
}

class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head =  new ListNode();
        ListNode point = head;
        int carry = 0;
        while(l1 != null || l2 != null) {
            int x = l1 == null? 0 : l1.val;
            int y = l2 == null? 0 : l2.val;

            point.next = new ListNode(carry);
            point = point.next;
            if(x + y + carry >= 10) {
                point.val += x + y - 10;
                carry = 1;
            } else {
                point.val += x + y;
                carry = 0;
            }
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        if (carry == 1) {
            point.next = new ListNode(carry);
        }

        return head.next;
    }
}