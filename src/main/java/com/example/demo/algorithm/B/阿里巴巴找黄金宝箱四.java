package com.example.demo.algorithm.B;

import java.util.*;
import java.util.stream.Collectors;

/**
 * OD138
 */
public class 阿里巴巴找黄金宝箱四 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long start = System.currentTimeMillis();
        int[] ints = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getResult(ints));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    private static String getResult(int[] ints) {
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        StringJoiner sj = new StringJoiner(",");
        for (int i = 0; i < ints.length; i++) {
            int num = ints[i];
            // 构造新的list
            List<Integer> arrayList = new ArrayList<>();
            if (i>0) {
                List<Integer> subbed = list.subList(0, i);
                List<Integer> removed =  list.subList(i,list.size());
                arrayList.addAll(removed);
                arrayList.addAll(subbed);
            } else {
                arrayList = list;
            }
            boolean flag = false;
            for (Integer integer : arrayList) {
                if (integer > num) {
                    flag = true;
                    sj.add(integer.toString());
                    break;
                }
            }
            if (!flag) {
                sj.add("-1");
            }
        }
        return sj.toString();
    }
}
