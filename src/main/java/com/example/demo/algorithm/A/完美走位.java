package com.example.demo.algorithm.A;

import java.util.*;

/**
 * OD004
 */
public class 完美走位 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String zw = sc.nextLine();
        System.out.println(getResult(zw));
    }

    private static int getResult(String zw) {
        int allTimes = zw.length();
        // 平均每个字母出现的次数
        int avg = allTimes / 4;
        // 结果
        int ans = 0;

        HashMap<Character, Integer> countTimes = new HashMap<>();
        for (int i = 0; i<allTimes ;i++){
            // 将走位字符串出现的次数put到HashMap
            countTimes.put(zw.charAt(i), countTimes.getOrDefault(zw.charAt(i),0)+1);
        }
        if (countTimes.entrySet().stream().allMatch(characterIntegerEntry -> characterIntegerEntry.getValue() == avg)){
            // 此时完美走位 反之需要修改
            return 0;
        }

        HashMap<Character, Integer> overFlow = new HashMap<>();
        int overFlowLength = 0;
        // 统计countTimes里面超过avg的字符及数量
        // 最终找的就是包含overFlow字符及出现的次数最小的子串 如 WWWWAAAASSSS W1A1S1 就找包含WAS最小子串 即WAAAAS 为6位
        for (Map.Entry<Character, Integer> entry : countTimes.entrySet()) {
            if (entry.getValue() > avg) {
                overFlowLength += entry.getValue() - avg;
                overFlow.put(entry.getKey(), entry.getValue() - avg);
            }
        }

        // 定义当前窗口下put进去的元素即出现的次数
        HashMap<Character, Integer> currentTimes = new HashMap<>();
        // cnt维护zw串[left,right]中满足overFLow map中的元素的个数，记录相对应字符的总数
        int len=Integer.MAX_VALUE,cnt=0;
        // 滑动窗口
        for (int left = 0, right = 0; right < zw.length(); right++) {
            currentTimes.put(zw.charAt(right), currentTimes.getOrDefault(zw.charAt(right),0)+1);

            // 满足下面两个if就说明该字符需要被cnt记录
            if (overFlow.containsKey(zw.charAt(right))) {
                if (currentTimes.get(zw.charAt(right)) <= overFlow.get(zw.charAt(right))) {
                    cnt++;
                }
            }

            // left左移判断
            while(left < right && (!overFlow.containsKey(zw.charAt(left)) || currentTimes.get(zw.charAt(left)) > overFlow.get(zw.charAt(left)))){
                currentTimes.put(zw.charAt(left),currentTimes.get(zw.charAt(left)) - 1);
                left++;
            }
            if (cnt==overFlowLength&&right-left+1<len) {
                len=right-left+1;
                ans = len;
            }

        }
        return ans;
    }
}
