package com.example.demo.javaPractice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class testFI {
    public static void main(String[] args) {
        boolean b = new Solution().oneEditAway("teacher", "bleacher");
        System.out.println(b);
    }
}
class Solution {
    public boolean oneEditAway(String first, String second) {
        int diff = 0;
        if (first.length() == second.length()) {
            for (int i = 0; i < first.length(); i++) {
                if (diff > 1) return false;
                if (first.charAt(i) != second.charAt(i)) diff++;
            }
        } else if (Math.abs(first.length() -second.length()) == 1){
            int i1 = first.length() - second.length();
            for (int i = 0, j = 0; i < first.length() && j < second.length(); i++ , j++) {
                if (diff > 1) return false;
                if (i1 > 0) {
                    if (first.charAt(i) != second.charAt(j)) {
                        diff ++;
                        j--;
                    }
                } else {
                    if (first.charAt(i) != second.charAt(j)) {
                        diff ++;
                        i--;
                    }
                }
            }
        } else {
            return false;
        }
        return diff == 1 || diff == 0;
    }
}