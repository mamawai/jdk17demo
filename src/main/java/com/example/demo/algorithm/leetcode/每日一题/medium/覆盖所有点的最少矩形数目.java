package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 覆盖所有点的最少矩形数目 {
    public static void main(String[] args) {
        Solution3111 solution = new Solution3111();
        int[][] points = {{0,0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5},{6,6}};
        int w = 2;
        System.out.println(solution.minRectanglesToCoverPoints(points, w));
    }
}

class Solution3111 {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        // 高度不用考虑
        int ans = 0;
        Set<Integer> set = new TreeSet<>();
        for (int[] point : points) set.add(point[0]);
        Deque<Integer> stack = new LinkedList<>();
        int rest = w;
        for (int point : set) {
            if (stack.isEmpty()){
                stack.push(point);
            } else {
                if (point - stack.peek() > rest) {
                    rest = w;
                    ans++;
                    stack = new LinkedList<>();
                    stack.push(point);
                } else {
                    rest -= point - stack.peek();
                    stack.push(point);
                }
            }
        }
        if (!stack.isEmpty()) ans++;
        return ans;
    }
}

class Solution3111B {
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Arrays.sort(points, (p, q) -> p[0] - q[0]);
        int ans = 0;
        int x2 = -1;
        for (int[] p : points) {
            if (p[0] > x2) {
                ans++;
                x2 = p[0] + w;
            }
        }
        return ans;
    }
}