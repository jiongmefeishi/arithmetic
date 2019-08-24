package cn.zqtao.learn.nowcode_other.day9;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @auther: zqtao
 * @description:
 * @version: 1.0
 */
public class Code_33_SmallestUnFormedSum {

    // 纯暴力
    public static int unformedSum1(int[] arr) {
        if (arr == null || arr.length == 0)
            return 1;
        HashSet<Integer> set = new HashSet<>(); // 存储子数组的和
        process(arr, 0, 0, set);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(arr[i], min);
        }

        for (int i = min + 1; i != Integer.MIN_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * @param sum 上一个递归传递下来的 子数组和
     * @param i   当前所在位置
     * @param set 和集
     */
    public static void process(int[] arr, int i, int sum, HashSet<Integer> set) {
        if (i == arr.length) {
            set.add(sum);
            return;
        }
        process(arr, i + 1, sum + arr[i], set); // 要当前节点
        process(arr, i + 1, sum, set); // 不要当前节点
    }

    /**
     * 动态规划
     *
     * @param arr
     * @return dp row: sum j   col: arr[i]
     * dp(i,j) : 含义是 0~i 范围上的累加和 j
     */
    public static int unformedSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }

        boolean[][] dp = new boolean[arr.length][sum + 1];
        dp[0][arr[0]] = true; // 只有一个元素累加和，第一行只有这一个是true，其他都是false
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
//                if (dp[i - 1][j]) { // 上一个是true，下一个就是true
//                    dp[i][j] = true;
//                } else { // 上一个是false，寻找上一行的位置是否是true
//                    int preSum = j - arr[i];
//                    dp[i][j] = dp[i][preSum];
//                }

                if (j - arr[i] >= 0) { // 防止越界
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

                System.out.println("begin-----------------");
                for (int m = 0; m < dp.length; m++) {
                    for (int n = 0; n < dp[0].length; n++) {
                        System.out.print(dp[m][n] + "\t");
                    }
                    System.out.println();
                }
                System.out.println("\n\n");
            }
        }


        for (int j = min; j < dp[0].length; j++) {
            if (!dp[arr.length - 1][j])
                return j;
        }
        return sum + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        System.out.println(unformedSum1(arr));
        System.out.println(unformedSum2(arr));
    }
}
