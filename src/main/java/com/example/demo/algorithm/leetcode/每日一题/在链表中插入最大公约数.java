package com.example.demo.algorithm.leetcode.每日一题;

import com.example.demo.algorithm.leetcode.ListNode;

public class 在链表中插入最大公约数 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(18, new ListNode(6, new ListNode(10, new ListNode(3))));
        ListNode res = new Solution2807().insertGreatestCommonDivisors(listNode);

    }
}

class Solution2807 {
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode point = head;
        while (point.next != null) {
            int val1 = point.val;
            int val2 = point.next.val;
            // 求最大公约数
            while (val2 != 0){
                int tmp = val1 % val2;
                val1 = val2;
                val2 = tmp;
            }
            ListNode addNode = new ListNode(val1, point.next);
            point.next = addNode;
            point = addNode.next;
        }
        return head;
    }
}