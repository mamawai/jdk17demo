package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class 统计将重叠区间合并成组的方案数 {
    public static void main(String[] args) {
        int[][] a = {{10,11},{25,28},{29,34},{49,51},{58,66},{77,84},{91,91}
                ,{100,104},{120,121},{127,128},{151,151},{156,168},{0,8}};
        int[][] b = {{1,3},{10,20},{2,5},{4,8}};
        int i = new Solution2580().countWays(b);
        System.out.println(i);
    }
}

class Solution2580 {
    private final int mod = 1000000007;
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, Comparator.comparingInt(a -> a[1]));
//        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
//        for (int[] range : ranges) {
//            if (queue.isEmpty()) {
//                queue.offer(range);
//                continue;
//            }
//            if (range[0] > queue.peek()[1]) {
//                // 区间不重叠 直接offer
//                queue.offer(range);
//            } else {
//                // 区间重叠 需要一直合并和修改下标直到区间不重叠
//                int[] lastPolled = null;
//                while (!queue.isEmpty() && (queue.peek()[0] >= range[0] || queue.peek()[1] >= range[0])) {
//                    lastPolled = queue.poll();
//                }
//                if (lastPolled[0] < range[0]) {
//                    queue.offer(new int[]{lastPolled[0], range[1]});
//                } else {
//                    queue.offer(range);
//                }
//            }
//        }
        int res = 1;
        for (int i = ranges.length - 1; i >= 0;) {
            int r = ranges[i][0];
            int j = i - 1;
            while (j >= 0 && r <= ranges[j][1]) {
                r = Math.min(r, ranges[j][0]);
                j --;
            }
            res = res * 2 % mod;
            i = j;
        }
//        int size = queue.size();

//        for (int i = 0; i < size; i++) {
//            res = res * 2 % mod;
//        }
        return res;
    }
}