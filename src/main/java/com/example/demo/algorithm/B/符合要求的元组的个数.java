package com.example.demo.algorithm.B;

import java.util.*;

public class 符合要求的元组的个数 {
//    static int[] nums;
//    static int k;
//    static int target;
    static int ans = 0;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//        k = Integer.parseInt(sc.nextLine());
//        target = Integer.parseInt(sc.nextLine());
//
//        System.out.println(getResult());
//    }
//
//    public static int getResult() {
//        Arrays.sort(nums);
//        dfs(0, 0, 0);
//        return ans;
//    }
//
//    /**
//     * 回溯算法 组合求解
//     *
//     * @param index 当前树层选取元素的起始位置，每次递归就是一层
//     * @param total 组合内元素之和
//     * @param count 组合内元素个数
//     */
//    public static void dfs(int index, long total, int count) {
//        // 组合内元素个数达到k个时
//        if (count == k) {
//            // 检查组合内元素之和是否为target
//            if (total == target) {
//                // 若是，则符合要求的元组个数+1
//                ans += 1;
//            }
//            return;
//        }
//
//        for (int i = index; i < nums.length - (k - count) + 1; i++) {
//            // 树层去重
//            if (i > index && nums[i] == nums[i - 1]) continue;
//            // 回溯逻辑已隐含
//            dfs(i + 1, total + nums[i], count + 1);
//        }
//    }
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String numsString = sc.nextLine();
    int k = sc.nextInt();
    int target = sc.nextInt();
    System.out.println(getResult(numsString, k, target));
}

    private static int getResult(String numsString, int k, int target) {
        int[] nums = Arrays.stream(numsString.split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);
        Deque<Integer> combine = new ArrayDeque<>();

        dfs(nums, combine, target, 0, 0, k);
        return ans;
    }

//    1000000000 1000000000 1000000000 1000000000
//    4
//    -294967296
    // 这里current求和的参数要用long否则上面这个用例会返回1正常应该返回0
    private static void dfs(int[] nums, Deque<Integer> combine, int target, long current, int index, int k) {
        // size为0时不计算当前总和
        if (combine.size() != 0 && combine.size() == k) {
            for (int num : combine) {
                current += num;
            }
            if (current == target) {
                ans ++;
            }
            return;
        }


        // nums.length - (k - combine.size()) + 1 剪枝； (k - combine.size())还可以选入的容量；nums.length - (k - combine.size()) + 1这个就是取值上限 即最大起点
        // 2 7 11 15   四个数中若只取三个数  4 - (3 - 0) + 1 = 2 ; i <2; 即 i=0，i=1
        for (int i = index; i < nums.length - (k - combine.size()) + 1; i++) {
            // 去重
            // -4 -1 -1 0 1 2；当从 -4 -1 这一分支遍历完到 第二个 -4 -1分支的时候会跳过即去重 因为回溯index会回到原来的数值但是i在combine.removeLast();后会i++大于index
            if (i > index && nums[i] == nums[i - 1]) continue;

            combine.add(nums[i]);
            dfs(nums, combine, target, current, i+1, k);
            combine.removeLast();
        }
    }
}
