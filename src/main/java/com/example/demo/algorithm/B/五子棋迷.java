package com.example.demo.algorithm.B;

import java.util.*;

public class 五子棋迷 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String l = sc.nextLine();
        System.out.println(getResult(s,l));
    }

    private static int getResult(String s, String l) {
        int position = -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        String[] split = l.split(" ");
        int mid = split.length / 2;
        // 这里要加2才行才比中点的距离大一，因为上面的mid算出来是脚标从0开始的。
        int minDis = mid+2;
        int length = 0;
        int defaultLength = 0;
        int defaultCount = 0;
        // 统计当前split中最长目标字符长度
        for (String value : split) {
            if (value.equals(s)) {
                defaultCount++;
            } else {
                defaultLength = Math.max(defaultLength, defaultCount);
                defaultCount = 0;
            }
        }
        defaultLength = Math.max(defaultLength, defaultCount);

        for (int i = 0; i<split.length; i++) {
            if (split[i].equals("0")) {
                length++;
                int tmp = i;
                // 向后找
                while (++i<split.length) {
                    // 如果0后的是目标字符那么length加一
                    if (split[i].equals(s)) {
                        length++;
                    } else {
                        // 如果不是目标字符 跳出while
                        break;
                    }
                }
                i = tmp;
                //向前找
                while (--i>=0) {
                    if (split[i].equals(s)) {
                        length++;
                    } else {
                        // 如果不是目标字符 跳出while
                        break;
                    }
                }
                i = tmp;
                // 超过五则说明该点不能下
                if(length <= 5) {
                    map.put(tmp,length);
                }
                length = 0;
            }
        }
        if (map.size() == 0) {
            return -1;
        } else {
            Integer maxLength = map.values().stream().max(Comparator.comparingInt(o -> o)).get();
            if (defaultLength >= maxLength) {
                return -1;
            } else {
                Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
                for (Map.Entry<Integer, Integer> entry : entries) {
                    if (entry.getValue().equals(maxLength)) {
                        int distance = Math.abs(entry.getKey() - mid -1);
                        if (minDis > distance) {
                            minDis = distance;
                            position = entry.getKey();
                        }
                    }
                }
            }
        }
       return position;
    }
}
