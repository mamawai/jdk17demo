package com.example.demo.algorithm.leetcode.每日一题.medium;

public class 给植物浇水 {
    public static void main(String[] args) {
        int i = new Solution2079().wateringPlants(new int[]{2, 2, 3, 3}, 5);
        System.out.println(i);
    }
}

class Solution2079 {
    public int wateringPlants(int[] plants, int capacity) {
        int curCapacity = capacity;
        int stepCount = 0;
        for (int i = 0; i < plants.length; i++) {
            // 判断够不够浇水
            if (curCapacity < plants[i]) {
                curCapacity = capacity;
                // 回河边取水
                stepCount += 2 * i;
            }
            // 走到植物 一步
            stepCount++;
            // 浇水
            curCapacity -= plants[i];
        }
        return stepCount;
    }
}