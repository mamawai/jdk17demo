package com.example.demo.algorithm.leetcode.每日一题.easy;

import java.util.*;

public class 使数组元素互不相同所需的最少操作次数 {
    public static void main(String[] args) {
        int i = new Solution3396().minimumOperations(new int[]{3,7,7,3});
        System.out.println(i);
        List<Double> doubles = allocateRedEnvelop(100, 10);
        System.out.println(doubles);
    }

    /**
     * 线段切割法
     */
    public static List<Double> allocateRedEnvelopNew(double totalMoney, int people) {
        // 转换为分处理避免浮点误差
        double totalCents = Math.round(totalMoney * 100);
        // 总金额的90%
        Random random = new Random();
        List<Double> result = new ArrayList<>();
        Set<Integer> pointCutSet = new HashSet<>();
        while (pointCutSet.size() < people - 1) {
            //生成n - 1个切割点，随机点取值范围是[1, totalCents]
            pointCutSet.add(random.nextInt((int) totalCents) + 1);
        }
        //接着生成对应子线段的钱数
        Integer[] points = pointCutSet.toArray(new Integer[0]);
        Arrays.sort(points);
        result.add(points[0] / 100.0);
        //子线段+ 最后那段的长度 = totalCents，注意上一步是已经加了points[0]，result中的所有元素和累加后的结果一定是totalCents,
        for (int i = 1; i < points.length; i++) {
            result.add((points[i] - points[i - 1]) / 100.0);
        }
        result.add((totalCents - points[points.length - 1]) / 100.0);
        return result;
    }

    public static List<Double> allocateRedEnvelop(double totalMoney, int people) {
        // 转换为分处理避免浮点误差
        double totalCents = Math.round(totalMoney * 100);
        double maxLimit = (totalCents * 0.9); // 总金额的90%
        Random random = new Random();
        double leaveMoney = totalCents;
        List<Double> result = new ArrayList<>();
        int n = people;
        //注意是大于1，最后1个人领取剩余的钱
        while (n > 1) {
            //生成随机金额的范围是[1, leaveMoney / n * 2 - 1]， 注意nextInt方法生成结果范围是左闭右开的
            double allocatMoney = 1 + random.nextInt((int)leaveMoney / n * 2 - 1);
            result.add(allocatMoney / 100.0);
            n--;
            leaveMoney -= allocatMoney;
        }
        result.add(leaveMoney / 100.0);
        return result;
    }

}

class Solution3396 {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = n - 1; i >= 0 ; i--) {
            if (!set.add(nums[i])) {
                int l = n - 1 - i;
                return (n - l) % 3 == 0 ? (n - l)/3:(n - l)/3+1;
            }
        }
        return 0;
    }
}