package com.example.demo.algorithm.leetcode.每日一题;

import org.apache.commons.lang3.StringUtils;

public class 参加考试的最大学生数 {
    public static void main(String[] args) {
        System.out.println(Integer.bitCount(6));
//        int i = new Solution1349().maxStudents(new char[][]{{'#', '#', '.', '#', '.', '#'},{'.','#','#','#','#','.'},{'#','.','#','#','.','#'}});
//        System.out.println(i);
    }
}

/**
状态压缩 DP, 各种位运算。不容易<br></br>
 Time: O(m*2^(2n)), m = seats.length, n = seats[0].length<br></br>
 Space: O(m*2^n)<br></br>
 */

/**
 * 第一次做 23/12/28 不会
 */
class Solution1349 {
    public int maxStudents(char[][] seats) {
        final int m = seats.length, n = seats[0].length;

        // （空间还可以优化）
        int[][] dp = new int[m + 1][1 << n];
        // 遍历每一行
        for (int i = 1; i <= m; i++) {
            // invalid 这个二进制数表示当前行 （i 行）broken seats (也就是 '#') 的状态，
            // 用 1 表示 broken seats ('#').
            // 比如 当前行是： ["#","#",".","#",".","#"]，
            // 那么 invalid 最后就应该是 110101,如果对照上边的pattern 的话，但其实我们用了
            // 高位在前的表示，也就是 invalid 二进制的右边数第一位表示的是当前行左边的第一位。
            // 这样，invalid 最后真正的二进制就是 110101 的反序： 101011
            int invalid = 0;
            // 遍历当前行的每一列用来得到最后的 invalid
            for (int j = 0; j < n; j++) {
                if (seats[i - 1][j] == '#') {
                    invalid |= 1 << j;
                }
            }

            // 遍历 dp[i][j] 中的这个 j，也就是第 i 行的状态，这个状态下， 1 表示的是做人的位置，
            for (int j = 0; j < (1 << n); j++) {
                // 当前状态 j 左移一位，得到 adjacentMask
                int adjacentMask = j << 1;
                // 坐在坏椅子上或相邻座位已坐，舍弃该状态
                // 如果 j & invalid) != 0， 说明 j 状态下坐人的位置（1的位置）上有坏的座位（invalid 里 1 的位置是坏的），
                // 这显然不是合法的，应该舍弃该状态。
                // 如果 j 状态下相邻位置不能同时坐人的话，也就是人和人之间至少得隔一个座位，这种情况下，
                // j & adjacentMask == 0, 如果 != 0, 就说明有相邻座位都坐人，也就是至少有2个 连续1 的状态 xx11xxx
                // 这个当然也是非法的，应该舍弃。
                // 注意，对于当前行 i 的状态来说，只需要 j & adjacentMask， 也就是 j 与上当前行左移一位就可以判断是否
                // 有相邻的坐在了一起，不需要同时把 j 与上本行右移一位。请仔细体会和画图就一目了然了。
                // 舍弃就是设 dp[i][j] 为一个不影响的值， 因为是求 max，所以设个负值就可以了。
                if ((j & invalid) != 0 || (j & adjacentMask) != 0) {
                    dp[i][j] = -1;
                    continue;
                }

                int theOtherAdjacentMask = j >> 1;

                // 如果状态可行，遍历i的上一行的所有状态，寻找状态最大值
                for (int s = 0; s < (1 << n); s++) {
                    // 如果 s 不合法，舍弃状态 s
                    if (dp[i - 1][s] == -1) {
                        continue;
                    }
                    // 记住此时 s 是 i 的上一行的状态，adjacentMask 和 theOtherAdjacentMask 是 i 这一行的状态
                    // 分别向左移一位和向右移一位的状态。
                    // (s & adjacentMask) != 0 说明 i 的左上一位是有人坐的，这个是不合法的，所以舍弃；
                    // (s & theOtherAdjacentMask) != 0 说明  i 的右上一位是有人坐的，这个是不合法的，所以舍弃；
                    if ((s & adjacentMask) != 0 || (s & theOtherAdjacentMask) != 0) {
                        continue;
                    }
                    // 剩下的就是 i 这一行是合法的，那能坐的人数就是上一行在上一行的状态s下的人数 dp[i - 1][s]
                    // 再 加上(+) i 这一行坐的人数。 i 这一行坐的人数，就是 i 这一行的状态 j 里 1 的个数。
                    // 这个个数用 Integer.bitCount(j) 来求。
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][s] + Integer.bitCount(j));
                }
            }
        }

        //noinspection OptionalGetWithoutIsPresent
        // 下面的写法很不熟悉。大概的意思就是 IntStream.of(dp[n]) 把 dp[n][j] 所有的 j 对于的 dp值给取出来了，
        // 然后取个 max(), 但是取完 max() 是 OptionalInt 类型，还不能直接返回这个，还有再来个 getAsInt().
        // 实际就是返回 dp[n][j] (0 <= j < (1 << m)) 中最大的那个。
        // 这个可以用最普通的打擂台的方式获得，好懂，容易记。
        // 这个数据流的方式取最大值，如果很熟练，代码是简洁一些。但是对于目前的我来说不是很好记。
        // 所以还是采用
        // return IntStream.of(dp[m]).max().getAsInt();

        int max = 0;
        for (int i = 0; i < 1 << n; i++) {
            max = Math.max(max, dp[m][i]);
        }

        return max;
    }
}