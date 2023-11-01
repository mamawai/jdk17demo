package com.example.demo.algorithm.B;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * OD123
 */
public class 分积木 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(sc.nextLine(), n));
    }

    private static String getResult(String s, int n) {
        int res = 0;
        String resString = "";
        String[] split = s.split(" ");
        List<Integer> list = Arrays.stream(split).map(Integer::parseInt).collect(Collectors.toList());
        for (Integer integer : list) {
            int binaryInt = Integer.parseInt(Integer.toBinaryString(integer));
            if (res == 0){
                res += binaryInt;
            } else {
                res += binaryInt;
                String binaryString = String.valueOf(res);
                resString = binaryString.replace('2', '0');
                res = Integer.parseInt(resString);
            }
        }
        if (res == 0) {
            // 说明可以分
            Integer integer = list.stream().min(Comparator.comparingInt(o -> o)).get();
            list.remove(integer);
            return String.valueOf(list.stream().reduce(0, Integer::sum));
        }
        return "NO";
    }
}
