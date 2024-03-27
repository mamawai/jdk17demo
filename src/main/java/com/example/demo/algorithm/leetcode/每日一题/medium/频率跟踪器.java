package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.HashMap;
import java.util.Map;

public class 频率跟踪器 {
    public static void main(String[] args) {
        FrequencyTracker frequencyTracker = new FrequencyTracker();
        frequencyTracker.add(5);
        frequencyTracker.add(5);
        frequencyTracker.hasFrequency(1);
    }
}

class FrequencyTracker {

    int[] arr;
    int[] fre;

    public FrequencyTracker() {
        arr = new int[100001];
        fre = new int[100001];
    }

    public void add(int number) {
        if (arr[number] != 0) fre[arr[number]]--;
        fre[arr[number] + 1] ++;
        arr[number] ++;
    }

    public void deleteOne(int number) {
        if (arr[number] > 0) {
            fre[arr[number]] --;
            fre[arr[number] - 1] ++;
            arr[number] --;
        }

    }

    public boolean hasFrequency(int frequency) {
        return fre[frequency] > 0;
    }
}
