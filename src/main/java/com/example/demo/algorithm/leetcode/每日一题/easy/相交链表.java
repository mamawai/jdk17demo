package com.example.demo.algorithm.leetcode.每日一题.easy;

import com.example.demo.algorithm.leetcode.structure.ListNode;

import java.util.HashSet;
import java.util.Set;

public class 相交链表 {
    public static void main(String[] args) {

    }
}
class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode a = headA;
        ListNode b = headB;
        while (!(a == null && b == null)) {
            if (a != null) {
                boolean isAdd = set.add(a);
                if (!isAdd) {
                    return a;
                }
                a = a.next;
            }
            if (b != null) {
                boolean isAdd = set.add(b);
                if (!isAdd) {
                    return b;
                }
                b = b.next;
            }
        }
        return null;
    }
}

// nodeA nodeB同时遍历 遍历完各自的后 再遍历对方的如果有交点就相同 反之同时为null
class Solution160B {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode nodeA = headA;

        ListNode nodeB = headB;

        while (nodeA != nodeB) {

            if (nodeA == null) {

                nodeA = headB;

            } else {
                nodeA = nodeA.next;
            }

            if (nodeB == null) {

                nodeB = headA;

            } else {
                nodeB = nodeB.next;
            }

        }

        return nodeA;

    }
}