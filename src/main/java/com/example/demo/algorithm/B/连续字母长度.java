package com.example.demo.algorithm.B;

import java.util.*;


/**
 * OD251
 */
public class 连续字母长度 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int k = Integer.parseInt(sc.nextLine());
        System.out.println(getResult(s,k));
    }

    private static int getResult(String s, int k) {
        HashMap<Character,Integer> map = new HashMap<>();
        char lastChar = '0';
        int times = 1;
        for (int i = 0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == lastChar) {
                times++;
            } else {
                if (lastChar != '0') {
                    Integer orDefault = map.getOrDefault(lastChar, 0);
                    if (orDefault == 0) {
                        map.put(lastChar, times);
                    } else {
                        map.put(lastChar, Math.max(orDefault, times));
                    }
                    times = 1;
                }
                lastChar = c;
            }
        }

        // 这里将最后一组字母put到map
        Integer orDefault = map.getOrDefault(lastChar, 0);
        if (orDefault == 0) {
            map.put(lastChar, times);
        } else {
            map.put(lastChar, Math.max(orDefault,times));
        }

        if (k > map.size()) {
            return -1;
        }
        List<Map.Entry<Character, Integer>> list = map.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).toList();

        return list.get(k - 1).getValue();
    }
}
