package com.example.demo.test;

import java.util.HashMap;
import java.util.Map;

public class 区间计数 {
    public static void main(String[] args) {
        int i = new XorSubarrayCount().countXorIntervals(new int[]{1, 2, 3, 2, 1}, 2);
        System.out.println(i);
    }
}

class XorSubarrayCount {
    public int countXorIntervals(int[] a, int x) {
        int ans = 0; // 最终结果计数
        int prefix = 0; // 当前的前缀异或和

        // 哈希表记录每个前缀异或和的奇偶出现频率
        Map<Integer, int[]> prefixCount = new HashMap<>();
        prefixCount.put(0, new int[]{1, 0}); // 初始化前缀和为0在偶数位置的情况

        for (int i = 0; i < a.length; i++) {
            prefix ^= a[i]; // 更新当前前缀异或和

            // 计算目标前缀和，使得 prefix[j] ^ prefix[i] = x
            int target = prefix ^ x;

            // 累加满足条件的区间数目
            if (prefixCount.containsKey(target)) {
                ans += prefixCount.get(target)[(i + 1) % 2];
            }

            // 更新当前前缀和的奇偶出现次数
            prefixCount.putIfAbsent(prefix, new int[]{0, 0});
            prefixCount.get(prefix)[(i + 1) % 2]++;
        }

        return ans;
    }
}