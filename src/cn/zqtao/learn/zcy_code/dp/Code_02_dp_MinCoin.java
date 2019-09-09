package cn.zqtao.learn.zcy_code.dp;

import cn.zqtao.examintion.Code_04_MinItemByAllMoney;
import cn.zqtao.model.ArraySortModel;
import cn.zqtao.model.MatrixModel;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @auther: zqtao
 * @description: 换钱的最少货币数
 *
 * 1、重复使用
 *
 * 给定数组arr, arr中所有的值都为正数且不重复。
 * 每个值代表一种面值的货币,每种面值的货币可以使用    任意张,
 * 再给定一个整数aim代表要找的钱数,求组成aim的最少货币数。
 *
 * arr-[5,2,3], aim=20.4张5元可以组成20元,其他的找钱方案都要使用更多张的货币,所以返回 4
 * arr-[5,2,3], aim=0不用任何货币就可以组成0元,返回 0.
 * arr-[3,5], aim=2.根本无法组成2元,钱不能找开的情况下默认返回 -1.
 */
public class Code_02_dp_MinCoin {

    /**
     * 动态规划
     * dp[i][j] 表示在可以使用 arr[0..i] 任意货币的情况下，组成 j 需要的最小张数
     * @param arr 货币价值
     * @param aim 目标值
     * @return 组成 aim 最少需要货币张数
     *
     * 完全不使用当前货币arr[i]情况下的最少张数,即 dpl[i-1][j]的值
     * 只使用1张当前货币arr[i]情况下的最少张数,即 dp[i-1][j-arr[i]] + 1
     * 只使用2张当前货币arr[i]情况下的最少张数,即 dp[i-1][j-2*arr[i]] + 2
     * 只使用3张当前货币arr[i]情况下的最少张数,即 dp[i-1][j-3*arr[i]] + 3
     *
     * 总结所有情况下，最终取最小张数
     * dp[i][j]=min{dp[i-1][j-K*arr[i]] + K}  (K >= 0)
     *
     * 简化依赖
     * dp[i][j]=min{dp[i-1][j], dp[i][j-arr[i]] + 1}
     * 其中 j-arr[i] <0 时越界，说明arr[i] 值太大，用一张货币都会超过钱数 j
     *
     * dp[i][j] 依赖左边一个值和上边一个值
     */
    public static int minCoin(int[] arr, int aim){
        if (arr == null || arr.length == 0 || aim < 0) return -1;

        int[][] dp = new int[arr.length][aim+1];
        int max = Integer.MAX_VALUE;

        for (int j = 1; j <= aim; j++) { // 初始化第一行，只是用arr[0] 的情况下
            dp[0][j] = max;
            if (j - arr[0] >= 0 && dp[0][j - arr[0]] != max) {
                dp[0][j] = dp[0][j - arr[0]] + 1;
            }
        }

        int left = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) {
                    left = dp[i][j-arr[i]] + 1;
                }

                dp[i][j] = Math.min(dp[i-1][j], left);
            }
        }
        return dp[arr.length - 1][aim] != max ? dp[arr.length - 1][aim] : -1; // 结果可能是 -1
    }

    public static int minCoin2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return -1;

        int[] dp = new int[aim+1];
        int max = Integer.MAX_VALUE;

        for (int j = 1; j <= aim; j++) { // 初始化第一行，只是用arr[0] 的情况下
            dp[j] = max;
            if (j - arr[0] >= 0 && dp[j - arr[0]] != max) {
                dp[j] = dp[j - arr[0]] + 1;
            }
        }

        int left = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != max){
                    left = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(dp[j], left);
            }
        }
        return dp[aim] == max ? -1 : dp[aim];
    }

    public static void main(String[] args) {
//        int[] arr = {10, 15,2, 1};
//        int aim = 4;
//        System.out.println(minCoin2(arr, aim));
//        System.out.println(Code_04_MinItemByAllMoney.solution(arr, aim));
        for (int i = 0; i < 100; i++) {
            int[] array = ArraySortModel.generateRandomArray(10, 20, false);
            int aim = (int)(Math.random() * 20 + 1);
//            if (minCoin(array, aim) != Code_04_MinItemByAllMoney.solution(array, aim)) {
            if (minCoin(array, aim) != minCoin2(array, aim)) {
                System.out.println("fuck");
                System.out.println(Arrays.toString(array));
                System.out.println("aim: " + aim);
                System.out.println(minCoin(array, aim) + "  ---   " + Code_04_MinItemByAllMoney.solution(array, aim));
            }
        }
    }
}
