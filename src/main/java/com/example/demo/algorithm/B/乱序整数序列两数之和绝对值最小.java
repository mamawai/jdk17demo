package com.example.demo.algorithm.B;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * OD238
 */
public class 乱序整数序列两数之和绝对值最小 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    private static String getResult(String s) {
        String[] split = s.split(" ");
        List<Integer> list = Arrays.stream(split).map(Integer::parseInt).sorted().toList();
        int minAbs = 65535;
        int x = 0;
        int y = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size();j++) {
                if (list.get(i) > 0 && i+1 <= list.size() - 1) {
                    if (minAbs > Math.abs(list.get(i)+list.get(i+1))) {
                        minAbs = Math.abs(list.get(i)+list.get(i+1));
                        x = list.get(i);
                        y = list.get(i+1);
                    }
                    return x+" "+y+" "+ minAbs;
                }
                if (minAbs > Math.abs(list.get(i)+list.get(j))) {
                    minAbs = Math.abs(list.get(i)+list.get(j));
                    x = list.get(i);
                    y = list.get(j);
                }
            }
        }
        return x+" "+y+" "+ minAbs;
    }
}
