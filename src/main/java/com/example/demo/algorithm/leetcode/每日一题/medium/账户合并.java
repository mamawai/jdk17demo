package com.example.demo.algorithm.leetcode.每日一题.medium;

import org.apache.commons.codec.StringEncoderComparator;

import java.util.*;
import java.util.function.Predicate;

public class 账户合并 {
    public static void main(String[] args) {
        ArrayList<List<String>> accounts = new ArrayList<>();
        ArrayList<String> one = new ArrayList<>();
        one.add("David");
        one.add("David0@m.co");
        one.add("David1@m.co");
        ArrayList<String> two = new ArrayList<>();
        two.add("David");
        two.add("David3@m.co");
        two.add("David4@m.co");
        ArrayList<String> three = new ArrayList<>();
        three.add("David");
        three.add("David4@m.co");
        three.add("David5@m.co");
        ArrayList<String> four = new ArrayList<>();
        four.add("David");
        four.add("David2@m.co");
        four.add("David3@m.co");
        ArrayList<String> five = new ArrayList<>();
        five.add("David");
        five.add("David1@m.co");
        five.add("David2@m.co");
        accounts.add(one);
        accounts.add(two);
        accounts.add(three);
        accounts.add(four);
        accounts.add(five);
        List<List<String>> lists = new Solution721B().accountsMerge(accounts);
        System.out.println(lists);
    }
}

class Solution721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<List<String>>> map = new HashMap<>();
        for (List<String> account : accounts) {
            String key = account.get(0);
            List<List<String>> value = map.getOrDefault(key, new ArrayList<>());
            ArrayList<String> subValue = new ArrayList<>();
            for (int i = 1; i < account.size(); i++) {
                subValue.add(account.get(i));
            }
            value.add(subValue);
            map.put(key, value);
        }

        for (Map.Entry<String, List<List<String>>> entry : map.entrySet()) {
            List<List<String>> value = entry.getValue();
            String key = entry.getKey();
            if (value.size() == 1) {
                List<String> e = new ArrayList<>();
                e.add(key);
                // value排序
                HashSet<String> set = new HashSet<>(value.get(0));
                e.addAll(sortList(new ArrayList<>(set)));
                res.add(e);
            } else {
                // 看是否需要合并
                int before = value.size();
                int after = before;
                while (true) {
                    for (int i = 0; i < value.size(); i++) {
                        List<String> f = value.get(i);
                        if (f.size() == 0) continue;
                        for (int j = 0; j < value.size(); j++) {
                            if (i == j) continue;
                            List<String> s = value.get(j);
                            if (s.size() == 0) continue;
                            for (String s1 : s) {
                                if (f.contains(s1)) {
                                    f.addAll(s);
                                    value.set(j, new ArrayList<>());
                                    after--;
                                    break;
                                }
                            }
                        }
                    }
                    if (before == after) break;
                    else before = after;
                }
                for (List<String> strings : value) {
                    if (strings.size() != 0) {
                        List<String> e = new ArrayList<>();
                        e.add(key);
                        HashSet<String> set = new HashSet<>(strings);
                        e.addAll(sortList(new ArrayList<>(set)));
                        res.add(e);
                    }
                }
            }
        }

        return res;
    }

    private List<String> sortList(List<String> value) {
        value.sort((o1, o2) -> {
            int minL = Math.min(o1.length(), o2.length());
            for (int i = 0; i < minL; i++) {
                char c1 = o1.charAt(i);
                char c2 = o2.charAt(i);
                if (c1 != c2) return c1 - c2;
            }
            return o1.length() - o2.length();
        });
        return value;
    }
}

class Solution721B {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Integer>> emailToIdx = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int k = 1; k < accounts.get(i).size(); k++) {
                emailToIdx.computeIfAbsent(accounts.get(i).get(k), x -> new ArrayList<>()).add(i);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        boolean[] vis = new boolean[accounts.size()];
        Set<String> emailSet = new HashSet<>(); // 用于收集 DFS 中访问到的邮箱地址
        for (int i = 0; i < accounts.size(); i++) {
            if (vis[i]) {
                continue;
            }
            emailSet.clear();
            dfs(i, accounts, emailToIdx, vis, emailSet);

            List<String> res = new ArrayList<>(emailSet);
            Collections.sort(res);
            res.add(0, accounts.get(i).get(0));

            ans.add(res);
        }
        return ans;
    }

    private void dfs(int i, List<List<String>> accounts, Map<String, List<Integer>> emailToIdx, boolean[] vis, Set<String> emailSet) {
        vis[i] = true;
        for (int k = 1; k < accounts.get(i).size(); k++) {
            String email = accounts.get(i).get(k);
            if (emailSet.contains(email)) {
                continue;
            }
            emailSet.add(email);
            for (int j : emailToIdx.get(email)) { // 遍历所有包含该邮箱地址的账户下标 j
                if (!vis[j]) { // j 没有访问过
                    dfs(j, accounts, emailToIdx, vis, emailSet);
                }
            }
        }
    }
}