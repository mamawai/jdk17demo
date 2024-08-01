package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class 棒球比赛 {
    public static void main(String[] args) {

    }
}

class Solution682 {
    public int calPoints(String[] operations) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (String operation : operations) {
            switch (operation) {
                case "C" -> deque.removeLast();
                case "D" -> deque.add(deque.getLast() * 2);
                case "+" -> {
                    int last = deque.pollLast();
                    int newLast = last + deque.getLast();
                    deque.add(last);
                    deque.add(newLast);
                }
                default -> deque.add(Integer.parseInt(operation));
            }
        }
        int res = 0;
        for (int e : deque) res += e;
        return res;
    }
}