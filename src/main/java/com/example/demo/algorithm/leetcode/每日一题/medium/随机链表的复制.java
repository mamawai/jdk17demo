package com.example.demo.algorithm.leetcode.每日一题.medium;

import com.example.demo.algorithm.leetcode.structure.NodeRandom;
import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 随机链表的复制 {
    public static void main(String[] args) {

    }
}

class Solution {
    public NodeRandom copyRandomList(NodeRandom head) {
        if (head == null) return null;
        List<NodeRandom> nrs = new ArrayList<>();
        int pos = 0;
        NodeRandom dummy = new NodeRandom(0);
        dummy.next = head;
        while (head != null) {
            NodeRandom nodeRandom = new NodeRandom(head.val);
            nrs.add(nodeRandom);
            if (pos > 0) {
                nrs.get(pos - 1).next = nodeRandom;
            }
            pos++;
            head = head.next;
        }
        head = dummy.next;
        Map<NodeRandom, NodeRandom> map = new HashMap<>();
        pos = 0;
        while (head != null) {
            map.put(head, nrs.get(pos++));
            head = head.next;
        }

        head = dummy.next;
        pos = 0;
        while (head != null) {
            nrs.get(pos++).random = map.get(head.random);
            head = head.next;
        }
        return nrs.get(0);
    }
}