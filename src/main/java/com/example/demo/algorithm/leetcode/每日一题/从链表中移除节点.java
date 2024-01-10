package com.example.demo.algorithm.leetcode.每日一题;

import com.example.demo.algorithm.leetcode.ListNode;

import java.util.*;

public class 从链表中移除节点 {
    public static void main(String[] args) {
        ListNode listNode = new SolutionB2487().removeNodes(new ListNode(5, new ListNode(2, new ListNode(13, new ListNode(3, new ListNode(8))))));

    }
}

class Solution2487 {
    public ListNode removeNodes(ListNode head) {
        Deque<ListNode> stack = new ArrayDeque<ListNode>();
        for (; head != null; head = head.next) {
            stack.push(head);
        }
        for (;!stack.isEmpty();stack.pop()) {
            if (head == null || stack.peek().val >= head.val) {
                ListNode peek = stack.peek();
                peek.next = head;
                head = peek;
            }
        }
        return head;
    }
}
class SolutionB2487 {
    public ListNode removeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        if (head.next != null && head.val < head.next.val) {
            return head.next;
        } else {
            return head;
        }
    }
}
/**
 * 单调栈。。。
 */
class SolutionC2487{
    public ListNode removeNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        Deque<Integer> stk = new ArrayDeque<>();
        for (int v : nums) {
            while (!stk.isEmpty() && stk.peekLast() < v) {
                stk.pollLast();
            }
            stk.offerLast(v);
        }
        ListNode dummy = new ListNode();
        head = dummy;
        while (!stk.isEmpty()) {
            head.next = new ListNode(stk.pollFirst());
            head = head.next;
        }
        return dummy.next;
    }
}