package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 给植物浇水2 {
    public static void main(String[] args) {

    }
}

class Solution2105 {
    public int minimumRefill(int[] plants, int capacityA, int capacityB) {
        int fillTimes = 0;
        int curA = capacityA;
        int curB = capacityB;
        int i = 0, j = plants.length - 1;
        while (j > i) {
            if (curA >= plants[i]) {
                curA -= plants[i];
            } else {
                curA = capacityA - plants[i];
                fillTimes++;
            }
            if (curB >= plants[j]) {
                curB -= plants[j];
            } else {
                curB = capacityB - plants[j];
                fillTimes++;
            }
            j--;
            i++;
        }
        if (i == j) {
            if (curA == curB) {
                if (curA < plants[i]) {
                    fillTimes++;
                }
            } else {
                if (Math.max(curA, curB) < plants[i]) {
                    fillTimes++;
                }
            }
        }
        return fillTimes;
    }
}