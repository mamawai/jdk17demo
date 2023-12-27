package com.example.demo.algorithm.leetcode.每日一题;

public class 收集足够苹果的最小花园周长 {
    public static void main(String[] args) {
        long l = new Solution1954().minimumPerimeter(2784381467700L);
        System.out.println(l);
    }
}

class Solution1954 {
    public long minimumPerimeter(long neededApples) {
        // 边长长度为2才能包括中心0，0点，也就是说一半的长度最小要为1
        long ansHl = 1L;

        // 2*1*halfLong + 2*2*halfLong + 2*n*halfLong = hl^2 * (1+hl)
        // 31 32 33
        // 21 22 23
        // 11 12 13
        // 四角加上四条线即 4 * hl^2 * (1+hl) + 2 * (1 + hl) * hl = 2hl(1+hl)(2hl+1)
        // ans = 8hl
        long r = (long) Math.pow((double) neededApples / 2, 1.0 / 3.0);
        long l = 1L;
        while (l <= r) {
            long mid = (l + r) >> 1;
            if (2*mid*(1+mid)*(2*mid+1) >= neededApples) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return 8 * l;
    }
}
class SolutionB1954 {
    public long minimumPerimeter(long neededApples) {
        long ansHl = 1L;
        while (true) {
            if (2*ansHl*(1+ansHl)*(2*ansHl+1) < neededApples) {
                ansHl++;
            } else {
                return 8 * ansHl;
            }
        }
    }
}