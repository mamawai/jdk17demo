package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.ListNode;

import java.util.HashSet;
import java.util.Set;

public class 环形链表2 {
    public static void main(String[] args) {

    }
}

class Solution142 {
    public ListNode detectCycle(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
    }
}