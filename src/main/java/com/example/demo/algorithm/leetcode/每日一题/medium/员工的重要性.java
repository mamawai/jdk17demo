package com.example.demo.algorithm.leetcode.每日一题.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 员工的重要性 {
    public static void main(String[] args) {

    }
}

class Solution690 {
    public int getImportance(List<Employee> employees, int id) {
        Employee[] emps = new Employee[2001];
        for (Employee emp : employees) {
            emps[emp.id] = emp;
        }
        return dfs(emps, id);
    }

    private int dfs(Employee[] emps, int id) {
        Employee emp = emps[id];
        if (emp == null) return 0;
        int ans = emp.importance;
        for (int subId : emp.subordinates) {
            ans += dfs(emps, subId);
        }
        return ans;
    }
}

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
}