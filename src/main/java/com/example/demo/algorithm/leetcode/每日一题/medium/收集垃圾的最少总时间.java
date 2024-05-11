package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class 收集垃圾的最少总时间 {
    public static void main(String[] args) {
        int i = new Solution2391().garbageCollection(new String[]{"G", "P", "GP", "GG"}, new int[]{2, 4, 3});
        System.out.println(i);
    }
}

class Solution2391 {
    public int garbageCollection(String[] garbage, int[] travel) {
        int travelTime = 0;
        int mIndex = -1, pIndex = -1, gIndex = -1;
        for (int i = 0; i < garbage.length; i++) {
            String g = garbage[i];
            if (g.indexOf("M") < 0) mIndex = i - 1;
            if (g.indexOf("P") < 0) pIndex = i - 1;
            if (g.indexOf("G") < 0) gIndex = i - 1;
            travelTime += g.length();
            if (i >= 1 && i < garbage.length - 1) travel[i] = travel[i - 1] + travel[i];
        }
        if (mIndex >= 0) travelTime += travel[mIndex];
        if (pIndex >= 0) travelTime += travel[pIndex];
        if (gIndex >= 0) travelTime += travel[gIndex];
        return travelTime;
    }
}