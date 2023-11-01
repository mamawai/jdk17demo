package com.example.demo.algorithm.A;

import java.util.*;

/**
 * OD005
 */

public class 最多颜色的车辆 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String carColor = sc.nextLine();
        int seconds = sc.nextInt();
        System.out.println(getResult(carColor, seconds));
    }

    private static int getResult(String carColor, int seconds) {
        int max = 0;
        int tmp;
        String carString = carColor.replace(" ", "");
        // seconds 就是搜索长度

        // 此时就找carArray中 012 出现最多数字的次数
        if (seconds >= carString.length()) {
            max = findMaxTime(carString);
        } else {
            // 此时就是定长的一个区间 去找这个区间中 012 出现次数最多的数字的次数 0112
            for (int left = 0; left + seconds <= carString.length(); left++) {
                String newCarString = carString.substring(left, left + seconds);
                tmp =  findMaxTime(newCarString);
                if (tmp > max) {
                    max = tmp;
                }
            }
        }
        return max;
    }

    private static int findMaxTime(String carString) {
        HashMap<String, Integer> map = new HashMap<>();
        Arrays.stream(carString.split("")).forEach(s -> {
            map.put(s, map.getOrDefault(s,0)+1);
        });
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        return entries.stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getValue();
    }
}
