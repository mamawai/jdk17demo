package com.example.demo.algorithm.B;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * OD147
 */
public class 分苹果 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        List<Integer> list = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toList();
        System.out.println(getResult(list,n));
    }

    private static int getResult(List<Integer> list, int n) {
        Integer reduce = list.stream().reduce(0, (integer, integer2) -> integer ^ integer2);
        if (reduce != 0) {
            return  -1;
        } else {
            Integer total = list.stream().reduce(0, Integer::sum);
            return total - list.stream().sorted().toList().get(0);
        }
    }
}
