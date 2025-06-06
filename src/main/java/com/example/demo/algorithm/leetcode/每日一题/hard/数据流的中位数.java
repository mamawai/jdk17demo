package com.example.demo.algorithm.leetcode.每日一题.hard;

import java.util.*;

public class 数据流的中位数 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}

class MedianFinder {

    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>((o1, o2) -> o1 - o2);
        right = new PriorityQueue<>((o1, o2) -> o2 - o1);

    }

    public void addNum(int num) {
        if (left.isEmpty() || num >= left.peek()) {
            left.offer(num);
            if (right.size() + 1 < left.size()) {
                right.offer(left.poll());
            }
        } else {
            right.offer(num);
            if (right.size() > left.size()) {
                left.offer(right.poll());
            }
        }
    }

    public double findMedian() {
        if (left.size() > right.size()) return (double) left.peek();
        else return (double) (left.peek() + right.peek()) / 2;
    }
}