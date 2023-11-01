package com.example.demo.algorithm.B;

import java.util.*;

/**
 * OD157
 */
public class 阿里巴巴找黄金宝箱二 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] ints = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(ints));
    }

    private static int getResult(int[] ints) {
        // 存入次数和数字
        HashMap<Integer, Integer> map = new HashMap<>();
        int totalTimes = 0;
        for (int num : ints) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            totalTimes++;
        }

        int times = 0;
        int m = 0;

        List<Map.Entry<Integer, Integer>> entryList = map.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).toList();

        for (Map.Entry<Integer,Integer> entry : entryList) {
            if (entry.getValue() + times >= totalTimes/2){
                m++;
                break;
            } else {
                m++;
                times += entry.getValue();
            }
        }

        return m;
    }
}
