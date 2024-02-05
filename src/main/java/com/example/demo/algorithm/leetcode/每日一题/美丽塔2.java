package com.example.demo.algorithm.leetcode.每日一题;

import java.util.*;

/**
 * 单调栈值得再做一遍
 */
public class 美丽塔2 {
    public static void main(String[] args) {
        long sum = new SolutionA2866().maximumSumOfHeights(Arrays.asList(6,5,3,9,2,7));
        System.out.println(sum);
    }
}

class Solution2866 {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        if (maxHeights.size() <= 2) {
            return maxHeights.stream().reduce(0, Integer::sum);
        }
        // 找峰值
        long ans = 0L;
        for (int i = 0; i <= maxHeights.size() - 1; i++) {
            if (i == 0 && maxHeights.get(0) >= maxHeights.get(1)) {
                ans = Math.max(calculateRight(0, maxHeights), ans);
            }
            else if (i == maxHeights.size() - 1) {
                ans = Math.max(calculateLeft(maxHeights.size() - 1, maxHeights), ans);
            }
            else if (i > 0 && maxHeights.get(i) > maxHeights.get(i - 1) && maxHeights.get(i) >= maxHeights.get(i + 1)) {
                ans = Math.max(calculateLeft(i, maxHeights) + calculateRight(i, maxHeights) - maxHeights.get(i), ans);
            }
        }
        return ans;
    }

    private long calculateLeft(int to, List<Integer> maxHeights) {
        // 从0到to计算求和
        Integer curMax = maxHeights.get(to);
        long sum = curMax;
        for (; to > 0; to--) {
            Integer previous = maxHeights.get(to - 1);
            if (curMax > previous) {
                sum += previous;
                curMax = previous;
            } else {
                sum += curMax;
            }
        }
        return sum;
    }

    private long calculateRight(int from, List<Integer> maxHeights) {
        // 从from到list结尾一次遍历
        Integer curMax = maxHeights.get(from);
        long sum = curMax;
        for (; from < maxHeights.size() - 1; from++) {
            Integer next = maxHeights.get(from + 1);
            if (curMax > next) {
                sum += next;
                curMax = next;
            } else {
                sum += curMax;
            }
        }
        return sum;
    }
}

// 单调栈
class SolutionA2866 {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long res = 0;
        // 这两个long数组在求和过程中相当于一个小dp 求的每个状态下前缀和 然后后一个位置可能会用到前一个位置的和
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        Deque<Integer> stack1 = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            while (!stack1.isEmpty() && maxHeights.get(i) < maxHeights.get(stack1.peek())) {
                stack1.pop();
            }
            if (stack1.isEmpty()) {
                prefix[i] = (long) (i + 1) * maxHeights.get(i);
            } else {
                prefix[i] = prefix[stack1.peek()] + (long) (i - stack1.peek()) * maxHeights.get(i);
            }
            stack1.push(i);
        }
        for (int i = n - 1; i >= 0; i--) {
            while (!stack2.isEmpty() && maxHeights.get(i) < maxHeights.get(stack2.peek())) {
                stack2.pop();
            }
            if (stack2.isEmpty()) {
                suffix[i] = (long) (n - i) * maxHeights.get(i);
            } else {
                suffix[i] = suffix[stack2.peek()] + (long) (stack2.peek() - i) * maxHeights.get(i);
            }
            stack2.push(i);
            res = Math.max(res, prefix[i] + suffix[i] - maxHeights.get(i));
        }
        return res;
    }
}

class SolutionB2866 {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long[] sufSums = new long[n];   // 后缀和数组
        Stack<Integer> st = new Stack<>();      // 单调栈
        st.push(n);         // 栈底为n表示后缀和边界
        long sufS = 0; // 后缀和
        for(int i = n - 1; i >= 0; i--){
            while(st.size() > 1 && maxHeights.get(i) <= maxHeights.get(st.peek())){
                // 在到达栈底n之前，弹出位于当前位置右侧的小于等于当前位置最大高度的索引
                int t = st.pop();   // 获取要弹出的元素
                sufS -= (long)maxHeights.get(t) * (st.peek() - t);  // 后缀和减去弹出索引对应的区间包含的高度和
            }
            sufS += (long)maxHeights.get(i) * (st.peek() - i);      // 后缀和累加要加入的索引对应的区间包含的高度和
            sufSums[i] = sufS;      // 记录后缀和
            st.push(i);             // 元素入栈
        }
        st.clear(); // 清空栈
        st.push(-1);                // 栈底为-1表示前缀和边界
        long res = 0;  // 结果值
        long preS = 0; // 前缀和
        for(int i = 0; i < n; i++){
            while(st.size() > 1 && maxHeights.get(i) <= maxHeights.get(st.peek())){
                // 在到达栈底-1之前，弹出位于当前位置左侧的小于等于当前位置最大高度的索引
                int t = st.pop();
                preS -= (long)maxHeights.get(t) * (t - st.peek());  // 前缀和和减去弹出索引对应的区间包含的高度和
            }
            preS += (long)maxHeights.get(i) * (i - st.peek());      // 前缀和累加要加入的索引对应的区间包含的高度和
            res = Math.max(res, preS + sufSums[i] - maxHeights.get(i));      // 得到当前位置前后缀和，更新最大值
            st.push(i);     // 元素入栈
        }
        return res;
    }
}

