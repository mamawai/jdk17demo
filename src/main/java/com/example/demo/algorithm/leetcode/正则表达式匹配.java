package com.example.demo.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class 正则表达式匹配 {
    public static void main(String[] args) {
        boolean match = new Solution10().isMatch("aab", "c*a*b");
        System.out.println(match);
    }
}

class Solution10 {
    public boolean isMatch(String s, String p) {
        List<Integer> starList = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                starList.add(i-1-starList.size());
            }
        }

        p = p.replace("*","");
        int index = -1;
        Iterator<Integer> iterator = starList.iterator();
        if (iterator.hasNext()) {
            // 初始化index
            index = iterator.next();
        }
        int j,k;
        for (j = 0, k = 0; j < s.length() && k < p.length(); j++, k++) {
            // 如果此时对比的两个char p中的char后面没跟*
            if (k != index) {
                // 同时遍历s p 相同则继续
                if (s.charAt(j) == p.charAt(k)) {
                    continue;
                } else {
                    if (p.charAt(k) == '.') {
                        continue;
                    } else {
                        return false;
                    }
                }
            } else {
                // 此时 p中的char后面有*
                // 如果当前两个char相同 那就说明*至少匹配到了一个
                if (s.charAt(j) == p.charAt(k)) {
                    while (j + 1 < s.length() && s.charAt(j+1) == p.charAt(k)) {
                        j++;
                    }
                    if (j == s.length() - 1) {
                        return matchBehind(s, p, k, iterator);
                    }
                } else {
                    if (p.charAt(k) == '.') {
                        // 如果 . 不是最后一个则break去下面判断 . 后续是否能匹配上
                        if (k != p.length() - 1) {
                            return matchBehind(s, p, k, iterator);
                        } else {
                            return true;
                        }
                    } else {
                        // 考虑*匹配零个的情况 j后退一步因为最后要j++ k就正常++跳过
                        j--;
                    }
                }
                if (iterator.hasNext()) {
                    index = iterator.next();
                }
            }
        }

        if (j == s.length() && k == p.length()) {
            return true;
        }

        int needToMinus = 0;
        if (index != -1) {
            needToMinus = 1;
        }
        while (iterator.hasNext()) {
            iterator.next();
            needToMinus++;
        }


        return j == s.length() && k == p.length()-needToMinus;
    }

    // baaaaa
    // ba*aa
    private boolean matchBehind(String s, String p, int k, Iterator<Integer> iterator) {
        int needSkip = -1;
        while (iterator.hasNext()) {
            needSkip = iterator.next();
        }
        int l = s.length() - 1;
        for (int i = p.length() - 1; i > k; i--) {
            if (p.charAt(i) != s.charAt(l--) && i != needSkip) {
                return false;
            }
        }
        return true;
    }
}