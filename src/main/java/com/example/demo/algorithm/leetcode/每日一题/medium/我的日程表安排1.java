package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class 我的日程表安排1 {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.book(48, 50);
        myCalendar.book(0, 6);
        myCalendar.book(6,13);
        myCalendar.book(8,13);
        myCalendar.book(15,23);
        myCalendar.book(49,50);

    }
}

class MyCalendar {
    List<int[]> routine;
    public MyCalendar() {
        routine = new ArrayList<>();
    }

    public boolean book(int startTime, int endTime) {
        if (routine.isEmpty()) {
            routine.add(new int[]{startTime, endTime - 1});
            return true;
        }

        int idx = -1;
        int spc = -1;
        for (int i = 0; i < routine.size(); i++) {
            int[] r = routine.get(i);
            if (!(startTime > r[1] || endTime - 1 < r[0])) return false;
            if (startTime == r[1] + 1 || endTime - 1 == r[0] - 1) {
                if (idx == -1) idx = i;
                else spc = i;
            }
        }
        // 如果idx不为-1, 说明可以合并区间
        if (idx != -1) {
            if (spc == -1) routine.set(idx, new int[]{Math.min(startTime, routine.get(idx)[0]), Math.max(endTime - 1, routine.get(idx)[1])});
            else routine.set(idx, new int[]{Math.min(routine.get(spc)[0], routine.get(idx)[0]), Math.max(routine.get(spc)[1], routine.get(idx)[1])});
        }
        else routine.add(new int[]{startTime, endTime - 1});

        if (spc != -1) routine.remove(spc);

        return true;
    }
}