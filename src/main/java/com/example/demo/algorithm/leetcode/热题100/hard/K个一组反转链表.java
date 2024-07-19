package com.example.demo.algorithm.leetcode.热题100.hard;

import com.example.demo.algorithm.leetcode.structure.ListNode;

public class K个一组反转链表 {

    public static void main(String[] args) {
        ListNode node = new Solution25().reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
        System.out.println(node);
    }
}

class Solution25 {
    ListNode dummy;
    ListNode tail;
    ListNode preTail;
    ListNode realHead;
    int oriK;
    public ListNode reverseKGroup(ListNode head, int k) {
        oriK = k;
        dummy = new ListNode();
        dummy.next = head;
        dfs(head, k - 1);
        return realHead;
    }

    private void dfs(ListNode node, int k) {
        // 获得node节点下一个节点next
        ListNode next = node.next;
        // 连接next 更新dummy
        if (dummy.next != node) {
            node.next = dummy.next;
            dummy.next = node;
        } else node.next = null;
        // 当node为每个片区第一个节点时更新tail
        if (k == oriK - 1) tail = node;
        if (k == 0) {
            if (realHead == null) realHead = dummy.next;
            if (preTail != null) preTail.next = dummy.next;
            preTail = tail;
            if (next == null) return;
            // 每个片区结束后更新dummy
            dummy.next = next;
            dfs(next, oriK - 1);
        } else {
            if (next != null) dfs(next, k - 1);
            // 说明当前遍历到最后一个ListNode且最后一组的数量不足k个
            // 需要再逆序过来拼接
            else dfs(dummy.next , oriK - k - 1);
        }
    }
}