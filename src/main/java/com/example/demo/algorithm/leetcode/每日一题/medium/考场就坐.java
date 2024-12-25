package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 考场就坐 {
    public static void main(String[] args) {
        //  1 2  4 5 6
        ExamRoom room = new ExamRoom(10);
        System.out.println(room.seat());
        System.out.println(room.seat());
        System.out.println(room.seat());
        System.out.println(room.seat());
        room.leave(4);
        System.out.println(room.seat());
//        System.out.println(room.seat());
//        System.out.println(room.seat());
//        room.leave(0);
//        room.leave(4);
//        System.out.println(room.seat());
//        System.out.println(room.seat());
    }
}


class ExamRoom {
    PriorityQueue<int[]> pq; // 构建大顶堆 [i,j] 表示首尾区间
    int n;
    public ExamRoom(int n) {
        this.n = n;
        // 初始化 构建小顶堆排序方式，要注意当数组存在边界点时要取总长度比较
        pq = new PriorityQueue<>((o1, o2) -> {
            int len1 = (o1[1] - o1[0]) / 2, len2 = (o2[1] - o2[0]) / 2;
            if (o1[0] == 0 || o1[1] == n - 1) len1 = o1[1] - o1[0];
            if (o2[0] == 0 || o2[1] == n - 1) len2 = o2[1] - o2[0];
            if (len1 == len2) return o1[0] - o2[0];
            return len2 - len1;
        });
        pq.offer(new int[]{0, n - 1});
    }

    public int seat() {
        int[] cur = pq.poll();
        int left = cur[0], right = cur[1];
        // 如果坐在边界点 只需要offer一个数组
        if (left == 0) {
            int[] r = {left + 1, right};
            if (r[1] >= r[0]) pq.offer(r);
            return 0;
        } else if (right == n - 1) {
            int[] l = {left, right - 1};
            if (l[1] >= l[0]) pq.offer(l);
            return n - 1;
        } else {
            // 非边界点offer两个数组
            int mid = (left + right) / 2;
            int[] l = {left, mid - 1}, r = {mid + 1, right};
            // 保证l或r下标1处的值不小于0处的值 如[1,0]不合理不offer
            if (l[1] >= l[0]) pq.offer(l);
            if (r[1] >= r[0]) pq.offer(r);
            return mid;
        }
    }

    public void leave(int p) {
        List<int[]> toRemove = new ArrayList<>();
        for (int[] each : pq) {
            if (each[0] - 1 == p || each[1] + 1 == p) toRemove.add(each);
            if ((p == 0 || p == n - 1) && toRemove.size() == 1 || toRemove.size() == 2) break; // 剪枝
        }
        // 如果没有要remove的片段说明都坐满了，只需要offer即可
        if (toRemove.size() == 0) {
            pq.offer(new int[]{p, p});
            return;
        }
        pq.removeAll(toRemove);
        // 合并remove的分支
        int min,max;
        if (toRemove.size() == 1) {
            min = Math.min(toRemove.get(0)[0], p);
            max = Math.max(toRemove.get(0)[1], p);
        }
        else {
            min = Math.min(toRemove.get(0)[0], toRemove.get(1)[0]);
            max = Math.max(toRemove.get(1)[1], toRemove.get(0)[1]);
        }
        pq.offer(new int[]{min, max});
    }
}