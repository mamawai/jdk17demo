package com.example.demo.algorithm.B;

import java.util.*;

public class 数据分类 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
//        System.out.println(Integer.toHexString(256));
    }

    private static int getResult(String s) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = Arrays.stream(s.split(" ")).map(Integer::parseInt).toList();
        int c = list.get(0);
        int b = list.get(1);
        for (int i = 2; i<list.size(); i++) {
            StringBuilder hexString = new StringBuilder(Integer.toHexString(list.get(i)));
            int fixLength = 8 - hexString.length();
            if (fixLength != 0) {
                while (hexString.length() < 8) {
                    hexString.insert(0, "0");
                }
            }
            int total = 0;
            for (int j = 1;j < 8;j+=2) {
                char c1 = hexString.charAt(j - 1);
                char c2 = hexString.charAt(j);
                String s1 = String.valueOf(c1);
                String s2 = String.valueOf(c2);
                int decimal = Integer.parseInt(s1+s2,16);
                total += decimal;
            }
            int compareToC = total % b;
            if (compareToC < c) {
                map.put(compareToC, map.getOrDefault(compareToC, 0) + 1);
            }
        }
        return map.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getValue();
    }
}
