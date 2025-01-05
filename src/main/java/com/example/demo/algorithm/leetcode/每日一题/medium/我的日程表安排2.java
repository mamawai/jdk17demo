package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.List;

public class 我的日程表安排2 {
    public static void main(String[] args) {
//        MyCalendarTwo3 mc2 = new MyCalendarTwo3();
//        mc2.book(10, 20);
//        mc2.book(50,60);
//        mc2.book(10,40);


        MyCalendarTwo2 mc2 = new MyCalendarTwo2();
        mc2.book(10, 20);
        mc2.book(50,60);
        mc2.book(10,40);
    }
}

class MyCalendarTwo {
    List<int[]> first;
    List<int[]> second;

    public MyCalendarTwo() {
        first = new ArrayList<>();
        second = new ArrayList<>();
    }

    public boolean book(int startTime, int endTime) {
        int realEndTime = endTime - 1;
        if (first.isEmpty() && second.isEmpty()) {
            // 第一次插入
            first.add(new int[]{startTime, realEndTime});
            return true;
        }
        // 先从2中遍历 如果有覆盖直接返回false 
        // 再从1中遍历 如果有覆盖 更新覆盖区间到2中 1中正常插入即可因为 如果2中覆盖的没有遍历到 1中覆盖的肯定没有
        if (!second.isEmpty()) {
            for (int[] itv2 : second) {
                if (!(startTime > itv2[1] || realEndTime < itv2[0])) return false;
            }
        }
        if (!first.isEmpty()) {
            for (int[] itv1 : first) {
                if (!(startTime > itv1[1] || realEndTime < itv1[0])) {
                    // 覆盖区间
                    int[] covered = new int[]{Math.max(startTime, itv1[0]), Math.min(realEndTime, itv1[1])};
                    first.add(covered);
                }
            }
        }
        first.add(new int[]{startTime, realEndTime});
        return true;
    }
}


class MyCalendarTwo2 {
    List<int[]> first;
    List<int[]> second;

    public MyCalendarTwo2() {
        first = new ArrayList<int[]>();
        second = new ArrayList<int[]>();
    }

    public boolean book(int startTime, int endTime) {
        int realEndTime = endTime - 1;
        // 先从2中遍历 如果有覆盖直接返回false
        // 再从1中遍历 如果有覆盖 更新覆盖区间到2中 1中正常插入即可 因为如果2中覆盖的没有遍历到 1中两个数组覆盖的肯定没有被startTime和realEndTime包含
        for (int[] arr : second) {
            if (!(startTime > arr[1] || realEndTime < arr[0])) return false;
        }
        for (int[] arr : first) {
            if (!(startTime > arr[1] || realEndTime < arr[0])) {
                // 覆盖区间
                second.add(new int[]{Math.max(startTime, arr[0]), Math.min(realEndTime, arr[1])});
            }
        }
        first.add(new int[]{startTime, realEndTime});
        return true;
    }
}