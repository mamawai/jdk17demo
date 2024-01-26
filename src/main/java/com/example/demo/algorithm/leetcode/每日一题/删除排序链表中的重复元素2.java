package com.example.demo.algorithm.leetcode.每日一题;

import com.example.demo.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 删除排序链表中的重复元素2 {
    public static void main(String[] args) {
        ListNode listNode = new SolutionB82().deleteDuplicates(
                new ListNode(1, new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(2))))));

    }
}

class Solution82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        LinkedList<Integer> ans = new LinkedList<>();
        ListNode point = head;
        int pre = -101;
        while (point != null) {
            if (ans.isEmpty()) {
                if (pre != point.val) {
                    ans.push(point.val);
                    pre = point.val;
                }
            } else {
                if (pre != point.val) {
                    ans.push(point.val);
                    pre = point.val;
                } else {
                    if (ans.peek() == point.val) {
                        ans.pop();
                    }
                }
            }
            point = point.next;
        }
        if (ans.isEmpty()) {
            return null;
        }
        ListNode dummy = head;
        while (ans.peek() != null) {
            dummy.val = ans.pollLast();
            if (ans.size() >= 1) {
                dummy = dummy.next;
            }
        }
        dummy.next = null;
        return head;
    }
}

class SolutionB82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}
