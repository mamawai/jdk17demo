package com.example.demo.algorithm.B;



import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class 组成最大数 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String[] arr = sc.next().split(",");
//        Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));
//
//        StringBuilder sb = new StringBuilder();
//        for (String s : arr) sb.append(s);
//        System.out.println(sb);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(132);
        integers.add(11);
        integers.add(22);
        integers.add(44);
        integers.add(99);
        Integer reduce = integers.stream().sorted((o1, o2) -> o2 - o1).
                limit(3).reduce(0, Integer::sum);
        System.out.println(reduce);

    }
}

