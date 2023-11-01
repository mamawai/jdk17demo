package com.example.demo.algorithm.B;

import java.util.*;
import java.util.stream.Collectors;

/**
 * OD326
 */
public class 荒岛求生 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    private static int getResult(String s) {
        List<Integer> collect = Arrays.stream(s.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        boolean needBattle = true;
        while (needBattle) {
            int left = 0;
            int right = 1;
            needBattle = false;
            for (; right < collect.size(); left++, right++) {
                Integer leftValue = collect.get(left);
                Integer rightValue = collect.get(right);
                if (leftValue == 0 || rightValue == 0) {
                    return -1;
                }
                if (leftValue > 0 && rightValue< 0) {
                    needBattle = true;
                    int res = leftValue + rightValue;
                    if (res > 0) {
                        collect.set(left--, res);
                        collect.remove(right--);
                    } else if (res == 0) {
                        collect.remove(left);
                        left = left == 0 ? left -1: left -2;
                        collect.remove(--right);
                        right--;
                    } else {
                        // 如果结果是负数那么不需要再看res和下一位了 直接右移左右这两个指针
                        collect.set(left, res);
                        collect.remove(right);
                    }
                }
            }
        }
        return collect.size();
    }
}
