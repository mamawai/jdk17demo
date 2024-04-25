package com.example.demo.algorithm.leetcode.每日一题.easy;

public class 总行驶距离 {
    public static void main(String[] args) {

    }
}
class Solution2739 {
    public int distanceTraveled(int mainTank, int additionalTank) {
        int used = 0;
        int totalUsed = 0;
        while (mainTank > 0) {
            mainTank --;
            used++;
            if (used == 5 && additionalTank > 0) {
                mainTank++;
                additionalTank--;
                totalUsed += used;
                used = 0;
            }
        }
        totalUsed += used;
        return totalUsed * 10;
    }
}
