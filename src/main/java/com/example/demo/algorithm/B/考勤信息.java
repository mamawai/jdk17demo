package com.example.demo.algorithm.B;

import java.util.Map;
import java.util.Scanner;

public class 考勤信息 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i<n; i++) {
            String info = sc.nextLine();
            System.out.println(getResult(info));
        }
    }

    private static Boolean getResult(String info) {
        String[] everyTimes = info.split(" ");
        int absentT = 0;
        int present = 0;
        String pre = "";
        for (int i = 0, everyTimesLength = everyTimes.length; i < everyTimesLength; i++) {
            if (i >= 7) {
                if ("present".equals(everyTimes[i-7])) present--;
            }
            String eT = everyTimes[i];
            switch (eT) {
                case "absent":
                    if (++absentT > 1) return false;
                    break;
                case "late":
                case "leaveearly":
                    if ("late".equals(pre) || "leaveearly".equals(pre)) return false;
                    break;
                case "present":
                    present++;
                    break;
            }
            pre = eT;

            int win_length = Math.min(i+1,7);
            if (win_length - present > 3) return false;
        }
        return true;
    }
}
