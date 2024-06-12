package com.example.demo.algorithm.leetcode.每日一题.medium;

import ch.qos.logback.classic.spi.EventArgUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

public class 最小栈 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        minStack.top();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
        minStack.pop();
        minStack.push(2147483647);
        minStack.top();
        minStack.getMin();
        minStack.push(-2147483648);
        minStack.top();
        minStack.getMin();
        minStack.pop();
        minStack.getMin();
    }
}

class MinStack {

    LinkedList<Integer> min;
    LinkedList<Integer> value;
    int curMin = Integer.MAX_VALUE;

    public MinStack() {
        min = new LinkedList<>();
        value = new LinkedList<>();

    }

    public void push(int val) {
        curMin = Math.min(val, curMin);
        min.push(curMin);
        value.push(val);
    }

    public void pop() {
        value.pop();
        min.pop();
        if (min.size() == 0) curMin = Integer.MAX_VALUE;
        else curMin = min.peek();
    }

    public int top() {
        return value.peek();
    }

    public int getMin() {
        return min.peek();
    }
}