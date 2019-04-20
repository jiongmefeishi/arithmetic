package cn.zqtao.code.leetcode.other;

import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class ClimbStairs {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            int n = scanner.nextInt();
            System.out.println(new ClimbStairs().climbStairs3(n));
        }

    }

    /**
     * 方法 3：动态规划
     * 算法
     *
     * 不难发现，这个问题可以被分解为一些包含最优子结构的子问题，
     * 即它的最优解可以从其子问题的最优解来有效地构建，我们可以使用动态规划来解决这一问题。
     *
     * 第 i 阶可以由以下两种方法得到：
     *
     * 在第 (i−1) 阶后向上爬 1 阶。
     *
     * 在第 (i−2) 阶后向上爬 2 阶。
     *
     * 所以到达第 i 阶的方法总数就是到第 (i−1) 阶和第 (i−2) 阶的方法数之和。
     *
     * 令 dp[i] 表示能到达第 i 阶的方法总数：
     *
     * dp[i]=dp[i-1]+dp[i-2]
     * dp[i]=dp[i−1]+dp[i−2]
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，单循环到 n 。
     *
     * 空间复杂度：O(n)。dp 数组用了 n 的空间。
     */
    public int climbStairs3(int n){
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    /**
     * 在上一种方法中，我们计算每一步的结果时出现了冗余。
     * 另一种思路是，我们可以把每一步的结果存储在 memomemo 数组之中，每当函数再次被调用，
     * 我们就直接从 memomemo 数组返回结果。
     *
     * 在 memomemo 数组的帮助下，我们得到了一个修复的递归树，其大小减少到 n 。
     *
     * 空间复杂度：O(n)。递归树的深度可以达到 n 。
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs2(0, n, memo);
    }

    public int climb_Stairs2(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs2(i + 1, n, memo) + climb_Stairs2(i + 2, n, memo);
        return memo[i];
    }

    public int climbStairs1(int n) {
        return climb_Stairs1(0, n);
    }

    /**
     * 暴力法
     *
     * 模拟每一次要前进的阶数
     * @param i 当前所处位置
     * @param n 目标位置
     */
    public int climb_Stairs1(int i, int n) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs1(i + 1, n) + climb_Stairs1(i + 2, n);
    }
}
