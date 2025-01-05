package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.Map;

public class 设计一个ATM机器 {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.deposit(new int[]{0,0,1,2,1});
        atm.withdraw(600);
        atm.deposit(new int[]{0,1,0,1,1});
        atm.withdraw(600);
        atm.withdraw(550);
    }
}

class ATM {

    int[] deposit;
    int[] money;

    public ATM() {
        deposit = new int[5];
        money = new int[]{20, 50, 100, 200, 500};
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < banknotesCount.length; i++) {
            deposit[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] res = new int[5];
        // 只能先拿能拿到钞票里面的最大的钞票
        for (int i = money.length - 1; i >= 0 ; i--) {
            while (amount >= money[i] && deposit[i] > 0) {
                int slice = Math.min(amount / money[i], deposit[i]);
                deposit[i] -= slice;
                res[i] += slice;
                amount -= slice * money[i];
            }
        }
        if (amount == 0) return res;
        // 取款失败，还原钞票数量
        for (int i = 0; i < res.length; i++) {
            deposit[i] += res[i];
        }
        return new int[]{-1};
    }
}