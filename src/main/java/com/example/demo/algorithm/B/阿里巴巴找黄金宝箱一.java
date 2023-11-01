package com.example.demo.algorithm.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * OD136
 */
public class 阿里巴巴找黄金宝箱一 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    private static int getResult(String s) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.addAll(Arrays.stream(s.split(",")).map(Integer::parseInt).toList());
        list.add(0);
        Integer total = list.stream().reduce(0, Integer::sum);
        int current = 0;
        for (int i = 1; i < list.size() - 1  ;i++) {
            if ((total - list.get(i)) % 2 != 0) {
                current += list.get(i);
                continue;
            }
            // 若能被2整除
            if (current == (total - list.get(i)) / 2) {
                return i - 1;
            }
            current += list.get(i);
        }
        return -1;
    }
}
