package com.example.demo.algorithm.leetcode.热题100.hard;

import com.example.demo.algorithm.leetcode.structure.ListNode;

import java.util.ArrayList;
import java.util.List;

public class K个一组反转链表 {

    public static void main(String[] args) {
        new Solution25B().reverseKGroup(new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(5))))),0);
    }
}

class Solution25 {
    // 一点点来先反转全部
    // 5 4 3 2 1
    // 1 2 3 4 5
    ListNode dummy;
    ListNode linkHead;
    List<ListNode[]> nodeList;
    int oriK;
    public ListNode reverseKGroup(ListNode head, int k) {
        oriK = k;
        nodeList = new ArrayList<>();
        dummy = new ListNode();
        dummy.next = head;
        dfs(head, k - 1);
        return dummy.next;
    }

    private void dfs(ListNode node, int k) {
        // 获得node节点下一个节点next
        ListNode next = node.next;
        // 连接next 更新dummy
        if (dummy.next != node) {
            next.next = dummy.next;
            dummy.next = next;
        }
        // 当node为每个片区第一个节点时才更新linkHead
        if (k == oriK - 1) linkHead = node;




        // 断开node下一个节点
        node.next = null;
        // 获取第三个节点的引用
        ListNode skip = next.next;
        // 它的下一个节点指向head即dummy.next
        next.next = dummy.next;
        dummy.next = next;
        k--;
        if (k == 0) {
            nodeList.add(new ListNode[]{dummy, linkHead});
            dfs();
        } else
    }
}
class Solution25B {
    // 一点点来先反转全部
    // 5 4 3 2 1
    // 1 2 3 4 5
    ListNode dummy;
    List<ListNode[]> nodeList;
    int oriK;
    public ListNode reverseKGroup(ListNode head, int k) {
        oriK = k;
        nodeList = new ArrayList<>();
        dummy = new ListNode();
        dummy.next = head;
        // head/head.next
        nodeList.add(new ListNode[]{dummy.next, dummy.next.next});
        dummy = dummy.next;
        nodeList.add(new ListNode[]{dummy.next, dummy.next.next});
        return new ListNode();
    }
}