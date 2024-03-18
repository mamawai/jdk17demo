package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.*;

public class 数位和相等数对的最大和 {
    public static void main(String[] args) {
        int i = new Solution2342B().maximumSum(new int[]{18, 43, 36, 13, 7, 34});
        System.out.println(i);
    }
}

class Solution2342 {
    public int maximumSum(int[] nums) {
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int n : nums) {
            int sum = 0;
            int m = n;
            while(m>0) {
                sum += m % 10;
                m /= 10;
            }
            Queue<Integer> orDefault = map.getOrDefault(sum, new PriorityQueue<>((o1, o2) -> o2 - o1));
            orDefault.offer(n);
            map.put(sum, orDefault);
        }
        int ans = -1;
        for (Map.Entry<Integer, Queue<Integer>> entry : map.entrySet()) {
            Queue<Integer> value = entry.getValue();
            if (value.size() >= 2) {
                ans = Math.max(ans, value.poll() + value.poll());
            }
        }
        return ans;
    }
}

class Solution2342B {
    public int maximumSum(int[] nums) {
        int ans = -1;
        int supArr[] = new int[82];
        Arrays.fill(supArr, 0, 82, 0);
        for(int num : nums){
            int s = 0;
            for(int n1 = num; n1 > 0; n1 /= 10){
                s += n1 % 10;
            }
            if(supArr[s] > 0){
                ans = Math.max(ans, supArr[s] + num);
            }
            supArr[s] = Math.max(num, supArr[s]);
        }
        return ans;
    }
}